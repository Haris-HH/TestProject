package com.example.demo.ServiceImpl;

import com.example.demo.Entity.Book;
import com.example.demo.Repository.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class BookServiceImplTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookServiceImpl bookServiceImpl;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddBook_returnOK() {

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
        doReturn(bookList).when(bookRepository).saveAllAndFlush(bookList);

        // Test
        assertEquals(bookList, bookServiceImpl.addBook(bookList));
    }

    @Test
    public void testAddBook_returnException() {

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
        doThrow(new RuntimeException("Connection Error")).when(bookRepository).saveAllAndFlush(bookList);

        RuntimeException exceptionMessage = assertThrows(RuntimeException.class, () -> {
                bookRepository.saveAllAndFlush(bookList);
        });

        // Test
        assertNull(bookServiceImpl.addBook(bookList));
        assertEquals("Connection Error", exceptionMessage.getMessage());
    }

    @Test
    public void testGetAllBook_returnOK() {

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
        doReturn(bookList).when(bookRepository).findAll();

        // Test
        assertEquals(bookList, bookServiceImpl.getAllBook());
    }

    @Test
    public void testGetAllBook_returnException() {

        // Setting condition
        doThrow(new RuntimeException("Connection Error")).when(bookRepository).findAll();

        RuntimeException exceptionMessage = assertThrows(RuntimeException.class, () -> {
            bookRepository.findAll();
        });

        // Test
        assertNull(bookServiceImpl.getAllBook());
        assertEquals("Connection Error", exceptionMessage.getMessage());
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

        Optional<Book> optBook = Optional.of(book);

        // Setting condition
        doReturn(optBook).when(bookRepository).findById(book.getId());

        // Test
        assertEquals(book, bookServiceImpl.getBookById(book.getId()));
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
        doReturn(null).when(bookRepository).findById(book.getId());

        // Test
        assertNull(bookServiceImpl.getBookById(book.getId()));
    }

    @Test
    public void testGetBookById_returnException() {

        Book book = new Book();
        book.setId(1L);
        book.setTitle("Test Title");
        book.setAuthor("Test Author");
        book.setIsbn("Test Isbn");
        LocalDate localDate = LocalDate.parse("1999-12-12");
        book.setPublishedDate(localDate);

        // Setting condition
        doThrow(new RuntimeException("Connection Error")).when(bookRepository).findById(book.getId());

        RuntimeException exceptionMessage = assertThrows(RuntimeException.class, () -> {
            bookRepository.findById(book.getId());
        });

        // Test
        assertNull(bookServiceImpl.getBookById(book.getId()));
        assertEquals("Connection Error", exceptionMessage.getMessage());
    }

    @Test
    public void testUpdateBook_returnOK() {

        Book book = new Book();
        book.setId(1L);
        book.setTitle("Test Title");
        book.setAuthor("Test Author");
        book.setIsbn("Test Isbn");
        LocalDate localDate = LocalDate.parse("1999-12-12");
        book.setPublishedDate(localDate);

        Optional<Book> optBook = Optional.of(book);

        // Setting condition
        doReturn(optBook).when(bookRepository).findById(book.getId());
        doReturn(book).when(bookRepository).saveAndFlush(book);

        // Test
        assertEquals(book, bookServiceImpl.updateBook(book.getId(), book));
    }

    @Test
    public void testUpdateBook_returnNull() {

        Book book = new Book();
        book.setId(1L);
        book.setTitle("Test Title");
        book.setAuthor("Test Author");
        book.setIsbn("Test Isbn");
        LocalDate localDate = LocalDate.parse("1999-12-12");
        book.setPublishedDate(localDate);

        // Setting condition
        doReturn(null).when(bookRepository).findById(book.getId());
        when(bookServiceImpl.getBookById(1L)).thenReturn(null);

        // Test
        assertNull(bookServiceImpl.updateBook(book.getId(), book));
    }

    @Test
    public void testUpdateBook_returnException() {

        Long id = 1L;
        Book bookToUpdate = new Book();
        bookToUpdate.setId(id);
        bookToUpdate.setTitle("Updated Title");
        bookToUpdate.setAuthor("Updated Author");
        bookToUpdate.setIsbn("Updated ISBN");
        bookToUpdate.setPublishedDate(LocalDate.now());

        Book existingBook = new Book();
        existingBook.setId(id);
        existingBook.setTitle("Existing Title");
        existingBook.setAuthor("Existing Author");
        existingBook.setIsbn("Existing ISBN");
        existingBook.setPublishedDate(LocalDate.now());

        Optional<Book> optBook = Optional.of(existingBook);

        // Setting condition
        doReturn(optBook).when(bookRepository).findById(id);
        doThrow(new RuntimeException("Database error")).when(bookRepository).saveAndFlush(any(Book.class));

        // Test
        assertNull(bookServiceImpl.updateBook(id, bookToUpdate));
    }

    @Test
    public void testDeleteBookById_returnOK() {

        Book book = new Book();
        book.setId(1L);
        book.setTitle("Test Title");
        book.setAuthor("Test Author");
        book.setIsbn("Test Isbn");
        LocalDate localDate = LocalDate.parse("1999-12-12");
        book.setPublishedDate(localDate);

        Optional<Book> optBook = Optional.of(book);

        // Setting condition
        doReturn(optBook).when(bookRepository).findById(book.getId());

        // Test
        assertEquals(book, bookServiceImpl.deleteBookById(book.getId()));
    }

    @Test
    public void testDeleteBookById_returnNull() {

        Book book = new Book();
        book.setId(1L);
        book.setTitle("Test Title");
        book.setAuthor("Test Author");
        book.setIsbn("Test Isbn");
        LocalDate localDate = LocalDate.parse("1999-12-12");
        book.setPublishedDate(localDate);

        // Setting condition
        doReturn(Optional.empty()).when(bookRepository).findById(book.getId());

        // Test
        assertNull(bookServiceImpl.deleteBookById(book.getId()));
    }

    @Test
    public void testDeleteBookById_returnException() {

        Book book = new Book();
        book.setId(1L);
        book.setTitle("Test Title");
        book.setAuthor("Test Author");
        book.setIsbn("Test Isbn");
        LocalDate localDate = LocalDate.parse("1999-12-12");
        book.setPublishedDate(localDate);

        // Setting condition
        doThrow(new RuntimeException("Connection Error")).when(bookRepository).findById(book.getId());

        RuntimeException exceptionMessage = assertThrows(RuntimeException.class, () -> {
            bookRepository.findById(book.getId());
        });

        // Test
        assertNull(bookServiceImpl.deleteBookById(book.getId()));
        assertEquals("Connection Error", exceptionMessage.getMessage());
    }
}