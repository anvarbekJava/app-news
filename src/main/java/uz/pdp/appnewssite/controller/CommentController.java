package uz.pdp.appnewssite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appnewssite.entity.Comment;
import uz.pdp.appnewssite.payload.ApiResponse;
import uz.pdp.appnewssite.payload.CommentDto;
import uz.pdp.appnewssite.service.CommentService;

import java.util.List;

@RestController
@RequestMapping(value = "/api/comment")
public class CommentController {
    @Autowired
    CommentService commentService;
    @PreAuthorize(value = "hasAuthority('ADD_COMMENT')")
    @PostMapping("/addComment")
    public HttpEntity<?> addComment(@RequestBody CommentDto dto){
        ApiResponse apiResponse = commentService.addComment(dto);
        return ResponseEntity.status(apiResponse.isSuccess()?201:409).body(apiResponse);
    }
    @PreAuthorize(value = "hasAuthority('EDET_COMMENT')")
    @PutMapping("/edetComment/{id}")
    public HttpEntity<?> edetComment(@PathVariable Integer id, @RequestBody CommentDto dto){
        ApiResponse apiResponse = commentService.edetComment(id, dto);
        return ResponseEntity.status(apiResponse.isSuccess()?202:409).body(apiResponse);
    }

    @PreAuthorize(value = "hasAuthority('DELETE_COMMENT')")
    @DeleteMapping("/deleteComment/{id}")
    public HttpEntity<?> deleteComment(@PathVariable Integer id){
        ApiResponse apiResponse = commentService.deleteComment(id);
        return ResponseEntity.status(apiResponse.isSuccess()?202:409).body(apiResponse);
    }

    @PreAuthorize(value = "hasAuthority('DELETE_MY_COMMENT')")
    @DeleteMapping("/deleteMyComment/{id}")
    public HttpEntity<?> deleteMyComment(@PathVariable Integer id){
        ApiResponse apiResponse = commentService.deleteMyComment(id);
        return ResponseEntity.status(apiResponse.isSuccess()?203:409).body(apiResponse);
    }

    @PreAuthorize(value = "hasAuthority('VIEW_COMMENT')")
    @GetMapping("/view")
    public HttpEntity<?> getAllComment(){
        List<Comment> allComment = commentService.getAllComment();
        return ResponseEntity.ok(allComment);
    }
}
