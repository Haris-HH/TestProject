package com.example.demo.Controller;

import com.example.demo.DemoApplication;
import com.example.demo.Entity.Book;
import com.example.demo.Service.BookService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BookController {

    @Autowired
    private BookService bookService;

    private final Logger logger = LogManager.getLogger(BookController.class);

    @PostMapping("/books")
    public List<Book> addingNewBook(@RequestBody List<Book> book) {
        List<Book> rtnBookList = bookService.addBook(book);
        if (null != rtnBookList && !rtnBookList.isEmpty()) {
            logger.info("Add new book completed");
        }
        return rtnBookList;
    }

    @GetMapping("/books")
    public List<Book> getAllBook() {
        List<Book> bookList = bookService.getAllBook();
        if (null != bookList && !bookList.isEmpty()) {
            logger.info("Get All book completed");
        }
        else {
            logger.error("There is no any book");
        }
        return bookList;
    }

    @GetMapping("/books/{id}")
    public Book getBookById(@PathVariable Long id) {
        Book book = bookService.getBookById(id);
        if (null != book) {
            logger.info("Get book id {} completed", id);
        }
        else {
            logger.error("There is no book id {} in database", id);
        }
        return book;
    }

    @PutMapping("/books/{id}")
    public Book updateBook(@PathVariable Long id, @RequestBody Book book) {
        Book rtnBook = bookService.updateBook(id, book);
        if (null != rtnBook) {
            logger.info("Updated book id {} completed", id);
        }
        else {
            logger.error("There is no book id {} in database", id);
        }
        return rtnBook;
    }

    @DeleteMapping("/books/{id}")
    public Book deleteBookById(@PathVariable Long id) {
        Book rtnBook = bookService.deleteBookById(id);
        if (null != rtnBook) {
            logger.info("Delete book id {} completed", id);
        }
        else {
            logger.error("There is no book id {} in database", id);
        }
        return rtnBook;
    }
}
