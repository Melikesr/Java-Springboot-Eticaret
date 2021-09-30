package com.example.Eticaret;

import com.example.Eticaret.Entity.Role;
import com.example.Eticaret.Entity.RoleRepository;
import com.example.Eticaret.Entity.User;
import com.example.Eticaret.Entity.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace =Replace.NONE)
@Rollback(false)
public class RoleRepositoryTests {

    @Autowired
    private RoleRepository repo;
    @Autowired
    private UserRepository userRepo;
    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void testCreateRole(){
        Role user=new Role("User");
        Role admin=new Role("Admin");
        Role customer=new Role("Customer");




        repo.saveAll(List.of(user,admin,customer));
        List<Role> listRoles=repo.findAll();
        assertThat(listRoles.size()).isEqualTo(3);
    }



}
