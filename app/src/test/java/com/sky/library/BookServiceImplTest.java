package com.sky.library;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BookServiceImplTest {

    @Test
    void shouldThrowExceptionWhenRetrieveBookByReferenceAndBookisNotFound() {
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
        assertEquals(bookService.retrieveBook("BOOK-GRUFF472"),
                new Book("BOOK-GRUFF472", "The Gruffalo", "A mouse taking a walk in the woods."));
    }

    @Test
    void shouldThrowErrorWhenRetrieveBookByInvalidReferencePrefix() {
        BookService bookService = new BookServiceImpl(new BookRepositoryStub());

        InvalidBookReferencePrefixException bookNotFoundException = assertThrows(
                InvalidBookReferencePrefixException.class,
                () -> bookService.retrieveBook("INVALID-TEXT"),
                "Expected retrieveBook() to throw exception because Book Reference must be prefixed with BOOK- But it did not"
        );

        assertEquals("Book reference must be prefixed by BOOK-. The invalid book reference is: INVALID-TEXT", bookNotFoundException.getMessage());
    }@Test

    void shouldThrowErrorWhenRetrieveBookByNullReference() {
        BookService bookService = new BookServiceImpl(new BookRepositoryStub());

        InvalidBookReferencePrefixException bookNotFoundException = assertThrows(
                InvalidBookReferencePrefixException.class,
                () -> bookService.retrieveBook(null),
                "Expected retrieveBook() to throw exception because Book Reference must be prefixed with BOOK- But it did not"
        );

        assertEquals("Book reference must be prefixed by BOOK-. The invalid book reference is: null", bookNotFoundException.getMessage());
    }

}
