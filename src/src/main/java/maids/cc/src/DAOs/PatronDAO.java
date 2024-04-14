package maids.cc.src.DAOs;

import maids.cc.src.DMOs.Patron;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

public class PatronDAO extends DAO<Patron> {
    public PatronDAO(JdbcTemplate jdbcTemplate) {
        super(jdbcTemplate, "PATRON", Patron.class);
    }

    // ------------------ UPDATE OPERATIONS ------------------

    @Transactional
    public boolean update(Patron patron) {
        if (patron == null) {
            throw new IllegalArgumentException("Patron to update cannot be null");
        }

        try {
            String sql = "UPDATE PATRON " +
                    "SET first_name = ?, " +
                    "last_name = ?, " +
                    "email = ? " +
                    "WHERE id = ?";

            return getJdbcTemplate().update(sql,
                    patron.getFirstName(),
                    patron.getLastName(),
                    patron.getEmail(),
                    patron.getId()) == 1;
        } catch (Exception e) {
            return false;
        }
    }
}
