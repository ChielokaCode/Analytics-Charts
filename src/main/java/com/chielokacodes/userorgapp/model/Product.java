package com.chielokacodes.userorgapp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "products")
public class Product {
        @Id
        private String id;

        private String name;
        private String description;
        private String category;
        private double price;
        @Column(name = "image_url")
        private String imageUrl;

        @ManyToOne
        @JsonIgnore
        @JoinColumn(name = "user_id", nullable = false)
        private User user;


}
