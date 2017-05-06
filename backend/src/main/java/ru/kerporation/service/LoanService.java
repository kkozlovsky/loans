package ru.kerporation.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.kerporation.dto.GeoData;
import ru.kerporation.entities.Loan;
import ru.kerporation.entities.User;
import ru.kerporation.exception.BlacklistException;
import ru.kerporation.exception.LimitPerSecondException;
import ru.kerporation.exception.LoanNotFoundException;
import ru.kerporation.exception.UserNotFoundException;
import ru.kerporation.repository.LoanRepository;

import java.net.URI;
import java.util.Date;
import java.util.List;

@Service
public class LoanService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private static final long FIVE_SECONDS = 5000;
    private static final long ONE_SECOND = 1000;
    private static final String SYPEXGEO_URL = "http://api.sypexgeo.net";

    @Autowired
    private LoanRepository loanRepository;

    @Autowired
    UserService userService;

    @Autowired
    RestTemplate restTemplate;

    public List<Loan> getAll() {
        return loanRepository.findAll();
    }

    public Loan getOne(String loanId) {
        Loan existingLoan = loanRepository.findOne(loanId);
        if (existingLoan == null) throw new LoanNotFoundException(loanId);
        return existingLoan;
    }

    public Loan create(String userId, Loan loan) {
        User user = userService.getOne(userId);
        if (user == null) throw new UserNotFoundException(userId);
        checkBlacklist(user);
        checkLimitPerSecond(user);
        loan.setUser(user);
        loan.setCountryCode(getCountryCode());
        return loanRepository.saveAndFlush(loan);
    }

    public Loan update(String loanId, Loan loan) {
        Loan existingLoan = this.getOne(loanId);
        existingLoan.setTerm(loan.getTerm());
        existingLoan.setAmount(loan.getAmount());
        Loan newLoan = loanRepository.saveAndFlush(existingLoan);
        return newLoan;
    }

    public Loan delete(String loanId) {
        Loan existingLoan = this.getOne(loanId);
        loanRepository.delete(existingLoan);
        return existingLoan;
    }


    public List<Loan> getAllByUser(String userId) {
        User existingUser = userService.getOne(userId);
        return loanRepository.findByUser(existingUser);
    }


    private void checkBlacklist(User user) {
        if (user.isBlacklist()) {
            throw new BlacklistException("Пользователь: " + user.getName() + " " + user.getSurName() + " находится в чёрном списке");
        }
    }


    private void checkLimitPerSecond(User user) {
        Loan lastLoan = loanRepository.findTop1ByUserOrderByAddedDesc(user);
        if (lastLoan == null) return;
        long last = lastLoan.getAdded().getTime();
        long now = new Date().getTime();
        if ((now - last) < ONE_SECOND) throw new LimitPerSecondException("Больше одного запроса в секунду");
    }


    private String getCountryCode() {
        String countryCode;
        try {
            ResponseEntity<GeoData> geoData = restTemplate.getForEntity(new URI(SYPEXGEO_URL), GeoData.class);
            countryCode = geoData.getBody().getCountry().getCode();
        } catch (Exception e) {
            countryCode = "--";
        }
        return countryCode;
    }
}
