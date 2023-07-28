package org.example.Blogging.Platform.API.Service;

import org.example.Blogging.Platform.API.Model.Post;
import org.example.Blogging.Platform.API.Model.User;
import org.example.Blogging.Platform.API.Repository.IPostRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class PostService {
    
    @Autowired
    IPostRepo postRepo;

    @Autowired
    UserService userService;
    public String createPost(Post post, String email) {
        User user = userService.findFirstByUserEmail(email);

        post.setPostOwner(user);    //Now our user is set
        post.setPostCreatedTimeStamp(LocalDateTime.now());

        postRepo.save(post);

        return "Post saved";
    }

    public String deletePost(Integer postId, String email) {
        User user = userService.findFirstByUserEmail(email);

        //Now we got our user, let's verify if our user is matched with one in our post
        Post intendedPost = postRepo.findById(postId).orElse(null);

        //Now if our post is null
        if(intendedPost == null)
            return "Post with Id does not exists";

        if(!intendedPost.getPostOwner().equals(user))
            return "Only owner can delete the post";

        //Now our post is valid and our user is matched
        postRepo.delete(intendedPost);
        return "Post deleted";
    }

    public Post readPost(Integer postId) {
        return postRepo.findById(postId).orElse(null);
    }

    public Post getPostById(Integer postId) {
        return postRepo.findById(postId).orElse(null);
    }

    public String updatePost(Integer postId, String email, String postData) {

        //Let's check if post exists
        Post post = postRepo.findById(postId).orElse(null);

        if(post == null)
            return "Post Id not valid";

        //Check if postOwner matches with one trying to update
        User user = userService.findFirstByUserEmail(email);

        if(!post.getPostOwner().equals(user))
            return "User not allowed";

        post.setPostData(postData);
        post.setPostCreatedTimeStamp(LocalDateTime.now());

        postRepo.save(post);

        return "Post data updated";
    }
}
