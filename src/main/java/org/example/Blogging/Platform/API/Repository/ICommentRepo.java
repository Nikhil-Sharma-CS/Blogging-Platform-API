package org.example.Blogging.Platform.API.Repository;

import org.example.Blogging.Platform.API.Model.Comment;
import org.example.Blogging.Platform.API.Model.Post;
import org.example.Blogging.Platform.API.Service.CommentService;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ICommentRepo extends JpaRepository<Comment, Integer> {
    List<Comment> findBypost(Post post);
}
