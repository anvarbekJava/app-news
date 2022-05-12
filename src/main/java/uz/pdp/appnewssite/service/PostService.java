package uz.pdp.appnewssite.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.appnewssite.entity.Post;
import uz.pdp.appnewssite.payload.ApiResponse;
import uz.pdp.appnewssite.payload.PostDto;
import uz.pdp.appnewssite.repository.PostRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {
    @Autowired
    PostRepository postRepository;


    public ApiResponse addPost(PostDto postDto) {
        if(postRepository.existsByTitle(postDto.getTitle()))
            return new ApiResponse("Bunday post mavjud", false);
        Post post = new Post();
        post.setTitle(postDto.getTitle());
        post.setText(postDto.getText());
        post.setUrl(postDto.getUrl());
        postRepository.save(post);
        return new ApiResponse("Post saqlandi", true);
    }

    public ApiResponse edetPost(Integer id, PostDto postDto){
        Optional<Post> optionalPost = postRepository.findById(id);
        if (!optionalPost.isPresent())
            return new ApiResponse("Bunday post mavjud emas", false);
        Post post = optionalPost.get();
        post.setTitle(postDto.getTitle());
        post.setText(postDto.getText());
        post.setUrl(postDto.getUrl());
        postRepository.save(post);
        return new ApiResponse("Edet qilindi", true);
    }

    public List<Post> getAllPost(){
       return postRepository.findAll();
    }

    public Post getById(Integer id){
        Optional<Post> optionalPost = postRepository.findById(id);
        if (!optionalPost.isPresent())
            return null;
        Post post = optionalPost.get();
        return post;
    }
    public ApiResponse deletePost(Integer id){
        try {
            postRepository.deleteById(id);
            return new ApiResponse("Delete post", true);
        }catch (Exception e){
            return new ApiResponse("No Delete post", false);
        }
    }
}
