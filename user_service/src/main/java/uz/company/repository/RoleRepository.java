package uz.company.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import uz.company.dto.role.RoleCreateDto;
import uz.company.dto.role.RoleUpdateDto;
import uz.company.imp.RepositoryImp;
@Repository
public class RoleRepository implements RepositoryImp<Short> {
    @Autowired
    JdbcTemplate template;

    @Override
    public String create(String json) {
        return template.queryForObject("select create_role(?)",String.class,json);
    }
    @Override
    public String update(String json) {
        return template.queryForObject("select update_role(?)",String.class,json);
    }
    @Override
    public String delete(Short aShort) {
        return null;
    }
    @Override
    public String findAll(Short size,Short page) {
        return template.queryForObject("select find_all_roles(?,?)",String.class, size,page);
    }
    @Override
    public String findById(Short aShort) {
        return template.queryForObject("select find_by_id_role(?)",String.class,aShort);
    }
}
