package uz.company.service;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import uz.company.dto.role.RoleCreateDto;
import uz.company.dto.role.RoleDto;
import uz.company.dto.role.RoleUpdateDto;
import uz.company.entity.Role;
import uz.company.entity.User;
import uz.company.repository.RoleRepository;
import uz.company.util.ApiResponseList;
import uz.company.util.ApiResponseObject;
import uz.company.util.BaseUtil;

import java.net.UnknownServiceException;

@Service
@RequiredArgsConstructor
public class RoleService {
    private final RoleRepository roleRepository;
    public ResponseEntity<?> getAll() {
        try {
            String all = roleRepository.findAll();
            ApiResponseList<RoleDto> list =new ObjectMapper().readValue(all, new TypeReference<>() {
            });

            return ResponseEntity.status(list.getHttpStatus()).body(list);

        }catch (Exception e){

            return ResponseEntity.status(500).body("Server error");
        }
    }
    public ResponseEntity<?> create(RoleCreateDto roleCreateDto) {

        try {
            String save = roleRepository.create(new ObjectMapper().writeValueAsString(roleCreateDto));
            ApiResponseObject<RoleDto> byIdObj = new ObjectMapper().readValue(save, new TypeReference<>() {
            });

            return ResponseEntity.status(byIdObj.getHttpStatus()).body(byIdObj);

        }catch (Exception e){
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }


    public ResponseEntity<?> getById(Short id) {
        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        try {
            String byId = roleRepository.findById(id);

            ApiResponseObject<RoleDto> byIdObj = new ObjectMapper().readValue(byId, new TypeReference<>() {
            });

            return ResponseEntity.status(byIdObj.getHttpStatus()).body(byIdObj);

        }catch (Exception e){
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    public ResponseEntity<?> update(RoleUpdateDto updateDto) {
        try {
            String byId = roleRepository.update(new ObjectMapper().writeValueAsString(updateDto));
            ApiResponseObject<RoleDto> byIdObj = new ObjectMapper().readValue(byId, new TypeReference<>() {
            });

            return ResponseEntity.status(byIdObj.getHttpStatus()).body(byIdObj);

        }catch (Exception e){
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }
}
