package uz.company.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Company {
    private Integer id;
    private String name;
    private String email;
    private String phone;
    private Set<Role>roles;
}
