package uz.pdp.appnewssite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appnewssite.entity.Post;
import uz.pdp.appnewssite.payload.ApiResponse;
import uz.pdp.appnewssite.payload.PostDto;
import uz.pdp.appnewssite.service.PostService;

import java.util.List;

@RestController
@RequestMapping(value = "/api/post")
public class PostController {

    @Autowired
    PostService postService;

    @PreAuthorize(value = "hasAuthority('ADD_POST')")
    @PostMapping("/addPost")
    public HttpEntity<?> addPost(@RequestBody PostDto postDto){
        ApiResponse apiResponse = postService.addPost(postDto);
        return ResponseEntity.status(apiResponse.isSuccess()?201:409).body(apiResponse);
    }

    @PreAuthorize(value = "hasAuthority('EDET_POST')")
    @PutMapping("/edetPost/{id}")
    public HttpEntity<?> edetPost(@PathVariable Integer id, @RequestBody PostDto postDto){
        ApiResponse apiResponse = postService.edetPost(id, postDto);
        return ResponseEntity.status(apiResponse.isSuccess()?201:409).body(apiResponse);
    }

    @PreAuthorize(value = "hasAuthority('VIEW_ALL_POST')")
    @GetMapping("/getAll")
    public HttpEntity<?> getAllPost(){
        List<Post> allPost = postService.getAllPost();
        return ResponseEntity.ok(allPost);
    }

    @PreAuthorize(value = "hasAuthority('VIEW_POST')")
    @GetMapping("/getById/{id}")
    public HttpEntity<?> getById(@PathVariable Integer id){
        Post byId = postService.getById(id);
        return ResponseEntity.ok(byId);
    }
}
