package com.mvc.controller;

import com.mvc.exception.ResourceNotFoundException;
import com.mvc.model.Post;
import com.mvc.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin("*")
public class PostController {

    @Autowired
    private PostRepository postRepository;

    // get all posts
    @GetMapping("/posts")
    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    // create post
    @PostMapping("/posts")
    public Post createEmployee(@RequestBody Post post) {
        return postRepository.save(post);
    }


    // get post by id
    @GetMapping("/posts/{id}")
    public ResponseEntity<Post> getPostById(@PathVariable Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post not exist with id: " + id));
        return ResponseEntity.ok(post);
    }

    // update post
    @PutMapping("/posts/{id}")
    public ResponseEntity<Post> updatePost(@PathVariable Long id, @RequestBody Post postDetail) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post not exist with id: " + id));
        post.setContent(postDetail.getContent());
        post.setImage(postDetail.getImage());

        Post updatePost = postRepository.save(post);
        return ResponseEntity.ok(updatePost);
    }

    //delete Employee
    @DeleteMapping("/posts/{id}")
    public ResponseEntity<Map<String, Boolean>> deletePosts(@PathVariable Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post not exits with id: " + id));
        postRepository.delete(post);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }

}
