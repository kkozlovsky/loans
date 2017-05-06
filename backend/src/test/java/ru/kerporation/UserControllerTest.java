package ru.kerporation;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import ru.kerporation.entities.User;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testGetPersonById(){
        ResponseEntity<User> response = restTemplate.getForEntity("/apiusers/users/ivan_petrov@test.ru", User.class);
        assertThat(response.getStatusCode() , equalTo(HttpStatus.OK));
        User user = response.getBody();
        assertThat(user.getUserId(), equalTo("ivan_petrov@test.ru"));
        assertThat(user.getName(), equalTo("Иван"));
        assertThat(user.getSurName(), equalTo("Петров"));
    }

}
