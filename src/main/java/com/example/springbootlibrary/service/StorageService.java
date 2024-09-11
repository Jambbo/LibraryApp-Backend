package com.example.springbootlibrary.service;

import com.example.springbootlibrary.dao.StorageRepository;
import com.example.springbootlibrary.model.ImageData;
import com.example.springbootlibrary.util.ImageUtils;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StorageService {

    private final StorageRepository storageRepository;

    @SneakyThrows
    public String uploadImage(MultipartFile file) {
        ImageData imageData = storageRepository.save(ImageData.builder()
                .name(file.getOriginalFilename())
                .type(file.getContentType())
                .imageData(ImageUtils.compressImage(file.getBytes())).build());
        if (imageData != null) {
            return "file uploaded successfully : " + file.getOriginalFilename();
        }
        return null;
    }
    @Transactional(readOnly = true)
    public byte[] downloadImage(Long id) {
        Optional<ImageData> dbImageData = storageRepository.findImageDataById(id);
        byte[] images = ImageUtils.decompressImage(dbImageData.get().getImageData());
        return images;
    }
}
