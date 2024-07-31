package com.example.demo.ServiceImpl;

import com.example.demo.Entity.Book;
import com.example.demo.Repository.BookRepository;
import com.example.demo.Service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BookServiceImpl implements BookService {

    private final Logger logger = LogManager.getLogger(BookServiceImpl.class);

    @Autowired
    private BookRepository bookRepository;

    /**
     * Insert book into database
     * @param bookList List of Book
     * @return Inserted Book
     */
    @Override
    @Transactional
    public List<Book> addBook(List<Book> bookList) {

        try {
            bookRepository.saveAllAndFlush(bookList);
        }
        catch (Exception ex) {
            logger.error("Error adding books: {}", ex.getMessage());
            bookList = null;
        }

        return bookList;
    }

    /**
     * Get all book in database
     * @return All book in database
     */
    @Override
    public List<Book> getAllBook() {
        logger.info("Get all books");
        List<Book> bookList = null;
        try {
            bookList = bookRepository.findAll();
        }
        catch (Exception ex) {
            logger.error("Error get all books: {}", ex.getMessage());
        }
        return bookList;
    }

    /**
     * Get book by using id
     * @param id ID of book
     * @return Book detail
     */
    @Override
    public Book getBookById(Long id) {
        logger.info("Get book by id: {}", id);
        try {
            return bookRepository.findById(id).orElse(null);
        }
        catch (Exception ex) {
            logger.error("Error book by id: {} {}", id, ex.getMessage());
            return null;
        }
    }

    /**
     * Update book
     * @param id ID of book
     * @param book Book detail
     * @return Updated book
     */
    @Override
    @Transactional
    public Book updateBook(Long id, Book book) {
        logger.info("Updating book: {}", book);
        try {
            Book rtnBook = getBookById(id);
            if (null != rtnBook) {
                rtnBook.setTitle(book.getTitle());
                rtnBook.setAuthor(book.getAuthor());
                rtnBook.setIsbn(book.getIsbn());
                rtnBook.setPublishedDate(book.getPublishedDate());
                bookRepository.saveAndFlush(rtnBook);
                return rtnBook;
            }
            else {
                logger.error("There is no book id {} in database", id);
                return null;
            }
        }
        catch (Exception ex) {
            logger.error("Error updating book by id: {} {}", id, ex.getMessage());
            return null;
        }
    }

    /**
     * Delete book by id
     * @param id id of Book
     * @return Deleted book
     */
    @Override
    public Book deleteBookById(Long id) {
        logger.info("Deleting book by id: {}", id);
        Book book = null;
        try {
            book = bookRepository.findById(id).orElse(null);
            if (book != null) {
                bookRepository.deleteById(id);
            }
        }
        catch (Exception ex) {
            logger.error("Error deleting book by id: {} {}", id, ex.getMessage());
        }
        return book;
    }
}
