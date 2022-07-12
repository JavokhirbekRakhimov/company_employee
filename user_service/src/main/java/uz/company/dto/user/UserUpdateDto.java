package uz.company.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserUpdateDto {
    @NotNull(message = " id must not be null ")
    private String id;
    @NotNull(message = " first_name must not be null ")
    private String first_name;
    @NotNull(message = " last_name must not be null ")
    private String last_name;
    @NotNull(message = " user_name must not be null ")
    private String user_name; //unique
    @NotNull(message = " password must not be null ")
    private String password;
    private String email; //unique
    private String phone;  //unique
}
