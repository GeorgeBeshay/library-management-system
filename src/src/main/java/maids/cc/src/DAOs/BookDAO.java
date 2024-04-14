package maids.cc.src.DAOs;

import maids.cc.src.DMOs.Book;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

public class BookDAO extends DAO<Book> {
    public BookDAO(JdbcTemplate jdbcTemplate) {
        super(jdbcTemplate, "BOOK", Book.class);
    }

    // ------------------ UPDATE OPERATIONS ------------------

    @Transactional
    public boolean update(Book book) {
        if (book == null) {
            throw new IllegalArgumentException("Book to update cannot be null");
        }

        try {
            String sql = "UPDATE BOOK " +
                    "SET title = ?, " +
                    "author = ?, " +
                    "publication_year = ?, " +
                    "isbn = ? " +
                    "WHERE id = ?";

            return getJdbcTemplate().update(sql,
                    book.getTitle(),
                    book.getAuthor(),
                    book.getPublicationYear(),
                    book.getIsbn(),
                    book.getId()) == 1;
        } catch (Exception e) {
            return false;
        }
    }
}
