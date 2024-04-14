package maids.cc.src.BusinessLogic.Services;

import maids.cc.src.DAOs.BookDAO;
import maids.cc.src.DMOs.Book;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServices {

    private final JdbcTemplate jdbcTemplate;
    private final BookDAO bookDAO;

    public BookServices(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.bookDAO = new BookDAO(this.jdbcTemplate);
    }

    public List<Book> getAllBooks() {
        return this.bookDAO.readAll();
    }

    public Book getBookById(int id) {
        return this.bookDAO.readById(id).getFirst();
    }

    public Boolean addBook(Book book) {
        return this.bookDAO.create(book) >= 1;
    }

    public Boolean updateBook(Book book) {
        return this.bookDAO.update(book);
    }

    public Boolean deleteBook(int id) {
        return this.bookDAO.delete(id);
    }
}
