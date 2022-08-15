package mas.project.mp5.repository;

import mas.project.mp5.model.Author;
import mas.project.mp5.model.PublishingHouse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.sql.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class BasicAssociationTest {

    @Autowired
    private PublishingHouseRepository publishingHouseRepository;

    @Autowired
    private AuthorRepository authorRepository;

    PublishingHouse publishingHouse;
    Author author;

    @BeforeEach
    public void init() {
        publishingHouse = PublishingHouse.builder()
                .name("BookLover")
                .email("booklover@gmail.com")
                .build();

        author = Author.builder()
                .firstName("FN1")
                .lastName("LN1")
                .dateOfBirth(Date.valueOf("2002-04-13"))
                .email("example@gmail.com")
                .build();
    }

    @Test
    public void testRequiredDependencies() {
        assertNotNull(publishingHouseRepository);
        assertNotNull(authorRepository);
    }

    @Test
    public void testSave() {
        publishingHouse.getAuthors().add(author);
        publishingHouseRepository.save(publishingHouse);

        author.setSignContractWith(publishingHouse);
        authorRepository.save(author);

        Optional<PublishingHouse> byId = publishingHouseRepository.findById(publishingHouse.getId());
        assertTrue(byId.isPresent());
        assertEquals(1, byId.get().getAuthors().size());
        System.out.println(byId.get().getAuthors());
    }

}
