package com.example.Eticaret;

import com.example.Eticaret.category.Category;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.Eticaret.category.CategoryRespository;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class CategoryRepositoryTests {
    @Autowired
    private CategoryRespository repo;

    @Test
    public void testCreateCategory(){

       Category sevedCategory= repo.save(new Category("Electronics"));
       assertThat(sevedCategory.getId()).isGreaterThan(0);
    }

}
