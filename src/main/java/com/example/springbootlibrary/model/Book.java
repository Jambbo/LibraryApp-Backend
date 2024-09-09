package com.example.springbootlibrary.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "book")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String author;
    private String description;
    private Integer copies;
    private Integer copiesAvailable;
    private String category;
    @Column(name = "img_data_id")
    private Integer imgDataId;

    @OneToOne
    @JoinColumn(name = "imagedata_id")
    private ImageData imageData;

}
