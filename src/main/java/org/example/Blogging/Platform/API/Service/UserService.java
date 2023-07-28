package org.example.Blogging.Platform.API.Service;

import org.example.Blogging.Platform.API.Model.Authentication;
import org.example.Blogging.Platform.API.Model.Dto.SignInInput;
import org.example.Blogging.Platform.API.Model.Dto.SignUpOutput;
import org.example.Blogging.Platform.API.Model.User;
import org.example.Blogging.Platform.API.Repository.IUserRepo;
import org.example.Blogging.Platform.API.Service.Hashing.PasswordEncrypter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    
    @Autowired
    IUserRepo userRepo;

    @Autowired
    AuthenticationService authenticationService;
    public SignUpOutput userSignUp(User user) {

        boolean signUpStatus = true;
        String signUpStatusMessage = null;

        String newEmail = user.getUserEmail();

        if(newEmail == null)
        {
            signUpStatusMessage = "Invalid email";
            signUpStatus = false;
            return new SignUpOutput(signUpStatus,signUpStatusMessage);
        }

        //Check if this user email already exists!
        User existingUser = userRepo.findFirstByUserEmail(newEmail);

        if(existingUser != null)
        {
            signUpStatusMessage = "Email already registered!!!";
            signUpStatus = false;
            return new SignUpOutput(signUpStatus,signUpStatusMessage);
        }

        //Encrypt the password
        try {
            String encryptedPassword = PasswordEncrypter.encryptPassword(user.getUserPassword());

            //Save our user with the New Encrypted Password

            user.setUserPassword(encryptedPassword);
            userRepo.save(user);

            return new SignUpOutput(signUpStatus, "User registered successfully!!!");
        }
        catch(Exception e)
        {
            signUpStatusMessage = "Internal error occurred during sign up";
            signUpStatus = false;
            return new SignUpOutput(signUpStatus,signUpStatusMessage);
        }
    }

    public String userSignIn(SignInInput signInInput) {

        String signInStatusMessage = null;

        String signInEmail = signInInput.getEmail();

        if(signInEmail == null)
        {
            signInStatusMessage = "Invalid email";
            return signInStatusMessage;


        }

        //Check if this user email already exists!
        User ExistingUser = userRepo.findFirstByUserEmail(signInEmail);

        if(ExistingUser == null)
        {
            signInStatusMessage = "Email not registered!!!";
            return signInStatusMessage;

        }

        //Match the passwords :

        //Encrypt the password
        try {
            String encryptedPassword = PasswordEncrypter.encryptPassword(signInInput.getPassword());
            if(ExistingUser.getUserPassword().equals(encryptedPassword))
            {
                //Session should be created since password matched and user id is valid
                Authentication Token  = new Authentication(ExistingUser);
                authenticationService.saveAuthToken(Token);

                return "Your token is : " + Token.getTokenValue() + " please keep it safe";
            }
            else {
                signInStatusMessage = "Invalid credentials!!!";
                return signInStatusMessage;
            }
        }
        catch(Exception e)
        {
            signInStatusMessage = "Internal error occurred during sign in";
            return signInStatusMessage;
        }
    }

    public String userSignOut(String email) {
        User user = userRepo.findFirstByUserEmail(email);
        Authentication token = authenticationService.findFirstByUser(user);
        authenticationService.removeToken(token);
        return "User Signed out successfully";
    }

    public User findFirstByUserEmail(String email) {
       return userRepo.findFirstByUserEmail(email);
    }

    public User getUserById(Integer commenterId) {
        return userRepo.findById(commenterId).orElse(null);
    }
}
