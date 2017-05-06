package ru.kerporation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kerporation.entities.User;

import java.util.List;

public interface UserRepository extends JpaRepository<User,String> {

    List<User> findByIsBlacklistTrue();
    List<User> findAllByOrderBySurNameAsc();
}
