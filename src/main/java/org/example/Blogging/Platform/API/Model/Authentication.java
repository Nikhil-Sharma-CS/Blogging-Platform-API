package org.example.Blogging.Platform.API.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Authentication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer tokenId;
    private String tokenValue;
    private LocalDateTime tokenCreationDateTime;

    //Mapping as one user can have only one token
    @OneToOne
    @JoinColumn(name = "User_Id")
    User user;


    //A Parameterized Constructor which takes user as an argument
    public Authentication(User user)
    {
        this.user = user;
        this.tokenValue = UUID.randomUUID().toString();
        this.tokenCreationDateTime = LocalDateTime.now();
    }
}
