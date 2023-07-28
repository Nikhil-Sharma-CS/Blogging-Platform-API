package org.example.Blogging.Platform.API.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Follow {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer followId;

    @ManyToOne
    @JoinColumn(name = "user_Id")
    User user;

    @ManyToOne
    @JoinColumn(name = "user_follower_Id")
    User userFollower;
}
