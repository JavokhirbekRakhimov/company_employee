package uz.company.dto.company;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompanyDto {
    @NotNull(message = " id must not be null ")
    private Integer id;
    @NotNull(message = " name must not be null ")
    private String name;
    @NotNull(message = " email must not be null ")
    private String email;
    @NotNull(message = " phone must not be null ")
    private String phone;
}
