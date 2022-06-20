package com.sky.library;

import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

import static com.sky.library.Book.BOOK_REFERENCE_PREFIX;

public class BookServiceImpl implements BookService {
    private BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public Book retrieveBook(String bookReference) throws BookNotFoundException, InvalidBookReferencePrefixException {
        if (bookReference == null || !bookReference.startsWith(BOOK_REFERENCE_PREFIX))
            throw new InvalidBookReferencePrefixException(bookReference);

        return retrieveBookOrError(bookReference);
    }


    @Override
    public String getBookSummary(String bookReference) throws BookNotFoundException {
        Book book = retrieveBookOrError(bookReference);

        String reviewWithPossibleEllipsis = fullReviewOrEllipsisedLongReview(book.getReview());

        return String.format("[%s] %s - %s", bookReference, book.getTitle(), reviewWithPossibleEllipsis);
    }

    private String fullReviewOrEllipsisedLongReview(String fullReview) {
        var fullBookReviewWords = fullReview.split(" ");

        if (fullBookReviewWords.length > 9) {
            List<String> firstEightWordSummary = Arrays.asList(fullBookReviewWords).subList(0, 8);

            String the9thWord = fullBookReviewWords[8];

            return String.join(" ", firstEightWordSummary) + " " + addEndingEllipsis(the9thWord);
        } else {
            return fullReview;
        }
    }

    private String addEndingEllipsis(String the9thWord) {
        String lastWordWithEllipsis;
        if (the9thWord.endsWith(",")) {
            lastWordWithEllipsis = the9thWord.replace(",", "...");
        } else {
            lastWordWithEllipsis = the9thWord + "...";
        }
        return lastWordWithEllipsis;
    }

    private Book retrieveBookOrError(String bookReference) {
        Book book = bookRepository.retrieveBook(bookReference);
        if (book == null)
            throw new BookNotFoundException(bookReference);
        return book;
    }
}
