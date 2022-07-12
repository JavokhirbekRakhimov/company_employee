package uz.company.util;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import uz.company.entity.Company;
import uz.company.entity.Role;
import uz.company.entity.enums.RoleEnum;
import uz.company.exceptions.UniversalException;

import java.io.IOException;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class BaseUtil {
  public void checkRole(Integer company_id, Set<RoleEnum> roleEnumSet){
      Set<String> collect = roleEnumSet.stream().map(Enum::name).collect(Collectors.toSet());
      boolean hasPermission=false;
      Collection<? extends GrantedAuthority> authorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
      for (GrantedAuthority authority : authorities) {
          try {
              Company company = new ObjectMapper().readValue(authority.getAuthority(), new TypeReference<Company>() {});
              if (company.getId().equals(company_id)){
                  for (Role role : company.getRoles()) {
                      if (collect.contains(role.getName())){
                          hasPermission=true;
                          break;
                      }
                  }
              }
          } catch (IOException e) {
              throw new UniversalException("Role check exception", HttpStatus.CONFLICT);
          }
          if (hasPermission)
              break;
      }
      if (!hasPermission)
          throw new UniversalException("Not permission",HttpStatus.UNAUTHORIZED);
  }
}
