package uz.pdp.appnewssite.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterDto {
    @NotNull
    private String fullName;

    @NotNull(message = "Username hato!")
    private String username;

    @NotNull(message = "Parol hato!")
    private String password;

    @NotNull(message = "Parol takrori hato!")
    private String prePassword;
}
