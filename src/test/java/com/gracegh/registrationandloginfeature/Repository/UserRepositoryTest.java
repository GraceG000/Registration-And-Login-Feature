package com.gracegh.registrationandloginfeature.Repository;

import com.gracegh.registrationandloginfeature.Entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;


@DataJpaTest//to make sure that the changes are made to the databases itself...
    @AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TestEntityManager testEntityManager;


    @Test
    public void testCreateUser(){
        User user = new User();

        user.setEmail("adrienag99@gmail.com");
        user.setPassword("iHeartmari!");
        user.setFirstName("Adrien");
        user.setLastName("Agreste");

        User savedUser = userRepository.save(user);

        User existUser = testEntityManager.find(User.class, savedUser.getId());

        assertThat(existUser.getEmail()).isEqualTo(user.getEmail());
    }

    @Test
    public void testFindUserByEmail(){
        String email = "adrienag99@gmail.com";

        User user = userRepository.findByEmail(email);

        assertThat(user).isNotNull();
    }
}