package uz.pdp.appnewssite.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostDto {
    @NotNull(message ="Title toldiring")
    private String title;

    @NotNull(message = "Content toldirish shart")
    private String text;

    @NotNull(message = "Url ni toldirish shart")
    private String url;
}
