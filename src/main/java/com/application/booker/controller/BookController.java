package com.application.booker.controller;

import com.application.booker.exception.BookNotFoundException;
import com.application.booker.model.AuthorDTO;
import com.application.booker.model.BookDTO;
import com.application.booker.projections.AuthorRecord;
import com.application.booker.response.ErrorResponse;
import com.application.booker.serviceImpl.BookServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private BookServiceImpl bookService;

    @PostMapping("/addBook")
    public ResponseEntity<?> addBook(@RequestBody @Valid BookDTO bookDTO) {
        Integer id = bookService.addBook(bookDTO);
        return new ResponseEntity<>(id, HttpStatus.CREATED);
    }

    @GetMapping("/getBooks")
    @Operation(summary = "Get All Books", description = "Return 200")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "List of All Books", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "400", description = "Internal Error", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "500", description = "default error", content = @Content(mediaType = "application/json"))
    })
    public ResponseEntity<?> getBooks() {
        List<BookDTO> booksList = bookService.getAllBooks();
        return new ResponseEntity<>(booksList, HttpStatus.OK);
    }

    @GetMapping("/getBooks/{genre}")
    public ResponseEntity<?> getBooksByGenre(@PathVariable("genre") String genre) {
        List<BookDTO> filteredList = bookService.getBooksByGenre(genre);
        return new ResponseEntity<>(filteredList, HttpStatus.OK);
    }

    @PutMapping("/updateBook")
    public ResponseEntity<?> updateBook(@RequestBody @Valid BookDTO bookDto) {
        try {
            BookDTO updated = bookService.updateBook(bookDto);
            return new ResponseEntity<>(updated, HttpStatus.OK);
        } catch (BookNotFoundException e) {
            ErrorResponse errorResponse = new ErrorResponse(LocalDateTime.now(), e.getMessage());
            return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/updateBookLatest")
    public ResponseEntity<?> updateBookLatest(@RequestBody @Valid BookDTO bookDto) throws BookNotFoundException {
        BookDTO updated = bookService.updateBookLatest(bookDto);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    @DeleteMapping("/deleteBook/{id}")
    public ResponseEntity<?> deleteBook(@PathVariable("id") Integer id) {
        bookService.deleteBook(id);
        return new ResponseEntity<>("Deleted", HttpStatus.OK);
    }

    @ExceptionHandler(BookNotFoundException.class)
    public ResponseEntity<?> handleBookNotFoundException(BookNotFoundException exception) {
        ErrorResponse errorResponse = new ErrorResponse(LocalDateTime.now(), exception.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @GetMapping("/getBookNames/{genre}")
    public ResponseEntity<?> getBookNamesByGenre(@PathVariable("genre") String genre) {
        List<String> filteredList = bookService.getBookNamesByGenre(genre);
        return new ResponseEntity<>(filteredList, HttpStatus.OK);
    }

    @GetMapping("/getBookDetailsDTO/{genre}")
    public ResponseEntity<?> getBookDetailsDTOByGenre(@PathVariable("genre") String genre) {
        List<AuthorDTO> filteredList = bookService.getBookDetailsDTOByGenre(genre);
        return new ResponseEntity<>(filteredList, HttpStatus.OK);
    }

    @GetMapping("/getBookDetailsRecord/{genre}")
    public ResponseEntity<?> getBookDetailsRecordByGenre(@PathVariable("genre") String genre) {
        List<AuthorRecord> filteredList = bookService.getBookDetailsRecordByGenre(genre);
        return new ResponseEntity<>(filteredList, HttpStatus.OK);
    }
}
