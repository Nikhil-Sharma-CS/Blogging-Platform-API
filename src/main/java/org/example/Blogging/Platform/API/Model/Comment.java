package org.example.Blogging.Platform.API.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer commentId;

    private String commentData;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY) // hide this in json but not in database table column
    private LocalDateTime commentCreationTime;

    @ManyToOne
    @JoinColumn(name = "post_Id")
    private Post post;

    @ManyToOne
    @JoinColumn(name = "Commenter_Id")
    @JsonIgnore
    private User commenter;
}
