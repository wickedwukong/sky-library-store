package com.sky.library;

public class BookServiceImpl implements BookService {
    private BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public Book retrieveBook(String bookReference) throws BookNotFoundException {
        Book book = bookRepository.retrieveBook(bookReference);
        if (book == null)
            throw new BookNotFoundException(bookReference);

        return book;
    }

    @Override
    public String getBookSummary(String bookReference) throws BookNotFoundException {
        throw new RuntimeException("Not yet implemented");
    }
}
