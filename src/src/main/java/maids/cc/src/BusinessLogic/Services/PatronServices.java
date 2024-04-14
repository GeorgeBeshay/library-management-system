package maids.cc.src.BusinessLogic.Services;

import maids.cc.src.DAOs.PatronDAO;
import maids.cc.src.DMOs.Patron;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PatronServices {

    private final JdbcTemplate jdbcTemplate;
    private final PatronDAO patronDAO;

    public PatronServices(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.patronDAO = new PatronDAO(this.jdbcTemplate);
    }

    public List<Patron> getAllPatrons() {
        return this.patronDAO.readAll();
    }

    public Patron getPatronById(int id) {
        return this.patronDAO.readById(id).getFirst();
    }

    public Boolean addPatron(Patron patron) {
        return this.patronDAO.create(patron) >= 1;
    }

    public Boolean updatePatron(Patron patron) {
        return this.patronDAO.update(patron);
    }

    public Boolean deletePatron(int id) {
        return this.patronDAO.delete(id);
    }
}
