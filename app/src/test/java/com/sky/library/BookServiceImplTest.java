package com.sky.library;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BookServiceImplTest {

    @Test
    void shouldThrowExceptionWhenRetrieveBookByReferenceAndBookIsNotFound() {
        BookService bookService = new BookServiceImpl(new BookRepositoryStub());

        BookNotFoundException bookNotFoundException = assertThrows(
                BookNotFoundException.class,
                () -> bookService.retrieveBook("BOOK-999"),
                "Expected retrieveBook() to throw exception because BOOK-999 does not exist, but it did not "
        );

        assertEquals("No book is found for book reference: BOOK-999", bookNotFoundException.getMessage());
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
    }

    @Test
    void shouldThrowErrorWhenRetrieveBookByNullReference() {
        BookService bookService = new BookServiceImpl(new BookRepositoryStub());

        InvalidBookReferencePrefixException bookNotFoundException = assertThrows(
                InvalidBookReferencePrefixException.class,
                () -> bookService.retrieveBook(null),
                "Expected retrieveBook() to throw exception because Book Reference must be prefixed with BOOK- But it did not"
        );

        assertEquals("Book reference must be prefixed by BOOK-. The invalid book reference is: null", bookNotFoundException.getMessage());
    }


    @Test
    void shouldThrowExceptionWhenGetBookSummaryByReferenceAndBookIsNotFound() {
        BookService bookService = new BookServiceImpl(new BookRepositoryStub());

        BookNotFoundException bookNotFoundException = assertThrows(
                BookNotFoundException.class,
                () -> bookService.getBookSummary("BOOK-999"),
                "Expected getBookSummary() to throw exception because BOOK-999 does not exist, but it did not "
        );

        assertEquals("No book is found for book reference: BOOK-999",
                bookNotFoundException.getMessage());
    }

    @Test
    void shouldGiveBookSummary() {
        BookService bookService = new BookServiceImpl(new BookRepositoryStub());

        assertEquals("[BOOK-GRUFF472] The Gruffalo - A mouse taking a walk in the woods.",
                bookService.getBookSummary("BOOK-GRUFF472"));
    }

    @Test
    void shouldGiveBookSummaryWithEndingEllipsisWhenBookReviewIsLongerThanNineWords() {
        BookService bookService = new BookServiceImpl(new BookRepositoryStub());

        assertEquals("[BOOK-POOH222] Winnie The Pooh - In this first volume, we meet all the friends...",
                bookService.getBookSummary("BOOK-POOH222"));

        assertEquals("[BOOK-WILL987] The Wind In The Willows - With the arrival of spring and fine weather outside...",
                bookService.getBookSummary("BOOK-WILL987"));
    }

}
