package com.example.demo.Controller;

import com.example.demo.Entity.Book;
import com.example.demo.Service.BookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.when;

@SpringBootTest
class BookControllerTest {

    @Mock
    private BookService bookService;

    @InjectMocks
    private BookController bookController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddNewBook_returnOK() {

        Book book = new Book();
        book.setTitle("Test Title");
        book.setAuthor("Test Author");
        book.setIsbn("Test Isbn");
        LocalDate localDate = LocalDate.parse("1999-12-12");
        book.setPublishedDate(localDate);

        // Add book to list
        List<Book> bookList = new ArrayList<>();
        bookList.add(book);

        // Setting condition
        when(bookService.addBook(bookList)).thenReturn(bookList);

        // Test
        assertEquals(bookList, bookController.addingNewBook(bookList));
    }

    @Test
    public void testAddNewBook_returnNull() {
        Book book = new Book();
        book.setTitle("Test Title");
        book.setAuthor("Test Author");
        book.setIsbn("Test Isbn");
        LocalDate localDate = LocalDate.parse("1999-12-12");
        book.setPublishedDate(localDate);

        // Add book to list
        List<Book> bookList = new ArrayList<>();
        bookList.add(book);

        // Setting condition
        when(bookService.addBook(anyList())).thenReturn(null);

        // Test
        assertNull(bookController.addingNewBook(bookList));
    }

    @Test
    public void testAddNewBook_returnEmptyList() {
        Book book = new Book();
        book.setTitle("Test Title");
        book.setAuthor("Test Author");
        book.setIsbn("Test Isbn");
        LocalDate localDate = LocalDate.parse("1999-12-12");
        book.setPublishedDate(localDate);

        // Add book to list
        List<Book> bookList = new ArrayList<>();
        bookList.add(book);

        // Setting condition
        when(bookService.addBook(bookList)).thenReturn(new ArrayList<>());

        // Test
        assertEquals(new ArrayList<>(), bookController.addingNewBook(bookList));
    }

    @Test
    public void testGetALLBook_returnOK() {

        Book book = new Book();
        book.setId(1L);
        book.setTitle("Test Title");
        book.setAuthor("Test Author");
        book.setIsbn("Test Isbn");
        LocalDate localDate = LocalDate.parse("1999-12-12");
        book.setPublishedDate(localDate);

        // Add book to list
        List<Book> bookList = new ArrayList<>();
        bookList.add(book);

        // Setting condition
        when(bookService.getAllBook()).thenReturn(bookList);

        // Test
        assertEquals(bookList, bookController.getAllBook());
    }

    @Test
    public void testGetALLBook_returnNull() {

        // Setting condition
        when(bookService.getAllBook()).thenReturn(null);

        // Test
        assertNull(bookController.getAllBook());
    }

    @Test
    public void testGetALLBook_returnEmptyList() {

        // Setting condition
        when(bookService.getAllBook()).thenReturn(new ArrayList<>());

        // Test
        assertEquals(new ArrayList<>(), bookController.getAllBook());
    }

    @Test
    public void testGetBookById_returnOK() {

        Book book = new Book();
        book.setId(1L);
        book.setTitle("Test Title");
        book.setAuthor("Test Author");
        book.setIsbn("Test Isbn");
        LocalDate localDate = LocalDate.parse("1999-12-12");
        book.setPublishedDate(localDate);

        // Setting condition
        when(bookService.getBookById(1L)).thenReturn(book);

        // Test
        assertEquals(book, bookController.getBookById(1L));
    }

    @Test
    public void testGetBookById_returnNull() {

        Book book = new Book();
        book.setId(1L);
        book.setTitle("Test Title");
        book.setAuthor("Test Author");
        book.setIsbn("Test Isbn");
        LocalDate localDate = LocalDate.parse("1999-12-12");
        book.setPublishedDate(localDate);

        // Setting condition
        when(bookService.getBookById(1L)).thenReturn(null);

        // Test
        assertNull(bookController.getBookById(1L));
    }

    @Test
    public void testUpdateBook_returnOK() {

        Book book = new Book();
        book.setId(1L);
        book.setTitle("Test Title1");
        book.setAuthor("Test Author");
        book.setIsbn("Test Isbn");
        LocalDate localDate = LocalDate.parse("1999-12-12");
        book.setPublishedDate(localDate);

        // Setting condition
        when(bookService.updateBook(1L, book)).thenReturn(book);

        // Test
        assertEquals(book, bookController.updateBook(1L, book));
    }

    @Test
    public void testUpdateBook_returnNull() {

        Book book = new Book();
        book.setId(2L);
        book.setTitle("Test Title1");
        book.setAuthor("Test Author");
        book.setIsbn("Test Isbn");
        LocalDate localDate = LocalDate.parse("1999-12-12");
        book.setPublishedDate(localDate);

        // Setting condition
        when(bookService.updateBook(1L, book)).thenReturn(null);

        // Test
        assertNull(bookController.updateBook(1L, book));
    }

    @Test
    public void testDeleteBookById_returnOK() {

        Book book = new Book();
        book.setId(1L);
        book.setTitle("Test Title1");
        book.setAuthor("Test Author");
        book.setIsbn("Test Isbn");
        LocalDate localDate = LocalDate.parse("1999-12-12");
        book.setPublishedDate(localDate);

        // Setting condition
        when(bookService.deleteBookById(1L)).thenReturn(book);

        // Test
        assertEquals(book, bookController.deleteBookById(1L));
    }

    @Test
    public void testDeleteBookById_returnNull() {

        Book book = new Book();
        book.setId(2L);
        book.setTitle("Test Title1");
        book.setAuthor("Test Author");
        book.setIsbn("Test Isbn");
        LocalDate localDate = LocalDate.parse("1999-12-12");
        book.setPublishedDate(localDate);

        // Setting condition
        when(bookService.deleteBookById(1L)).thenReturn(null);

        // Test
        assertNull(bookController.deleteBookById(1L));
    }
}