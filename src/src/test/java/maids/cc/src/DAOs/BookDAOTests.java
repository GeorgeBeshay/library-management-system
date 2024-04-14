package maids.cc.src.DAOs;

import maids.cc.src.DMOs.Book;
import maids.cc.src.SrcApplication;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest(classes = SrcApplication.class)
public class BookDAOTests {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    private BookDAO bookDAO;

    @BeforeEach
    public void instantiateBookDAO() {
        this.bookDAO = new BookDAO(jdbcTemplate);
    }

    // Helping Methods
    private Book createSampleBook() {
        Book book = new Book();
        book.setAuthor("John Doe");
        book.setTitle("The Lord of the Rings");
        book.setIsbn("1234567891234");
//        book.setPublicationYear(2000);
        return book;
    }

    @Test
    @DisplayName("Create Book - Valid")
    public void testCreateBookValid() {
        // Arrange
        Book book = createSampleBook();

        // Act
        int bookId = this.bookDAO.create(book);

        // Assert
        assertTrue(bookId >= 1);

        // Clean
        assertEquals(1, jdbcTemplate.update("DELETE FROM BOOK WHERE id = ?", bookId));
    }

    @Test
    @DisplayName("Create Book - Invalid (ISBN Format)")
    public void testCreateBookInvalidIsbn() {
        // Arrange
        Book book = createSampleBook();
        book.setIsbn("123456");

        // Act
        int bookId = this.bookDAO.create(book);

        // Assert
        assertEquals(-1, bookId);

        // Clean
        assertEquals(0, jdbcTemplate.update("DELETE FROM BOOK WHERE id = ?", bookId));
    }

    @Test
    @DisplayName("Create Book - Invalid (Author is null)")
    public void testCreateBookInvalidAuthor() {
        // Arrange
        Book book = createSampleBook();
        book.setAuthor(null);

        // Act
        int bookId = this.bookDAO.create(book);

        // Assert
        assertEquals(-1, bookId);

        // Clean
        assertEquals(0, jdbcTemplate.update("DELETE FROM BOOK WHERE id = ?", bookId));
    }

    @Test
    @DisplayName("Read Book By Id - Valid")
    public void testReadBookByIdValid() {
        // Arrange
        Book book = createSampleBook();

        // Act
        int bookId = this.bookDAO.create(book);
        book.setId(bookId);

        // Assert
        assertTrue(bookId >= 1);

        // Act
        List<Book> booksFound = this.bookDAO.readById(bookId);

        // Assert
        assertTrue(booksFound.contains(book));
        assertEquals(1, booksFound.size());

        // Clean
        assertEquals(1, jdbcTemplate.update("DELETE FROM BOOK WHERE id = ?", bookId));
    }

    @Test
    @DisplayName("Read Book By Id - Invalid")
    public void testReadBookByIdInvalid() {
        // Arrange
        // nothing

        // Act
        List<Book> booksFound = this.bookDAO.readById(-1);

        // Assert
        assertTrue(booksFound.isEmpty());
    }

    @Test
    @DisplayName("Update Book - Valid")
    public void testUpdateBookValid() {
        // Arrange
        Book book = createSampleBook();

        // Act
        int bookId = this.bookDAO.create(book);
        book.setId(bookId);

        // Assert
        assertTrue(bookId >= 1);

        // Act
        book.setAuthor("New Author");
        boolean updateResult = this.bookDAO.update(book);
        List<Book> booksFound = this.bookDAO.readById(bookId);

        // Assert
        assertTrue(updateResult);
        assertTrue(booksFound.contains(book));
        assertEquals(1, booksFound.size());

        // Clean
        assertEquals(1, jdbcTemplate.update("DELETE FROM BOOK WHERE id = ?", bookId));
    }

    @Test
    @DisplayName("Update Book - Invalid (ISBN Format)")
    public void testUpdateBookInvalidIsbn() {
        // Arrange
        Book book = createSampleBook();

        // Act
        int bookId = this.bookDAO.create(book);
        book.setId(bookId);

        // Assert
        assertTrue(bookId >= 1);

        // Act
        String oldIsbn = book.getIsbn();
        book.setIsbn("BADISBN");
        boolean updateResult = this.bookDAO.update(book);
        List<Book> booksFound = this.bookDAO.readById(bookId);
        book.setIsbn(oldIsbn);

        // Assert
        assertFalse(updateResult);
        assertTrue(booksFound.contains(book));
        assertEquals(1, booksFound.size());

        // Clean
        assertEquals(1, jdbcTemplate.update("DELETE FROM BOOK WHERE id = ?", bookId));
    }

    @Test
    @DisplayName("Delete Book - Valid")
    public void testDeleteBookValid() {
        // Arrange
        Book book = createSampleBook();

        // Act
        int bookId = this.bookDAO.create(book);
        book.setId(bookId);

        // Assert
        assertTrue(bookId >= 1);

        // Act
        boolean deleteResult = this.bookDAO.delete(bookId);
        List<Book> booksFound = this.bookDAO.readById(bookId);

        // Assert
        assertTrue(deleteResult);
        assertEquals(0, booksFound.size());

        // Clean
        assertEquals(0, jdbcTemplate.update("DELETE FROM BOOK WHERE id = ?", bookId));
    }

}
