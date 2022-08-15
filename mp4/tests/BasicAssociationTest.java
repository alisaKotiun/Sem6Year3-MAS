import helper.Address;
import helper.InvalidFieldException;
import org.junit.Before;
import org.junit.Test;
import xor.Author;
import xor.EPublisher;
import xor.PublishingHouse;

import java.time.LocalDateTime;

import static org.junit.Assert.*;

public class BasicAssociationTest {
    Author author1;
    Address address1, address2;
    PublishingHouse publishingHouse1, publishingHouse2;
    EPublisher ePublisher1, ePublisher2;

    @Before
    public void init() throws InvalidFieldException {
        address1 = new Address("Poland", "Warsaw", "Koszykowa", "86a");
        address2 = new Address("Poland", "Warsaw", "Koszykowa", "86b");

        publishingHouse1 = new PublishingHouse("PB1", address1, "email@gmail.com");
        publishingHouse2 = new PublishingHouse("PB2", address2, "email@gmail.com");

        ePublisher1 = new EPublisher("EP1", "wewe.com", "alisa@gmail.com");
        ePublisher2 = new EPublisher("EP2", "wewe.com", "alisa@gmail.com");

    }

    @Test
    public void changeToEPublisher() throws InvalidFieldException {
        author1 = new Author("fName1", "lName1", LocalDateTime.of(1980, 1, 1, 1, 1 ), "email@gmail.com", "(123) 321 123", publishingHouse1);
        author1.changeEPublisher(ePublisher1);

        assertEquals(0, publishingHouse1.getAuthors().size());
        assertEquals(1, ePublisher1.getAuthors().size());
        assertNotNull(author1.getEPublisher());
        assertNull(author1.getPublishingHouse());
    }

    @Test
    public void changeToAnotherPublishingHouse() throws InvalidFieldException {
        author1 = new Author("fName1", "lName1", LocalDateTime.of(1980, 1, 1, 1, 1 ), "email@gmail.com", "(123) 321 123", publishingHouse1);
        author1.changePublishingHouse(publishingHouse2);

        assertEquals(0, publishingHouse1.getAuthors().size());
        assertEquals(1, publishingHouse2.getAuthors().size());
        assertNotNull(author1.getPublishingHouse());
    }

    @Test
    public void changeToPublishingHouse() throws InvalidFieldException {
        author1 = new Author("fName1", "lName1", LocalDateTime.of(1980, 1, 1, 1, 1 ), "email@gmail.com", "(123) 321 123", ePublisher1);
        author1.changePublishingHouse(publishingHouse1);

        assertEquals(0, ePublisher1.getAuthors().size());
        assertEquals(1, publishingHouse1.getAuthors().size());
        assertNull(author1.getEPublisher());
        assertNotNull(author1.getPublishingHouse());
    }

    @Test
    public void changeToAnotherEPublisher() throws InvalidFieldException {
        author1 = new Author("fName1", "lName1", LocalDateTime.of(1980, 1, 1, 1, 1 ), "email@gmail.com", "(123) 321 123", ePublisher1);
        author1.changeEPublisher(ePublisher2);

        assertEquals(0, ePublisher1.getAuthors().size());
        assertEquals(1, ePublisher2.getAuthors().size());
        assertNotNull(author1.getEPublisher());
    }

    @Test
    public void addAuthorEPublisher() throws InvalidFieldException {
        author1 = new Author("fName1", "lName1", LocalDateTime.of(1980, 1, 1, 1, 1 ), "email@gmail.com", "(123) 321 123", ePublisher1);
        ePublisher2.addAuthor(author1);

        assertEquals(0, ePublisher1.getAuthors().size());
        assertEquals(1, ePublisher2.getAuthors().size());
        assertNotNull(author1.getEPublisher());
    }

    @Test
    public void addAuthorPublishingHouse() throws InvalidFieldException {
        author1 = new Author("fName1", "lName1", LocalDateTime.of(1980, 1, 1, 1, 1 ), "email@gmail.com", "(123) 321 123", publishingHouse1);
        publishingHouse2.addAuthor(author1);

        assertEquals(0, publishingHouse1.getAuthors().size());
        assertEquals(1, publishingHouse2.getAuthors().size());
        assertNotNull(author1.getPublishingHouse());
    }
}
