package ru.kerporation;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ru.kerporation.entities.Loan;
import ru.kerporation.entities.User;
import ru.kerporation.repository.LoanRepository;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LoanRepositoryTest {

    @Autowired
    private LoanRepository loanRepository;

    @Test
    public void testLoanRepositoryFind() {
        List<Loan> loanList = loanRepository.findAll();
        assertNotNull(loanList);
    }

    @Test
    public void testLoansListByUserFind() {
        User user = new User();
        user.setUserId("adam@test.ru");
        List<Loan> personalLoanList = loanRepository.findByUser(user);
        assertNotNull(personalLoanList);
        assertEquals(3, personalLoanList.size());
    }

    @Test
    public void testLastLoanByPersonalIdFind() {
        User user = new User();
        user.setUserId("adam@test.ru");
        Loan lastLoan = loanRepository.findTop1ByUserOrderByAddedDesc(user);
        assertNotNull(lastLoan);
        Date current = new Date();
        assertTrue(lastLoan.getAdded().before(current));
    }

    @Test
    public void testCreateLoan() {
        User user = new User();
        user.setUserId("titov@test.ru");
        Loan loan = new Loan();
        loan.setCountryCode("RU");
        loan.setAmount(1500);
        loan.setTerm(15);
        loan.setUser(user);
        assertNotNull(loanRepository.saveAndFlush(loan));
        List<Loan> personalLoanList = loanRepository.findByUser(user);
        assertNotNull(personalLoanList);
        assertEquals(1, personalLoanList.size());
    }

}
