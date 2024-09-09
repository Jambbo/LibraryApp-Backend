package com.example.springbootlibrary.dao;

import com.example.springbootlibrary.model.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;


public interface BookRepository extends JpaRepository<Book, Long> {

    @Query("SELECT b FROM Book b WHERE LOWER(b.title) LIKE LOWER(CONCAT('%', :title, '%'))")
    Page<Book> findByTitleContaining(@RequestParam("title") String title, Pageable pageable);

    @Query("SELECT b FROM Book b WHERE LOWER(b.category) LIKE LOWER(CONCAT('%', :category, '%'))")
    Page<Book> findByCategory(@RequestParam("category") String category, Pageable pageable);



}
