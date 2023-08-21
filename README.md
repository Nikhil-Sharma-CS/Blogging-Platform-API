
# Blogging-Platform-API


This is the code for Blogging-Platform-API. Its an small representation of how a Blooging Platform works. Where users can post/blog, add comments, follow unfollow each other.

This also uses **Relational Data** and **JPA** for data source.

This also implements various concepts of mappings like **One to One**, **One to Many**, etc.

This project is hosted on a remote server with a static IP.

This is the static IP along with swagger UI link **http://3.109.203.237:8080/swagger-ui/index.html#/**.

## 🚀 About Me
*Hi, My name is Nikhil Sharma*,

I'm a full stack developer trainee at Geekster. I have knowledge of Java, OOPs, Maven, APIs, DSA, SpringBoot.


# Data Flow

## Controller
-   *This section contains 1 class which uses API endpoints as mentioned below* :

* ### UserController Class

    * @PostMapping("user/signup")
    * @PostMapping("user/signIn")
    * @DeleteMapping("user/signOut")
    * @PostMapping("createPost")
    * @DeleteMapping("deletePost/{postId}")
    * @GetMapping("readPost/{postId}")
    * @PutMapping("updatePost")
    * @PostMapping("addComment")
    * @GetMapping("seeComments/{postId}")
    * @DeleteMapping("deleteComment")
    * @PutMapping("editComment")
    * @PostMapping("follow")
    * @DeleteMapping("unfollow")
    * @GetMapping("followerCount")

## Services
-  *This section contains 5 classes with functions that helps API to perform their specific tasks* : 


* ### UserService Class

    * userSignUp(User user)
    * userSignIn(SignInInput signInInput)
    * userSignOut(String email)
    * findFirstByUserEmail(String email)
    * getUserById(Integer commenterId)
    
* ### PostService Class

    * createPost(Post post, String email)
    * deletePost(Integer postId, String email)
    * readPost(Integer postId)
    * updatePost(Integer postId, String email, String postData)
    * getPostById(Integer postId)

* ### AuthenticationService Class

    * authenticate(String email, String token)
    * saveAuthToken(Authentication Token)
    * findFirstByUser(User user)
    * removeToken(Authentication token)

* ### CommentService Class

    * addComment(Comment comment, String email)
    * seeComments(Integer postId)
    * deleteComment(Integer postId, Integer commentId, String email)
    * editComment(String commentData, Integer commentId, String email)

* ### FollowService Class
    
    * followUser(String followerEmail, Follow follow)
    * unfollowUser(String email, Integer userId)
    * followerCount(Integer userId)


- *This section also includes a **hashing function** which is used to encrypt password.*

## Model

- *This section contains 5 models as required by project named **User**, **Comment**, **Authentication**, **Follow**, **Post**. They have their relative datamembers in them which will be used as columns to store their respective data*.

- *It also contains 2 Dtos*

##  Repository
- *This section contains 5 **Interfaces** named IUserRepo, IAuthenticationRepo, ICommentRepo, IPostRepo, IFollowRepo.*

- *They all extends JpaRepository Interface*.


## Database Design
- *In this section we used the models as mentioned in Model section, which are used as a table to store data*.
- *It also uses **Relational Database** and **JPA** to create datastructure*.
- *We use **MYSQL** which is a management system for relational data*

## Data Structure Used

- *We used **Relational database** along with **JPA** as the data source in this project*.

- *This kind of data persists forever and we use **SQL** to manipulate this data via MYSQL.*

- *The database of this project is hosted on a remote AWS server.*

- *That remote database server is used in our project which is also hosted on a remote AWS server, so that another person can access project.*

## Screenshots

- Below are some screenshots of how code looks like and results of some of its functionalities

   - Code Show
  
   ![Code Show](https://github.com/Nikhil-Sharma-CS/Blogging-Platform-API/assets/72157075/e212acce-3032-424d-9c88-26c11a704551)

   - Code Run
  
   ![Code Running](https://github.com/Nikhil-Sharma-CS/Blogging-Platform-API/assets/72157075/c0f2af1d-3a99-4acb-a09e-16bc3258d78e)

   - Sign Out
     
   ![Sign Out](https://github.com/Nikhil-Sharma-CS/Blogging-Platform-API/assets/72157075/68d08b89-562c-4c7f-b0ef-f57f0b8d68c0)

   - See Comments
     
   ![See Comments](https://github.com/Nikhil-Sharma-CS/Blogging-Platform-API/assets/72157075/da661ef9-a413-4754-b055-fafd4ac78500)

   - Read Post
     
   ![Read Post](https://github.com/Nikhil-Sharma-CS/Blogging-Platform-API/assets/72157075/03b869bb-6338-4221-9cc5-af88a7d04305)




## Summary

*This project performs the task which a user can perform on a Blogging site. Like post a blog, reading it, commenting on the blog/post etc. with various types of API's like **Get, Post, Put, Delete***.

*This project is also connected to a relational data which is the data source of this project.*

*We used **MVC Architecture** to create this project. 
And this is created in **SpringBoot** using **Maven** and **Java**.*


## 🔗 Links
[![Github](https://img.shields.io/badge/Github-000?style=for-the-badge&logo=ko-fi&logoColor=white)](https://github.com/Nikhil-Sharma-CS)
[![linkedin](https://img.shields.io/badge/linkedin-0A66C2?style=for-the-badge&logo=linkedin&logoColor=white)](https://www.linkedin.com/in/nikhil-sharma-cse)


