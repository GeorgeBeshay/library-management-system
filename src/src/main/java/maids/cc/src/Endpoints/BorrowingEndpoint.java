package maids.cc.src.Endpoints;

import maids.cc.src.BusinessLogic.Services.BorrowingServices;
import maids.cc.src.DMOs.Borrowing;
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
@RequestMapping({"/LMS/api/"})
public class BorrowingEndpoint {

    private final BorrowingServices borrowingServices;

    @Autowired
    public BorrowingEndpoint(JdbcTemplate jdbcTemplate) {
        this.borrowingServices = new BorrowingServices(jdbcTemplate);
    }

    @GetMapping("borrowings")
    public ResponseEntity<List<Borrowing>> getBorrowings() {
        List<Borrowing> borrowings = this.borrowingServices.getAllBorrowings();
        return new ResponseEntity<>(borrowings, borrowings != null ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @PostMapping("borrow/{bookId}/patrons/{patronId}")
    public ResponseEntity<Boolean> borrowBook(@PathVariable int bookId, @PathVariable int patronId) {
        Boolean operationResult = this.borrowingServices.addBorrowing(bookId, patronId);
        return new ResponseEntity<>(operationResult, operationResult ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
    }

    @PutMapping("return/{bookId}/patrons/{patronId}")
    public ResponseEntity<Boolean> returnBook(@PathVariable int bookId, @PathVariable int patronId) {
        Boolean operationResult = this.borrowingServices.updateBorrowing(bookId, patronId);
        return new ResponseEntity<>(operationResult, operationResult ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
    }


}
