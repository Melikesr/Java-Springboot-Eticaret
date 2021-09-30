package com.example.Eticaret;

import com.example.Eticaret.Entity.Role;
import com.example.Eticaret.Entity.RoleRepository;
import com.example.Eticaret.Entity.User;
import com.example.Eticaret.Entity.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.as;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace =Replace.NONE)
@Rollback(false)
public class UserRepositoryTests {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private RoleRepository roleRepo;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void testCreateUser(){
        User user=new User();
        user.setEmail("sari@gmail.com");
        user.setPassword("123456");
        user.setFirstName("melike");
        user.setLastname("sarı");

        User savedUser=userRepo.save(user);

        User existUser =entityManager.find(User.class, savedUser.getId());

        assertThat(existUser.getEmail()).isEqualTo(user.getEmail());
    }

    @Test
    public void testFindUserByEmail(){
        String email="melike@gmail.com";
      User user= userRepo.findByEmail(email);

        assertThat(user).isNotNull();

    }

    @Test
    public void testAddRoleToNewUser(){
        User user=new User();
        user.setEmail("su@gmail.com");
        user.setPassword("123456");
        user.setFirstName("su");
        user.setLastname("kara");


       Role roleUser=roleRepo.findByName("User");
        user.addRole(roleUser);

        User savedUser=userRepo.save(user);

        assertThat(savedUser.getRoles().size()).isEqualTo(1);
    }

    @Test
    public void testAddRolesToExistingUser(){
        User user =userRepo.findById(1L).get();

        Role roleUser=roleRepo.findByName("User");
        user.addRole(roleUser);

        Role roleAdmin=new Role(2L);
        user.addRole(roleAdmin);

        User savedUser=userRepo.save(user);

        assertThat(savedUser.getRoles().size()).isEqualTo(2);

    }

}
