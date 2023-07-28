package org.example.Blogging.Platform.API.Service;

import org.example.Blogging.Platform.API.Model.Authentication;
import org.example.Blogging.Platform.API.Model.User;
import org.example.Blogging.Platform.API.Repository.IAuthenticationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    
    @Autowired
    IAuthenticationRepo authenticationRepo;

    public boolean authenticate(String email, String token) {
        Authentication Token = authenticationRepo.findFirstBytokenValue(token);

        if(Token == null)
        {
            return false;
        }

        String tokenConnectedEmail = Token.getUser().getUserEmail();

        return tokenConnectedEmail.equals(email);
    }

    public void saveAuthToken(Authentication Token) {
        authenticationRepo.save(Token);
    }

    public Authentication findFirstByUser(User user) {
        return authenticationRepo.findFirstByUser(user);
    }

    public void removeToken(Authentication token) {
        authenticationRepo.delete(token);
    }
}
