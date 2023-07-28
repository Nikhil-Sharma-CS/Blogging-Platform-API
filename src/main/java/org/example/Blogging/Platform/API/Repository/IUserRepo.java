package org.example.Blogging.Platform.API.Repository;

import org.example.Blogging.Platform.API.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepo extends JpaRepository<User, Integer> {
    User findFirstByUserEmail(String newEmail);
}
