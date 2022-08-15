import basic.Author;
import basic.PublishingHouse;
import general.Address;
import org.junit.Before;
import org.junit.Test;
import util.InvalidFieldException;

import java.time.LocalDateTime;

import static org.junit.Assert.*;

public class BasicAssociationTest {
    Author author1, author2;
    Address address1, address2;
    PublishingHouse publishingHouse1, publishingHouse2;

    @Before
    public void init() throws InvalidFieldException {
        author1 = new Author("fName1", "lName1", LocalDateTime.of(1980, 1, 1, 1, 1 ), "email@gmail.com", "(123) 321 123");
        author2 = new Author("fName2", "lName2", LocalDateTime.of(1980, 1, 1, 1, 1 ), "email@gmail.com", "(123) 321 123");

        address1 = new Address("Poland", "Warsaw", "Koszykowa", "86a");
        address2 = new Address("Poland", "Warsaw", "Koszykowa", "86b");

        publishingHouse1 = new PublishingHouse("PB1", address1, "email@gmail.com");
        publishingHouse2 = new PublishingHouse("PB2", address2, "email@gmail.com");
    }

    @Test
    public void setPublishingHouse(){
        author1.setPublishingHouse(publishingHouse1);
        assertEquals(publishingHouse1, author1.getPublishingHouse());
        assertEquals(1, publishingHouse1.getAuthors().size());

        author1.setPublishingHouse(null);
        assertNull(author1.getPublishingHouse());
        assertEquals(0, publishingHouse1.getAuthors().size());

        author1.setPublishingHouse(publishingHouse1);
        author1.setPublishingHouse(publishingHouse2);
        assertEquals(0, publishingHouse1.getAuthors().size());
        assertEquals(publishingHouse2, author1.getPublishingHouse());
        assertEquals(1, publishingHouse2.getAuthors().size());
    }

    @Test
    public void removePublishingHouse(){
        author1.setPublishingHouse(publishingHouse1);
        assertEquals(publishingHouse1, author1.getPublishingHouse());
        assertEquals(1, publishingHouse1.getAuthors().size());

        author1.removePublishingHouse();
        assertNull(author1.getPublishingHouse());
        assertEquals(0, publishingHouse1.getAuthors().size());
    }

    @Test
    public void addAuthor(){
        publishingHouse1.addAuthor(author1);
        assertEquals(publishingHouse1, author1.getPublishingHouse());
        assertEquals(1, publishingHouse1.getAuthors().size());

        publishingHouse1.addAuthor(author2);
        assertEquals(publishingHouse1, author2.getPublishingHouse());
        assertEquals(2, publishingHouse1.getAuthors().size());

        publishingHouse1.addAuthor(null);
        assertEquals(2, publishingHouse1.getAuthors().size());
    }

    @Test
    public void removeAuthor(){
        publishingHouse1.addAuthor(author1);
        publishingHouse1.addAuthor(author2);
        assertEquals(publishingHouse1, author1.getPublishingHouse());
        assertEquals(publishingHouse1, author2.getPublishingHouse());
        assertEquals(2, publishingHouse1.getAuthors().size());

        publishingHouse1.removeAuthor(author1);
        assertNull(author1.getPublishingHouse());
        assertEquals(1, publishingHouse1.getAuthors().size());

        publishingHouse1.removeAuthor(null);
        assertEquals(1, publishingHouse1.getAuthors().size());
    }
}
