package maids.cc.src.Endpoints;

import maids.cc.src.BusinessLogic.Services.PatronServices;
import maids.cc.src.DMOs.Patron;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@ComponentScan(basePackages = {"maids.cc.src"})
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping({"/LMS/api/patrons/", "/LMS/api/patrons"})
public class PatronEndpoint {

    private final PatronServices patronServices;

    @Autowired
    public PatronEndpoint(JdbcTemplate jdbcTemplate) {
        this.patronServices = new PatronServices(jdbcTemplate);
    }

    @GetMapping("")
    public ResponseEntity<List<Patron>> getAllPatrons() {
        List<Patron> patrons = patronServices.getAllPatrons();
        return new ResponseEntity<>(patrons, patrons != null ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @GetMapping("{id}")
    @ResponseBody
    public ResponseEntity<Patron> getPatronById(@PathVariable int id) {
        Patron patron = patronServices.getPatronById(id);
        return new ResponseEntity<>(patron, patron != null ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @PostMapping("")
    @ResponseBody
    public ResponseEntity<Boolean> addPatron(@RequestBody Patron patron) {
        Boolean creationResult = patronServices.addPatron(patron);
        return new ResponseEntity<>(creationResult, creationResult ? HttpStatus.CREATED : HttpStatus.BAD_REQUEST);
    }

    @PutMapping("{id}")
    @ResponseBody
    public ResponseEntity<Boolean> updatePatron(@PathVariable int id, @RequestBody Patron patron) {
        Boolean updateResult = patronServices.updatePatron(patron);
        return new ResponseEntity<>(updateResult, updateResult ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Boolean> deletePatron(@PathVariable int id) {
        Boolean deleteResult = patronServices.deletePatron(id);
        return new ResponseEntity<>(deleteResult, deleteResult ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

}
