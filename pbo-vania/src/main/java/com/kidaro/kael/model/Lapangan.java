package com.kidaro.kael.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@Table(name = "lapangan")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Lapangan {
    @Id @GeneratedValue
    private Long id;

    private String name;
    private boolean resting;
    private LocalDate restStartDate;

    @JsonBackReference
    @ManyToOne
    private User owner;

    private int restDurationDays;
}

