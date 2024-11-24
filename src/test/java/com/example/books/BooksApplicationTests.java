package com.example.books;

//import com.example.books.dto.UserDTO;
//import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BooksApplicationTests {

//	@Autowired
//	private UserMapper userMapper;
//
//	@Autowired
//	private UserService userService;
//
//	@Autowired
//	private UserRepo userRepo;


	@Test
	void contextLoads() {

	}

//	@Test
//	void checkUserDto(){
//		User user = new User();
//		user.setId(55555L);
//		user.setPassword("555555");
//		user.setEmail("fffff@mail.ru");
//		user.setAddress("almaty");
//
//		UserDTO userDTO = userMapper.toDTO(user);
//
//		Assertions.assertEquals(user.getAddress(), userDTO.getLocation());
//		Assertions.assertEquals(user.getEmail(), userDTO.getEmail());
//	}
//
//	@Test
//	void cheacInsertUser() {
//		User user = new User();
//		user.setId(55555L);
//		user.setPassword("555555");
//		user.setEmail("ffff@mail.ru");
//		user.setAddress("almaty");
//
//		User newUser = userService.registerUser(user);
//
//		Assertions.assertNotNull(newUser.getId());
//		Assertions.assertEquals(user.getAddress(), newUser.getAddress());
//		Assertions.assertNotNull(newUser.getPermissions().get(0));
//		Assertions.assertEquals(user.getEmail(), newUser.getEmail());
//		Assertions.assertEquals(user.getName(), newUser.getName());
//		userService.delete(newUser.getId());
//	}
//
//
//	@Test
//	void checkupdateUser (){
//		User user = new User();
//		user.setId(55555L);
//		user.setPassword("785462");
//		user.setEmail("normal@mail.ru");
//		user.setAddress("nowhere");
//
//        User userDb = userRepo.findById(55555L).get();
//
//
//		userService.update(userDb.getId(), user);
//
//		Assertions.assertNotEquals(userDb.getEmail(), user.getEmail());
//		Assertions.assertNotNull(userDb.getPassword(), user.getPassword());
//		Assertions.assertNotNull(userDb.getAddress(), user.getAddress());
//
//	}
}
