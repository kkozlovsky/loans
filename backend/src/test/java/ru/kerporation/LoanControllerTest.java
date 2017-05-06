package ru.kerporation;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import ru.kerporation.entities.Loan;
import ru.kerporation.entities.User;
import ru.kerporation.exception.BlacklistException;
import ru.kerporation.exception.LimitPerSecondException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.hamcrest.core.IsNull.notNullValue;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class LoanControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testCreateLoan(){
        Loan loan = new Loan(90,200);
        HttpEntity<Loan> request = new HttpEntity<>(loan);
        System.out.println("-------------------------------------------------------------------------------------");
        ResponseEntity<Loan> response = restTemplate.exchange("/apiloans/users/ivan_petrov@test.ru", HttpMethod.POST, request, Loan.class);
        System.out.println("!!!!!!!!!!" + response);
        assertThat(response.getStatusCode() , equalTo(HttpStatus.CREATED));
        Loan createdLoan = response.getBody();
        assertThat(createdLoan.getCountryCode(), notNullValue());
        assertThat(createdLoan.getCountryCode(), instanceOf(String.class)); // проверяем отработал ли метод получения кода страны
    }


    @Test
    public void testBlacklistExcepition(){
        try {
            Loan loan = new Loan(90,200);
            HttpEntity<Loan> request = new HttpEntity<>(loan);
            restTemplate.exchange("/apiloans/users/fake2@test.ru", HttpMethod.POST, request, Loan.class);
        }
        catch (Exception e){
            assertThat(e, instanceOf(BlacklistException.class));
        }
    }

    @Test
    public void testDoubleCreatePerSecondExcepition(){
        try {
            Loan loan1 = new Loan(90,200);
            Loan loan2 = new Loan(90,200);
            HttpEntity<Loan> request1 = new HttpEntity<>(loan1);
            HttpEntity<Loan> request2 = new HttpEntity<>(loan2);
            restTemplate.exchange("/apiloans/users/titov@test.ru'", HttpMethod.POST, request1, Loan.class);
            restTemplate.exchange("/apiloans/users/titov@test.ru'", HttpMethod.POST, request2, Loan.class);
        }
        catch (Exception e){
            assertThat(e, instanceOf(LimitPerSecondException.class));
        }
    }

}
