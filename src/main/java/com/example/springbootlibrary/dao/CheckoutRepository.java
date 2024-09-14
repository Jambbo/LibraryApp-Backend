package com.example.springbootlibrary.dao;

import com.example.springbootlibrary.model.Checkout;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CheckoutRepository extends JpaRepository<Checkout,Long> {

    Checkout findByUserEmailAndBookId(String userEmail,Long bookId);

}
