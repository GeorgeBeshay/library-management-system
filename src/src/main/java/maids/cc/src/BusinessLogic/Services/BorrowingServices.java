package maids.cc.src.BusinessLogic.Services;

import maids.cc.src.DAOs.BorrowingDAO;
import maids.cc.src.DMOs.Borrowing;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class BorrowingServices {

    private final JdbcTemplate jdbcTemplate;
    private final BorrowingDAO borrowingDAO;

    public BorrowingServices(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.borrowingDAO = new BorrowingDAO(jdbcTemplate);
    }

    public List<Borrowing> getAllBorrowings() {
        return this.borrowingDAO.readAll();
    }

    public Borrowing getBorrowingByPatronId(int patronId) {
        return this.borrowingDAO.readByPatronId(patronId).getFirst();
    }

    public Borrowing getBorrowingByBookId(int bookId) {
        return this.borrowingDAO.readByBookId(bookId).getFirst();
    }

    public Borrowing getBorrowingByStartDate(String startDate) {
        return this.borrowingDAO.readByStartDate(startDate).getFirst();
    }

    public Boolean addBorrowing(int bookId, int patronId) {
        Borrowing borrowing = new Borrowing();
        borrowing.setBookId(bookId);
        borrowing.setPatronId(patronId);
        borrowing.setStartDate(getCurrentDate());
        return this.borrowingDAO.create(borrowing);
    }

    public Boolean updateBorrowing(int bookId, int patronId) {
        List<Borrowing> borrowings = this.borrowingDAO.readByPatronIdAndBookId(patronId, bookId);
        int borrowingRecord = -1;
        for (int i = 0; i < borrowings.size(); i++) {
            Borrowing borrowing = borrowings.get(i);
            if (borrowing.getEndDate() == null) {
                borrowingRecord = i;
            }
        }
        Borrowing borrowing = borrowings.get(borrowingRecord);
        borrowing.setEndDate(getCurrentDate());
        return this.borrowingDAO.update(borrowing);
    }

    // Helping Methods
    private String getCurrentDate() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return now.format(formatter);
    }
}
