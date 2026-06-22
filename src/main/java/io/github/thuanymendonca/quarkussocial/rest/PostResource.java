package io.github.thuanymendonca.quarkussocial.rest;

import io.github.thuanymendonca.quarkussocial.domain.model.Post;
import io.github.thuanymendonca.quarkussocial.domain.model.User;
import io.github.thuanymendonca.quarkussocial.domain.repository.PostRepository;
import io.github.thuanymendonca.quarkussocial.domain.repository.UserRepository;
import io.github.thuanymendonca.quarkussocial.rest.dto.CreatePostRequest;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.time.LocalDateTime;

@Path("/users/{userId}/posts")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PostResource {

    private final UserRepository userRepository;
    private final PostRepository postRepository;

    public PostResource(UserRepository userRepository, PostRepository postRepository) {
        this.userRepository = userRepository;
        this.postRepository = postRepository;
    }

    @POST
    @Transactional
    public Response savePost(@PathParam("userId") Long userId, CreatePostRequest request){
        User user = userRepository.findById(userId);
        if(user == null){
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        Post post = new Post();
        post.setText(request.getText());
        post.setUser(user);
        //post.setDateTime(LocalDateTime.now()); - Estou utilizando o prePersist no model Post

        postRepository.persist(post);
        return Response.status(Response.Status.CREATED).build();
    }

    @GET
    public Response listPosts(@PathParam("userId") Long userId){
        User user = userRepository.findById(userId);
        if(user == null){
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        return Response.ok().build();
    }

}
