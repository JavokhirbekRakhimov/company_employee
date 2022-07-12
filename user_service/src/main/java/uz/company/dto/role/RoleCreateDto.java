
package uz.company.dto.role;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleCreateDto {
    @NotNull(message = " name must not be null ")
    private String name;
    private Short rank;
}
