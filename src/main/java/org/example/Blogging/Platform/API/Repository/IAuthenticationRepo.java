package org.example.Blogging.Platform.API.Repository;

import org.example.Blogging.Platform.API.Model.Authentication;
import org.example.Blogging.Platform.API.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAuthenticationRepo extends JpaRepository<Authentication, Integer> {
    Authentication findFirstBytokenValue(String token);

    Authentication findFirstByUser(User user);
}
