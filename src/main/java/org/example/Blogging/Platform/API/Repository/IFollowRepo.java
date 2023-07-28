package org.example.Blogging.Platform.API.Repository;

import org.example.Blogging.Platform.API.Model.Follow;
import org.example.Blogging.Platform.API.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IFollowRepo extends JpaRepository<Follow, Integer> {
    Follow findByUserAndUserFollower(User user, User follower);

    Integer countByUser(User user);
}
