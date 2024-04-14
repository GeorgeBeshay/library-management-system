package maids.cc.src.DAOs;

import maids.cc.src.DMOs.Borrowing;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class BorrowingDAO {
    private final JdbcTemplate jdbcTemplate;
    private final BeanPropertyRowMapper<Borrowing> rowMapper;

    public BorrowingDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.rowMapper = new BeanPropertyRowMapper<>(Borrowing.class);
    }

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public BeanPropertyRowMapper<Borrowing> getRowMapper() {
        return rowMapper;
    }

    // ------------------ CREATE OPERATIONS ------------------

    @Transactional
    public boolean create(Borrowing borrowing) {
        if (borrowing == null) {
            throw new IllegalArgumentException("Borrowing object to create can't be null");
        }

        try {
            SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate).withTableName("BORROWING");
            BeanPropertySqlParameterSource parameterSource = new BeanPropertySqlParameterSource(borrowing);
            return simpleJdbcInsert.execute(parameterSource) == 1;

        } catch (Exception e) {
            return false;
        }

    }

    // ------------------ READ OPERATIONS ------------------

    @Transactional
    public List<Borrowing> readAll() {
        try {
            String sql = "SELECT * FROM " + "BORROWING";
            return jdbcTemplate.query(sql, rowMapper);

        } catch (Exception e) {
            return null;
        }
    }

    @Transactional
    public List<Borrowing> readByPatronId(int id) {
        try {
            String sql = "SELECT * FROM " + "BORROWING" + " WHERE patron_id = ?";
            return jdbcTemplate.query(sql, rowMapper, id);

        } catch (Exception e) {
            return null;
        }
    }

    @Transactional
    public List<Borrowing> readByBookId(int id) {
        try {
            String sql = "SELECT * FROM " + "BORROWING" + " WHERE book_id = ?";
            return jdbcTemplate.query(sql, rowMapper, id);

        } catch (Exception e) {
            return null;
        }
    }

    @Transactional
    public List<Borrowing> readByPatronIdAndBookId(int patronId, int bookId) {
        try {
            String sql = "SELECT * FROM " + "BORROWING" + " WHERE book_id = ? AND patron_id = ?";
            return jdbcTemplate.query(sql, rowMapper, bookId, patronId);

        } catch (Exception e) {
            return null;
        }
    }

    @Transactional
    public List<Borrowing> readByStartDate(String startDate) {
        try {
            String sql = "SELECT * FROM " + "BORROWING" + " WHERE start_date = ?";
            return jdbcTemplate.query(sql, rowMapper, startDate);

        } catch (Exception e) {
            return null;
        }
    }

    @Transactional
    public List<Borrowing> readByEndDate(String endDate) {
        try {
            String sql = "SELECT * FROM " + "BORROWING" + " WHERE end_date = ?";
            return jdbcTemplate.query(sql, rowMapper, endDate);

        } catch (Exception e) {
            return null;
        }
    }

    // ------------------ UPDATE OPERATIONS ------------------

    @Transactional
    public boolean update(Borrowing borrowing) {
        if (borrowing == null) {
            throw new IllegalArgumentException("Borrowing record to update cannot be null");
        }

        try {
            String sql = "UPDATE BORROWING " +
                    "SET end_date = ? " +
                    "WHERE book_id = ? AND patron_id = ? AND start_date = ?";

            return getJdbcTemplate().update(sql,
                    borrowing.getEndDate(),
                    borrowing.getBookId(),
                    borrowing.getPatronId(),
                    borrowing.getStartDate()) == 1;
        } catch (Exception e) {
            return false;
        }
    }

    // ------------------ DELETE OPERATIONS ------------------

    @Transactional
    public boolean delete(Borrowing borrowing) {
        if (borrowing == null) {
            throw new IllegalArgumentException("Borrowing record to delete can't be null.");
        }

        try {
            String sql = "DELETE FROM " + "BORROWING" + " WHERE patron_id = ? AND book_id = ? AND start_date = ?";
            return jdbcTemplate.update(sql, borrowing.getPatronId(), borrowing.getBookId(), borrowing.getStartDate()) == 1;   // number of rows affected must be exactly 1
        } catch (Exception e) {
            return false;
        }
    }
}
