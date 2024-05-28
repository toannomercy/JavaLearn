package org.example.javalt.Entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String userName;
    private String passWord;
    private String email;
    private String firstName;
    private String lastName;
    private Boolean isDeleted;
    @ManyToOne
    @JoinColumn(name = "role_id",nullable = false)
    private Role role;

    @PrePersist
    protected void onCreate() {
        if (this.isDeleted == null) {
            this.isDeleted = false;
        }
    }
}
