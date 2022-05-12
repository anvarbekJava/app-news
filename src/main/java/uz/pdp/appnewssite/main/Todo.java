package uz.pdp.appnewssite.main;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Todo {
    private Integer userId;
    private Integer id;
    private String title;
    private Boolean completed;
}
