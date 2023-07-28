package org.example.Blogging.Platform.API.Service;

import org.example.Blogging.Platform.API.Model.Follow;
import org.example.Blogging.Platform.API.Model.User;
import org.example.Blogging.Platform.API.Repository.IFollowRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FollowService {

    @Autowired
    IFollowRepo followRepo;

    @Autowired
    UserService userService;

    public String followUser(String followerEmail, Follow follow) {

        //Let's extract follower from email
        User follower = userService.findFirstByUserEmail(followerEmail);

        //Let's extract the user to be followed
        User user = follow.getUser();

        Integer userId = user.getUserId();

        user = userService.getUserById(userId);

        if(user == null)
            return "User to be followed does not exists";

        //Now user to be followed if exists than we will check if same user is trying to follow itself or not
        if(follower.equals(user))
            return "You cannot follow yourself";

        follow.setUser(user);

        follow.setUserFollower(follower);

        followRepo.save(follow);

        return "Followed : " + user.getUserName();
    }

    public String unfollowUser(String email, Integer userId) {

        //Let's extract follower from email
        User follower = userService.findFirstByUserEmail(email);

        User user = userService.getUserById(userId);

        //We'll check if user to be unfollowed exists or not
        if(user == null)
            return "User to be unfollowed does not exists";

        Follow follow = followRepo.findByUserAndUserFollower(user, follower);

        if(follow == null)
            return "You do not follow this user";

        followRepo.delete(follow);

        return "Unfollowed";

    }

    public String followerCount(Integer userId) {
        User user = userService.getUserById(userId);

        if(user == null)
            return "User does not exists";

        Integer count = followRepo.countByUser(user);

        return "Follower count for " + user.getUserName() + " is : " + count;
    }
}
