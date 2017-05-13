package ru.kerporation.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.kerporation.dto.RandomUser;
import ru.kerporation.entities.User;
import ru.kerporation.exception.RandomUserException;
import ru.kerporation.exception.UserNotFoundException;
import ru.kerporation.repository.UserRepository;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@Service
public class UserService {

    private static final String RANDOM_USER_API_URL = "http://randus.ru/api.php";

    @Autowired
    UserRepository userRepository;

    @Autowired
    RestTemplate restTemplate;

    public List<User> getAll() {
        return userRepository.findAllByOrderBySurNameAsc();
    }

    public User getOne(String userId){
        User existingUser = userRepository.findOne(userId);
        if (existingUser == null) throw new UserNotFoundException(userId);
        return existingUser;
    }

    public User create(User user){
        return userRepository.saveAndFlush(user);
    }

    public User update(String userId, User user){
        User existingUser = this.getOne(userId);
        user.setUserId(userId);
        if (user.getName() == null || user.getName().isEmpty()) {
            user.setName(existingUser.getName());
        }
        if (user.getSurName() == null || user.getSurName().isEmpty()) {
            user.setSurName(existingUser.getSurName());
        }
        return userRepository.saveAndFlush(user);
    }

    public User delete(String userId) {
        User existingUser = this.getOne(userId);
        userRepository.delete(existingUser);
        return existingUser;
    }

    public List<User> getBlacklist() {
        return userRepository.findByIsBlacklistTrue();
    }

    public User generateRandomUser() {
        User user;
        try {
            user = getRandomUser();
        } catch (URISyntaxException e) {
            throw new RandomUserException("Не удалось сгенерировать нового пользователя");
        }
        return userRepository.saveAndFlush(user);
    }

    private User getRandomUser() throws URISyntaxException {
        ResponseEntity<RandomUser> randomUserResponse = restTemplate.getForEntity(new URI(RANDOM_USER_API_URL), RandomUser.class);
        User resultUser = new User();
        resultUser.setName(randomUserResponse.getBody().getFirstName());
        resultUser.setSurName(randomUserResponse.getBody().getLastName());
        resultUser.setUserId(randomUserResponse.getBody().getLogin() + "@test.ru");
        resultUser.setBlacklist(false);
        return resultUser;
    }

}
