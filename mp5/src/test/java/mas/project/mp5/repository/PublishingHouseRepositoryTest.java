package mas.project.mp5.repository;

import mas.project.mp5.model.PublishingHouse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.ConstraintViolationException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class PublishingHouseRepositoryTest {

    @Autowired
    private PublishingHouseRepository publishingHouseRepository;

    @PersistenceContext
    private EntityManager entityManager;

    PublishingHouse publishingHouse;

    @BeforeEach
    public void init() {
        publishingHouse = PublishingHouse.builder()
                .name("BookLover")
                .email("booklover@gmail.com")
                .build();
    }

    @Test
    public void testRequiredDependencies() {
        assertNotNull(publishingHouseRepository);
    }

    @Test
    public  void testFetchPH() {
        Iterable<PublishingHouse> publishingHouses = publishingHouseRepository.findAll();
        for(PublishingHouse ph : publishingHouses) {
            System.out.println(ph);
        }
    }

    @Test
    public void testSavePH() {
        publishingHouseRepository.save(publishingHouse);
        entityManager.flush();
        long count = publishingHouseRepository.count();
        assertEquals(1, count);
    }

    @Test
    public void testSaveInvalidEmailPH() {
        assertThrows(ConstraintViolationException.class, () -> {
            publishingHouse.setEmail("ssfsdf");
            publishingHouseRepository.save(publishingHouse);
            entityManager.flush();
        });
    }

    @Test
    public void testFindPHByName() {
        publishingHouseRepository.save(publishingHouse);
        entityManager.flush();
        List<PublishingHouse> publishingHouses = publishingHouseRepository.findPublishingHouseByName("BookLover");
        assertEquals(1, publishingHouses.size());
    }
}