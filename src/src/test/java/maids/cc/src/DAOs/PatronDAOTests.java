package maids.cc.src.DAOs;

import maids.cc.src.DMOs.Patron;
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
import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest(classes = SrcApplication.class)
public class PatronDAOTests {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    private PatronDAO patronDAO;

    @BeforeEach
    public void instantiateBookDAO() {
        this.patronDAO = new PatronDAO(jdbcTemplate);
    }

    // Helping Methods
    private Patron createSamplePatron() {
        Patron patron = new Patron();
        patron.setEmail("test@test.com");
        patron.setFirstName("Test");
        patron.setLastName("Test");
        return patron;
    }

    @Test
    @DisplayName("Create Patron - Valid")
    public void testCreatePatronValid() {
        // Arrange
        Patron patron = createSamplePatron();

        // Act
        int patronId = this.patronDAO.create(patron);

        // Assert
        assertTrue(patronId >= 1);

        // Clean
        assertEquals(1, jdbcTemplate.update("DELETE FROM PATRON WHERE id = ?", patronId));
    }

    @Test
    @DisplayName("Create Patron - Invalid (Email is null)")
    public void testCreatePatronInvalidEmail() {
        // Arrange
        Patron patron = createSamplePatron();
        patron.setEmail(null);

        // Act
        int patronId = this.patronDAO.create(patron);

        // Assert
        assertEquals(-1, patronId);

        // Clean
        assertEquals(0, jdbcTemplate.update("DELETE FROM PATRON WHERE id = ?", patronId));
    }

    @Test
    @DisplayName("Create Patron - Invalid (Lastname is null)")
    public void testCreatePatronInvalidLastName() {
        // Arrange
        Patron patron = createSamplePatron();
        patron.setLastName(null);

        // Act
        int patronId = this.patronDAO.create(patron);

        // Assert
        assertEquals(-1, patronId);

        // Clean
        assertEquals(0, jdbcTemplate.update("DELETE FROM PATRON WHERE id = ?", patronId));
    }

    @Test
    @DisplayName("Read Patron By Id - Valid")
    public void testReadPatronByIdValid() {
        // Arrange
        Patron patron = createSamplePatron();

        // Act
        int patronId = this.patronDAO.create(patron);
        patron.setId(patronId);

        // Assert
        assertTrue(patronId >= 1);

        // Act
        List<Patron> patronsFound = this.patronDAO.readById(patronId);

        // Assert
        assertTrue(patronsFound.contains(patron));
        assertEquals(1, patronsFound.size());

        // Clean
        assertEquals(1, jdbcTemplate.update("DELETE FROM PATRON WHERE id = ?", patronId));
    }

    @Test
    @DisplayName("Read Patron By Id - Invalid")
    public void testReadPatronByIdInvalid() {
        // Arrange
        // nothing

        // Act
        List<Patron> patronsFound = this.patronDAO.readById(-1);

        // Assert
        assertTrue(patronsFound.isEmpty());
    }

    @Test
    @DisplayName("Update Patron - Valid")
    public void testUpdatePatronValid() {
        // Arrange
        Patron patron = createSamplePatron();

        // Act
        int patronId = this.patronDAO.create(patron);
        patron.setId(patronId);

        // Assert
        assertTrue(patronId >= 1);

        // Act
        patron.setLastName("New Last Name");
        boolean updateResult = this.patronDAO.update(patron);
        List<Patron> patronsFound = this.patronDAO.readById(patronId);

        // Assert
        assertTrue(updateResult);
        assertTrue(patronsFound.contains(patron));
        assertEquals(1, patronsFound.size());

        // Clean
        assertEquals(1, jdbcTemplate.update("DELETE FROM PATRON WHERE id = ?", patronId));
    }

    @Test
    @DisplayName("Update Patron - Invalid (Email Format)")
    public void testUpdatePatronInvalidIsbn() {
        // Arrange
        Patron patron = createSamplePatron();

        // Act
        int patronId = this.patronDAO.create(patron);
        patron.setId(patronId);

        // Assert
        assertTrue(patronId >= 1);

        // Act
        String oldEmail = patron.getEmail();
        patron.setEmail("BADEMAIL");
        boolean updateResult = this.patronDAO.update(patron);
        List<Patron> patronsFound = this.patronDAO.readById(patronId);
        patron.setEmail(oldEmail);

        // Assert
        assertFalse(updateResult);
        assertTrue(patronsFound.contains(patron));
        assertEquals(1, patronsFound.size());

        // Clean
        assertEquals(1, jdbcTemplate.update("DELETE FROM PATRON WHERE id = ?", patronId));
    }

    @Test
    @DisplayName("Delete Patron - Valid")
    public void testDeletePatronValid() {
        // Arrange
        Patron patron = createSamplePatron();

        // Act
        int patronId = this.patronDAO.create(patron);
        patron.setId(patronId);

        // Assert
        assertTrue(patronId >= 1);

        // Act
        boolean deleteResult = this.patronDAO.delete(patronId);
        List<Patron> patronsFound = this.patronDAO.readById(patronId);

        // Assert
        assertTrue(deleteResult);
        assertEquals(0, patronsFound.size());

        // Clean
        assertEquals(0, jdbcTemplate.update("DELETE FROM PATRON WHERE id = ?", patronId));
    }

}
