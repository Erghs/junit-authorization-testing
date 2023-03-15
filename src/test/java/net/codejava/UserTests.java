package net.codejava;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class UserTests {

	private AppController appController;

	@Autowired
	private TestEntityManager entityManager;
	
	@Autowired
	private UserRepository repo;

	//добавление данных в БД
	@Test
	public void testCreateUser() {
		User user = new User();
		user.setEmail("IvanovIvan@gmail.com");
		user.setPassword("ре89302рк");
		user.setFirstName("Иван");
		user.setLastName("Иванов");

		//сохраняем пользователя
		User savedUser = repo.save(user);

		//ищем этого пользователя в бд
		User existUser = entityManager.find(User.class, savedUser.getId());

		//Проверяем, что пользователь с таким email существует в базе данных
		//т.е проверяем, что данные в бд добавились
		assertEquals(user.getEmail(), existUser.getEmail());

	}
	//Проверка существования пользователя бд 1 позитивный и 1 негативный
	@Test
	public void testFindByEmail() {
		String email = "name@code.net";
		//пытаемся найти несуществующего пользователя
		User user = repo.findByEmail(email);
		assertFalse(user!=null);
	}
	@Test
	public void testFindByEmailUser() {
		String email = "hisamiewa2017@yandex.ru";
		//ищем существующего пользователя по email
		User user = repo.findByEmail(email);
		assertTrue(user.getEmail().equals(email));
	}

}
