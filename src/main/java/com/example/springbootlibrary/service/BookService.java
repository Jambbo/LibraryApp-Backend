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
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;
    private final CheckoutRepository checkoutRepository;

    @SneakyThrows
    public Book checkoutBook(String userEmail, Long bookId){
        Optional<Book> book = bookRepository.findById(bookId);
        Checkout validateCheckout = checkoutRepository.findByUserEmailAndBookId(userEmail, bookId);

        if(book.isEmpty() || validateCheckout != null || book.get().getCopiesAvailable()<=0){
            throw new Exception("Book doesn't exist or already checked out by user.");
        }

        book.get().setCopiesAvailable(book.get().getCopiesAvailable()-1);
        bookRepository.save(book.get());

        checkoutRepository.save(
                Checkout.builder()
                .userEmail(userEmail)
                .checkoutDate(LocalDate.now().toString())
                .returnDate(LocalDate.now().plusDays(7).toString())
                .bookId(book.get().getId())
                .build()
        );

        return book.get();
    }

    public Boolean checkoutBookByUser(String userEmail, Long bookId){
        Checkout validateCheckout = checkoutRepository.findByUserEmailAndBookId(userEmail, bookId);
        return validateCheckout != null;
    }

    public int currentLoansCount(String userEmail) {
        return checkoutRepository.findBooksByUserEmail(userEmail).size();
    }

}
