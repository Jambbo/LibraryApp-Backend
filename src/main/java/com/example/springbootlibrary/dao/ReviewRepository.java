package com.example.springbootlibrary.dao;

import com.example.springbootlibrary.model.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.RequestParam;

public interface ReviewRepository extends JpaRepository<Review,Integer> {


    Page<Review> findByBookId(
                            @RequestParam("book_id")
                            Long bookId,
                            Pageable pageable
        );

}
