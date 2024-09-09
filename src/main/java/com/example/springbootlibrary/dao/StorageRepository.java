package com.example.springbootlibrary.dao;

import com.example.springbootlibrary.model.ImageData;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface StorageRepository extends JpaRepository<ImageData, Long> {

    Optional<ImageData> findImageDataById(Long id);
}
