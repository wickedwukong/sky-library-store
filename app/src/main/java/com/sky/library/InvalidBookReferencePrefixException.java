package com.sky.library;

/*
 * Copyright © 2015 Sky plc All Rights reserved.
 * Please do not make your solution publicly available in any way e.g. post in forums or commit to GitHub.
 */

import static com.sky.library.Book.BOOK_REFERENCE_PREFIX;

public class InvalidBookReferencePrefixException extends RuntimeException {

    public InvalidBookReferencePrefixException(String invalidBookReference) {
        super(String.format("Book reference must be prefixed by %s. The invalid book reference is: %s", BOOK_REFERENCE_PREFIX, invalidBookReference));
    }
}
