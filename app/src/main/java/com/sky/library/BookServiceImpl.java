package com.sky.library;

public class BookServiceImpl implements BookService {
    public BookServiceImpl(BookRepository bookRepository) {
    }

    @Override
    public Book retrieveBook(String bookReference) throws BookNotFoundException {
        throw new BookNotFoundException(bookReference);
    }

    @Override
    public String getBookSummary(String bookReference) throws BookNotFoundException {
        throw new RuntimeException("Not yet implemented");
    }
}
