package com.sky.library;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BookServiceImplTest {

    @Test
    void throwExceptionWhenBookAreNotFound() {
        BookService bookService = new BookServiceImpl(new BookRepositoryStub());

        BookNotFoundException bookNotFoundException = assertThrows(
                BookNotFoundException.class,
                () -> bookService.retrieveBook("BOOK-999"),
                "Expected retrieveBook() to throw exception because BOOK-999 does not exist, but it did not "
        );

        assertTrue(bookNotFoundException.getMessage().contains("BOOK-999"));
    }

    @Test
    void shouldRetrieveBookByReference() {
        BookService bookService = new BookServiceImpl(new BookRepositoryStub());
        try {
            assertEquals(bookService.retrieveBook("BOOK-GRUFF472"),
                    new Book("BOOK-GRUFF472", "The Gruffalo", "A mouse taking a walk in the woods."));
        } catch (BookNotFoundException e) {
            fail("should not have failed", e);
        }
    }

}
