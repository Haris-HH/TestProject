package com.example.demo.Service;

import java.util.*;
import com.example.demo.Entity.Book;

public interface BookService {

    /**
     * Insert book into database
     * @param bookList List of Book
     * @return Inserted Book
     */
    List<Book> addBook(List<Book> bookList);

    /**
     * Get all book in database
     * @return All book in database
     */
    List<Book> getAllBook();

    /**
     * Get book by using id
     * @param id id of book
     * @return Book detail
     */
    Book getBookById(Long id);

    /**
     * Update book
     * @param book Book detail
     * @return Updated book
     */
    Book updateBook(Long id, Book book);

    /**
     * Delete book by id
     * @param id id of Book
     * @return Deleted book
     */
    Book deleteBookById(Long id);
}
