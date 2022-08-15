package mas.project.mp5.repository;

import mas.project.mp5.model.Author;
import mas.project.mp5.model.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.sql.Date;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class IndividualRepositoryTest {

    @Autowired
    private IndividualRepository individualRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @PersistenceContext
    private EntityManager entityManager;

    private Author author;
    private Customer customer;

    @BeforeEach
    public void initData() {
        author = Author.builder()
                .firstName("FN1")
                .lastName("LN1")
                .dateOfBirth(Date.valueOf("2002-04-13"))
                .email("example@gmail.com")
                .build();

        customer = Customer.builder()
                .firstName("FN2")
                .lastName("LN2")
                .dateOfBirth(Date.valueOf("2002-04-13"))
                .email("example2@gmail.com")
                .phoneNumber("223-433-233")
                .isLoyal(false)
                .build();
    }

    @Test
    public void testRequiredDependencies() {
        assertNotNull(individualRepository);
        assertNotNull(authorRepository);
        assertNotNull(customerRepository);
    }

    @Test
    public void testSaveIndividuals() {
        authorRepository.save(author);
        customerRepository.save(customer);
        entityManager.flush();

        long count = individualRepository.count();
        assertEquals(2, count);
    }
}