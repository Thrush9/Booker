package com.application.booker.serviceImpl;

import com.application.booker.entity.Book;
import com.application.booker.exception.BookNotFoundException;
import com.application.booker.model.AuthorDTO;
import com.application.booker.model.BookDTO;
import com.application.booker.projections.AuthorRecord;
import com.application.booker.repository.BookRepository;
import com.application.booker.service.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    Logger logger = LoggerFactory.getLogger(BookServiceImpl.class);

    @Override
    public Integer addBook(BookDTO bookDTO) {
        Book newBook = mapToEntity(bookDTO);
        logger.info("Adding New Book");
        newBook = bookRepository.save(newBook);
        return newBook.getId();
    }

    @Override
    public List<BookDTO> getAllBooks() {
        logger.info("Fetching All Books");
        return bookRepository.findAll().stream().map(this::mapToDTO).toList();
    }

    public List<BookDTO> getBooksByGenre(String genre) {
        logger.info("Fetching All Books by Genre :: {}", genre);
        return bookRepository.findByGenre(genre).stream().map(this::mapToDTO).toList();
    }

    @Override
    public BookDTO updateBook(BookDTO bookDto) throws BookNotFoundException {
        Book existing = bookRepository.findById(bookDto.getId())
                .orElseThrow(() -> new BookNotFoundException("Book Not Found With Id : " + bookDto.getId()));
        existing.setName(bookDto.getName());
        existing.setAuthor(bookDto.getAuthor());
        existing.setGenre(bookDto.getGenre());
        logger.info("Updating Book :: {}", bookDto.toString());
        existing = bookRepository.save(existing);
        return mapToDTO(existing);
    }

    @Override
    public BookDTO updateBookLatest(BookDTO bookDto) throws BookNotFoundException {
        Book existing = bookRepository.findById(bookDto.getId())
                .orElseThrow(() -> new BookNotFoundException("Book Not Found With Id : " + bookDto.getId()));
        existing.setName(bookDto.getName());
        existing.setAuthor(bookDto.getAuthor());
        existing.setGenre(bookDto.getGenre());
        existing = bookRepository.save(existing);
        logger.info("Updating Book :: {}", bookDto.toString());
        return mapToDTO(existing);
    }

    @Override
    public void deleteBook(Integer id) {
        logger.info("Deleting Book by Id :: {}", id);
        bookRepository.deleteById(id);
    }

    private Book mapToEntity(BookDTO bookDTO) {
        Book newBook = new Book();
        newBook.setName(bookDTO.getName());
        newBook.setAuthor(bookDTO.getAuthor());
        newBook.setGenre(bookDTO.getGenre());
        return newBook;
    }

    private BookDTO mapToDTO(Book book) {
        return new BookDTO(book.getId(), book.getName(), book.getAuthor(), book.getGenre());
    }

    public List<String> getBookNamesByGenre(String genre) {
        logger.info("Fetching All BookNames by Genre :: {}", genre);
        return bookRepository.findBookNamesByGenre(genre);
    }

    public List<AuthorDTO> getBookDetailsDTOByGenre(String genre) {
        logger.info("Fetching All BookDetailsDTO by Genre :: {}", genre);
        return bookRepository.findDetailsDTOByGenre(genre);
    }

    public List<AuthorRecord> getBookDetailsRecordByGenre(String genre) {
        logger.info("Fetching All BookDetailsRecord by Genre :: {}", genre);
        return bookRepository.findDetailsRecordByGenre(genre);
    }

}
