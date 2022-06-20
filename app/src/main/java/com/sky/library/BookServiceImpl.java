package com.sky.library;

import static com.sky.library.Book.BOOK_REFERENCE_PREFIX;

public class BookServiceImpl implements BookService {
    private BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public Book retrieveBook(String bookReference) throws BookNotFoundException {
        if (bookReference == null || !bookReference.startsWith(BOOK_REFERENCE_PREFIX))
            throw new InvalidBookReferencePrefixException(bookReference);

        Book book = bookRepository.retrieveBook(bookReference);
        if (book == null)
            throw new BookNotFoundException(bookReference);

        return book;
    }

    @Override
    public String getBookSummary(String bookReference) throws BookNotFoundException {
        Book book = bookRepository.retrieveBook(bookReference);

        if (book == null)
            throw new BookNotFoundException(bookReference);

        return String.format("[%s] %s - %s", bookReference, book.getTitle(), book.getReview());
    }
}
