package org.example.Blogging.Platform.API.Service;

import org.example.Blogging.Platform.API.Model.Comment;
import org.example.Blogging.Platform.API.Model.Post;
import org.example.Blogging.Platform.API.Model.User;
import org.example.Blogging.Platform.API.Repository.ICommentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CommentService {
    
    @Autowired
    ICommentRepo commentRepo;

    @Autowired
    PostService postService;

    @Autowired
    UserService userService;
    public String addComment(Comment comment, String email) {

        Post intendedPost = comment.getPost();

        Integer postId = intendedPost.getPostId();
        //Firstly we'll verify if post is exists or not
        intendedPost = postService.getPostById(postId);

        if(intendedPost == null)
            return "Post does not exists";

        //Now we'll set the post and user of comment object
        User commenter = userService.findFirstByUserEmail(email);

//        Integer commenterId  = commenter.getUserId();
//
//        commenter = userService.getUserById(commenterId);

//        if(commenter == null)
//            return "User does not exists";

//        String userEmail = commenter.getUserEmail();
//
//        if(!userEmail.equals(email))
//            return "User email and provided does not match";

        comment.setCommenter(commenter);

        comment.setPost(intendedPost);
        comment.setCommentCreationTime(LocalDateTime.now());

        commentRepo.save(comment);

        return "Comment added";
    }

    public List<Comment> seeComments(Integer postId) {
        Post post = postService.getPostById(postId);

        if(post == null)
            return null;

        return commentRepo.findBypost(post);
    }

    public String deleteComment(Integer postId, Integer commentId, String email) {

        User user = userService.findFirstByUserEmail(email);

        //Now this user should either be postOwner or CommentOwner
        Post post = postService.getPostById(postId);

        //Let's check if post is valid
        if(post == null)
            return "Post Id not valid";

        Comment comment = commentRepo.findById(commentId).orElse(null);

        //Let's check if comment is valid or not
        if(comment == null)
            return "Comment Id not valid";

        //Let's check if comment belongs to that post or not
        if(!comment.getPost().equals(post))
            return "Comment doesn't belong to the desired post";

        if(comment.getCommenter().equals(user) || post.getPostOwner().equals(user))
        {
            commentRepo.delete(comment);
            return "Comment deleted";
        }

        return "User is not allowed to perform this operation";
    }

    public String editComment(String commentData, Integer commentId, String email) {
        //Lets check if comment exists or not
        Comment comment = commentRepo.findById(commentId).orElse(null);

        if(comment == null)
            return "Comment by Id does not exists";

        User user = userService.findFirstByUserEmail(email);

        //Now lets check if the user matches with comment owner
        if(!comment.getCommenter().equals(user))
            return "User mismatch";

        comment.setCommentData(commentData);
        comment.setCommentCreationTime(LocalDateTime.now());

        commentRepo.save(comment);

        return "Comment Updated";
    }
}
