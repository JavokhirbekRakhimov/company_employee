package uz.company.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import uz.company.dto.user.UserCreateDto;
import uz.company.dto.user.UserUpdateDto;
import uz.company.imp.RepositoryImp;

import java.util.UUID;

@org.springframework.stereotype.Repository
@RequiredArgsConstructor
public class UserRepository implements RepositoryImp<UUID> {
private final JdbcTemplate template;
    @Override   
    public String create(String json) {
        return null;
    }

    @Override
    public String update(String json) {
        return null;
    }

    @Override
    public String delete(UUID uuid) {
        return null;
    }

    @Override
    public String findAll(Short size,Short page) {
        return null;
    }

    @Override
    public String findById(UUID uuid) {
        return null;
    }

    public String findByUserNameAndPassword(String userName,String password) {
        return template.queryForObject("select user_find_by_userName_and_password(?,?)",String.class,userName,password);
    }

    public String findByUserName(String username) {
        return template.queryForObject("select user_find_by_userName(?)",String.class,username);
    }
}
