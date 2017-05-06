package ru.kerporation.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kerporation.entities.User;
import ru.kerporation.exception.UserNotFoundException;
import ru.kerporation.repository.UserRepository;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

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

}
