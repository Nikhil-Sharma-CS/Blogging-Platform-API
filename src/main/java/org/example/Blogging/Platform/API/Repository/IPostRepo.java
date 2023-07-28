package org.example.Blogging.Platform.API.Repository;

import org.example.Blogging.Platform.API.Model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPostRepo extends JpaRepository<Post, Integer> {
}
