package org.example.javalt.RequestEntities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.javalt.Entities.Role;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RequestCreateUser {
    private String userName;
    private String passWord;
    private String email;
    private String firstName;
    private String lastName;
    private Boolean isDeleted;
    private Role role;
}
