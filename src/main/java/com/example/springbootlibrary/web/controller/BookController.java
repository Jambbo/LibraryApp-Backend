package com.example.springbootlibrary.web.controller;

import com.example.springbootlibrary.model.Book;
import com.example.springbootlibrary.service.BookService;
import com.example.springbootlibrary.web.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("http://localhost:3000") // app on port 3000 will be able to call this controller without getting a cors error
@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;
    private final JwtTokenProvider jwtTokenProvider;

    @PutMapping("/secure/checkout")
    @SneakyThrows
    public Book checkoutBook(
                             @RequestHeader(name = HttpHeaders.AUTHORIZATION) String authorizationHeader,
                             @RequestParam Long bookId)
    {
        String token = authorizationHeader.substring(7);
        String userEmail = jwtTokenProvider.getUsername(token);
        return bookService.checkoutBook(userEmail, bookId);
    }

    @GetMapping("/secure/ischeckedout/byuser")
    public Boolean checkoutBookByUser(
                                        @RequestHeader(name = HttpHeaders.AUTHORIZATION) String authorizationHeader,
                                        @RequestParam Long bookId)
    {
        String token = authorizationHeader.substring(7);
        String userEmail = jwtTokenProvider.getUsername(token);
        return bookService.checkoutBookByUser(userEmail, bookId);
    }

    @GetMapping("/secure/currentloans/count")
    public int currentLoansCount(@RequestHeader(name = HttpHeaders.AUTHORIZATION) String authorizationHeader){
        String token = authorizationHeader.substring(7);
        String userEmail = jwtTokenProvider.getUsername(token);
        return bookService.currentLoansCount(userEmail);
    }



}
