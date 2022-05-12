package uz.pdp.appnewssite.payload;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class CommentDto {
    @NotNull(message = "Toldirish shart")
    private String text;


    private Integer postId;
}
