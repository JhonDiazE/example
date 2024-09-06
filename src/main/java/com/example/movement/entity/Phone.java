package com.example.movement.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "phones")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Phone {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String number;
    private String citycode;
    private String countrycode;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
