package com.application.booker.service;

import com.application.booker.exception.BookNotFoundException;
import com.application.booker.model.AuthorDTO;
import com.application.booker.model.BookDTO;
import com.application.booker.projections.AuthorRecord;

import java.util.List;

public interface BookService {
    Integer addBook(BookDTO bookDTO);

    List<BookDTO> getAllBooks();

    List<BookDTO> getBooksByGenre(String genre);

    BookDTO updateBook(BookDTO bookDto) throws BookNotFoundException;

    BookDTO updateBookLatest(BookDTO bookDto) throws BookNotFoundException;

    void deleteBook(Integer id);

    List<String> getBookNamesByGenre(String genre);

    List<AuthorDTO> getBookDetailsDTOByGenre(String genre);

    List<AuthorRecord> getBookDetailsRecordByGenre(String genre);
}


