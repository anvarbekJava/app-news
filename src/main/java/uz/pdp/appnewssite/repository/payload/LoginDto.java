package uz.pdp.appnewssite.payload;

import lombok.Data;

import javax.validation.constraints.NotNull;
@Data
public class LoginDto {
    @NotNull(message = "Username hato!")
    private String username;

    @NotNull(message = "Parol hato!")
    private String password;
}
