package uz.pdp.appnewssite.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import uz.pdp.appnewssite.entity.Comment;
import uz.pdp.appnewssite.entity.Post;
import uz.pdp.appnewssite.entity.Users;
import uz.pdp.appnewssite.payload.ApiResponse;
import uz.pdp.appnewssite.payload.CommentDto;
import uz.pdp.appnewssite.repository.CommentRepository;
import uz.pdp.appnewssite.repository.PostRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CommentService {
    @Autowired
    CommentRepository commentRepository;

    @Autowired
    PostRepository postRepository;

    public ApiResponse addComment(CommentDto commentDto){
        Comment comment = new Comment();
        comment.setText(commentDto.getText());
        Optional<Post> optionalPost = postRepository.findById(commentDto.getPostId());
        if (!optionalPost.isPresent())
            return new ApiResponse("Post mavjud emas", false);
        Post post = optionalPost.get();
        comment.setPost(post);
        commentRepository.save(comment);
        return new ApiResponse("Comment saqlandi", true);
    }

    public ApiResponse edetComment(Integer id, CommentDto dto){
        Optional<Comment> optionalComment = commentRepository.findById(id);
        if(!optionalComment.isPresent())
            return new ApiResponse("Comment mavjud emas", false);
        Comment comment = optionalComment.get();
        comment.setText(dto.getText());

        Optional<Post> optionalPost = postRepository.findById(dto.getPostId());
        if (!optionalPost.isPresent())
            return new ApiResponse("Post mavjud emas", false);
        Post post = optionalPost.get();
        comment.setPost(post);

        return new ApiResponse("Comment tahrirlandi", true);
    }

    public ApiResponse deleteComment(Integer id){
        try {
            commentRepository.deleteById(id);
            return new ApiResponse("Delete comment", true);
        }catch (Exception e){
            return new ApiResponse("No deleted comment", false);
        }
    }
    public ApiResponse deleteMyComment(Integer id){
        Users user =(Users) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Users createdBy = user.getCreatedBy();
        Optional<Comment> optionalComment = commentRepository.findById(id);
        if (!optionalComment.isPresent())
            return new ApiResponse("Commment topiladi", false);
        Comment comment = optionalComment.get();
        Users createdByComment = comment.getCreatedBy();

        if (createdByComment.equals(createdBy)){
            try {
                commentRepository.deleteById(id);
                return new ApiResponse("Delete comment", true);
            }catch (Exception e){
                return new ApiResponse("No deleted comment", false);
            }
        }else {
            return new ApiResponse("No deleted comment", false);
        }

    }
    public List<Comment> getAllComment(){
        List<Comment> allComment = commentRepository.findAll();
        return allComment;
    }
}
