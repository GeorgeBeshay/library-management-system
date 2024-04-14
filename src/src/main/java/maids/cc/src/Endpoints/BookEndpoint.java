package maids.cc.src.Endpoints;

import maids.cc.src.BusinessLogic.Services.BookServices;
import maids.cc.src.DMOs.Book;
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
@RequestMapping({"/LMS/api/books/", "/LMS/api/books"})
public class BookEndpoint {

    private final BookServices bookServices;

    @Autowired
    public BookEndpoint(JdbcTemplate jdbcTemplate) {
        this.bookServices = new BookServices(jdbcTemplate);
    }

    @GetMapping("")
    public ResponseEntity<List<Book>> getAllBooks() {
        List<Book> books = bookServices.getAllBooks();
        return new ResponseEntity<>(books, books != null ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @GetMapping("{id}")
    @ResponseBody
    public ResponseEntity<Book> getBookById(@PathVariable int id) {
        Book book = bookServices.getBookById(id);
        return new ResponseEntity<>(book, book != null ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @PostMapping("")
    @ResponseBody
    public ResponseEntity<Boolean> addBook(@RequestBody Book book) {
        Boolean creationResult = bookServices.addBook(book);
        return new ResponseEntity<>(creationResult, creationResult ? HttpStatus.CREATED : HttpStatus.BAD_REQUEST);
    }

    @PutMapping("{id}")
    @ResponseBody
    public ResponseEntity<Boolean> updateBook(@PathVariable int id, @RequestBody Book book) {
        Boolean updateResult = bookServices.updateBook(book);
        return new ResponseEntity<>(updateResult, updateResult ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Boolean> deleteBook(@PathVariable int id) {
        Boolean deleteResult = bookServices.deleteBook(id);
        return new ResponseEntity<>(deleteResult, deleteResult ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }
}
