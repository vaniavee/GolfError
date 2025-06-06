package com.kidaro.kael.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import jakarta.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "golf_user")
public class User {
    @Id @GeneratedValue
    private Long id;

    private String username;
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    @JsonManagedReference
    @OneToMany(mappedBy = "owner")
    private List<Lapangan> lapangans;

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<Transaction> transactions;
}

