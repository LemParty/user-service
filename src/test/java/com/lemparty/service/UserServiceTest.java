//package com.lemparty.service;
//
//import com.lemparty.data.MongoUserRepository;
//import com.lemparty.entity.User;
//import com.lemparty.exception.InvalidPasswordException;
//import com.lemparty.exception.InvalidUserException;
//import com.lemparty.util.PasswordUtil;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.internal.util.reflection.Whitebox;
//import org.mockito.junit.MockitoJUnitRunner;
//
//import java.util.Optional;
//
//import static org.junit.Assert.assertEquals;
//import static org.mockito.Mockito.when;
//
//@RunWith(MockitoJUnitRunner.class)
//public class UserServiceTest {
//
//    @Mock
//    MongoUserRepository userRepository;
//
//    @InjectMocks
//    UserService mockUserService;
//
//    @Test
//    public void testAuthenticateUser() {
//        Whitebox.setInternalState(mockUserService, "salt", "txestsalt");
//
//        User mockUser = new User();
//        String password = "password";
//        String encryptedPassword = PasswordUtil.hashPassword(password, "testsalt").get();
//        System.out.println(PasswordUtil.hashPassword(password, "testsalt").get());
//        mockUser.setEmail("test@domain.com");
//        mockUser.setPassword(encryptedPassword);
//
//        Optional<User> optionalUser = Optional.of(mockUser);
//
//        when(userRepository.findUserByEmailForAuthentication("test@domain.com"))
//                .thenReturn(optionalUser);
//        try {
//            assertEquals(PasswordUtil.hashPassword(password, "testsalt").get(), mockUserService.authenticateUser("test@domain.com", "password").getPassword());
//        } catch (InvalidUserException e) {
//            e.printStackTrace();
//        } catch (InvalidPasswordException e) {
//            e.printStackTrace();
//        }
//
//    }
//
//}