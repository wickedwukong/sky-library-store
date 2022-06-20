package com.sky.library;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BookServiceImplTest {

    @Test
    void throwExceptionWhenBooksAreNotFound() {
        BookService bookService = new BookServiceImpl(new BookRepositoryStub());

        BookNotFoundException bookNotFoundException = assertThrows(
                BookNotFoundException.class,
                () -> bookService.retrieveBook("BOOK-999"),
                "Expected retrieveBook() to throw exception because BOOK-999 does not exist, but it did not "
        );

        assertTrue(bookNotFoundException.getMessage().contains("BOOK-999"));
    }

}
