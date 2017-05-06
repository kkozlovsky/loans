package ru.kerporation;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ru.kerporation.entities.User;
import ru.kerporation.repository.UserRepository;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @Test
    public void testBlacklistRepositoryFind() {
        List<User> blacklist = userRepository.findByIsBlacklistTrue();
        assertNotNull(blacklist);
        assertEquals(2, blacklist.size());
    }

    @Test
    public void testBlackPersonByIdFind(){
        User User = userRepository.findOne("ivan_petrov@test.ru");
        assertEquals("Иван", User.getName());
        assertEquals("Петров", User.getSurName());
    }
}
