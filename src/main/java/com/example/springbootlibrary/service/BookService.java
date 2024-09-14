package com.example.springbootlibrary.service;

import com.example.springbootlibrary.dao.BookRepository;
import com.example.springbootlibrary.dao.CheckoutRepository;
import com.example.springbootlibrary.model.Book;
import com.example.springbootlibrary.model.Checkout;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
@Transactional
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;
    private final CheckoutRepository checkoutRepository;

    @SneakyThrows
    public Book checkoutBook(String userEmail, Long bookId){
        Book book = bookRepository.findById(bookId).orElseThrow(RuntimeException::new);
        Checkout validateCheckout = checkoutRepository.findByUserEmailAndBookId(userEmail, bookId);

        if(validateCheckout != null || book.getCopiesAvailable()<=0){
            throw new Exception("Book doesn't exist or already checked out by user.");
        }

        book.setCopiesAvailable(book.getCopiesAvailable()-1);
        bookRepository.save(book);
        
        Checkout checkout = Checkout.builder()
                .userEmail(userEmail)
                .checkoutDate(LocalDate.now().toString())
                .returnDate(LocalDate.now().plusDays(7).toString())
                .bookId(book.getId())
                .build();
        checkoutRepository.save(checkout);
        return book;
    }

}
