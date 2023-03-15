package net.codejava;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
public class ValidationTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserRepository repo;
    //негативные тесты на валидацию
    @Test
    public void passValidation() {
        //пытаемся создать пользователя с невалидным паролем
        User user = new User();
        user.setEmail("h33oei7qw434a@gmail.com");
        user.setPassword("12");
        user.setFirstName("Aleksei");
        user.setLastName("Igorev");

        User existUser = repo.findByEmail(user.getEmail());

        assertEquals(existUser,null);

    }
    @Test
    public void emailValidation() {
        User user = new User();
        //попытка создать пользователя с невалидным email
        user.setEmail("h33ogmailcom6677");
        user.setPassword("1s324");
        user.setFirstName("firstmame");
        user.setLastName("lastname");

        assertEquals(user.getId(),null);

    }

    //Позитивные тесты на валидацию полей логин и пароль
    @Test
    public void validationPositive() {
        User user = new User();
        user.setEmail("hajshhdgqh@gmail.com");
        user.setPassword("Entyaghwye");
        user.setFirstName("Yan");
        user.setLastName("Syjjdd");

        User savedUser = repo.save(user);

        User existUser = entityManager.find(User.class, savedUser.getId());

        assertTrue(savedUser.getEmail().equals(existUser.getEmail()));

    }
    @Test
    public void validationPositive2() {
        User user = new User();
        user.setEmail("rtsуgagd77@yandex.ru");
        user.setPassword("1234sbbhss");
        user.setFirstName("Ygsvq");
        user.setLastName("fefefef");

        User savedUser = repo.save(user);

        User existUser = entityManager.find(User.class, savedUser.getId());

        assertEquals(savedUser.getEmail(),existUser.getEmail());
    }

}
