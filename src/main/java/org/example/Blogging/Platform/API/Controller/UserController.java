package org.example.Blogging.Platform.API.Controller;

import jakarta.validation.Valid;
import org.example.Blogging.Platform.API.Model.Comment;
import org.example.Blogging.Platform.API.Model.Dto.SignInInput;
import org.example.Blogging.Platform.API.Model.Dto.SignUpOutput;
import org.example.Blogging.Platform.API.Model.Follow;
import org.example.Blogging.Platform.API.Model.Post;
import org.example.Blogging.Platform.API.Model.User;
import org.example.Blogging.Platform.API.Service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Validated
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    AuthenticationService authenticationService;

    @Autowired
    PostService postService;

    @Autowired
    CommentService commentService;

    @Autowired
    FollowService followService;


    //Firstly Lets Sign Up our user
    @PostMapping("user/signUp")
    public SignUpOutput userSignUp(@RequestBody User user)
    {

        return userService.userSignUp(user);
    }

    //Let's Sign In Our User
    @PostMapping("user/signIn")
    public String userSignIn(@RequestBody @Valid SignInInput signInInput)
    {
        return userService.userSignIn(signInInput);
    }


    //Let's Sign Out User
    @DeleteMapping("user/signOut")
    public String userSignOut(String email, String token)
    {
        if(authenticationService.authenticate(email,token)) {
            return userService.userSignOut(email);
        }
        else {
            return "Authentication Failed.";
        }
    }

    //Let's implement post feature--------------------------------------------------------------------------------

    //Let's help our user to create a Post/Blog
    @PostMapping("createPost")
    public String createPost(String email, String token, @RequestBody Post post)
    {
        if(authenticationService.authenticate(email, token))
        {
            return postService.createPost(post, email);
        }
        return "Authentication Failed";
    }

    //Now let User delete the post
    @DeleteMapping("deletePost/{postId}")
    public String deletePost(String email, String token, @PathVariable Integer postId)
    {
        if(authenticationService.authenticate(email, token))
        {
            return postService.deletePost(postId, email);
        }
        return "Authentication Failed";
    }

    //Now any user can read any post
    @GetMapping("readPost/{postId}")
    public Post readPost(String email, String token, @PathVariable Integer postId)
    {
        if(authenticationService.authenticate(email, token))
        {
            return postService.readPost(postId);
        }
        return null;
    }

    //Let's allow owner to update post
    @PutMapping("updatePost")
    public String updatePost(String email, String token, Integer postId, String postData)
    {
        if(authenticationService.authenticate(email, token))
        {
            return postService.updatePost(postId, email, postData);
        }
        return "Authentication Failed";
    }

    //Let's implement comment feature --------------------------------------------------------------------------------

    //Now let's add some comments to our post, and any user can add comments including owner
    @PostMapping("addComment")
    public String addComment(String email, String token, @RequestBody Comment comment)
    {
        if(authenticationService.authenticate(email, token))
        {
            return commentService.addComment(comment,email);
        }
        return "Authentication Failed";
    }

    //Now let's allow our users to see comments
    @GetMapping("seeComments/{postId}")
    public List<Comment> seeComments(String email, String token, @PathVariable Integer postId)
    {
        if(authenticationService.authenticate(email, token))
        {
            return commentService.seeComments(postId);
        }
        return null;
    }

    //Let's allow our user to delete comment. This could be done by comment owner or post owner both.
    @DeleteMapping("deleteComment")
    public String deleteComment(String email, String token, Integer postId, Integer commentId)
    {
        if(authenticationService.authenticate(email, token))
        {
            return commentService.deleteComment(postId, commentId, email);
        }
        return "Authentication Failed";
    }

    //Let's allow comment owners to edit comment
    @PutMapping("editComment")
    public String editComment(String email, String token, Integer commentId, String commentData)
    {
        if(authenticationService.authenticate(email, token))
        {
            return commentService.editComment(commentData, commentId, email);
        }
        return "Authentication Failed";
    }

    //Now Lets Implement Follow features---------------------------------------------------------------------------

    //Firstly Follow Feature
    @PostMapping("follow")
    public String followUser(String email, String token, @RequestBody Follow follow)
    {
        if(authenticationService.authenticate(email, token))
        {
            return followService.followUser(email, follow);
        }
        return "Authentication Failed";
    }

    //Now let's unfollow our user
    @DeleteMapping("unfollow")
    public String unfollowUser(String email, String token, Integer userId)
    {
        if(authenticationService.authenticate(email, token))
            return followService.unfollowUser(email, userId);

        return "Authentication Failed";
    }

    //Now let's get a follower count for a user
    @GetMapping("followerCount")
    public String followerCount(String email, String token, Integer userId)
    {
        if(authenticationService.authenticate(email, token))
            return followService.followerCount(userId);
        return "Authentication Failed";
    }

}
