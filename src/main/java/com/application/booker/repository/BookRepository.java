package com.application.booker.repository;

import com.application.booker.entity.Book;
import com.application.booker.model.AuthorDTO;
import com.application.booker.projections.AuthorRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {

    List<Book> findByGenre(String genre);

    @Query(nativeQuery = true, value = "Select name from books where genre =:genre")
    List<String> findBookNamesByGenre(@Param("genre") String genre);

    @Query(nativeQuery = true, value = "Select author,name from books where genre =:genre")
    List<AuthorDTO> findDetailsDTOByGenre(@Param("genre") String genre);

    @Query("SELECT author,name FROM Book WHERE genre =:genre")
    List<AuthorRecord> findDetailsRecordByGenre(@Param("genre") String genre);
}
