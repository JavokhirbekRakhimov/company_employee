package uz.company.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.company.dto.role.RoleCreateDto;
import uz.company.dto.role.RoleUpdateDto;
import uz.company.entity.enums.RoleEnum;
import uz.company.service.RoleService;
import uz.company.util.BaseUtil;
import uz.company.util.SecretKeys;

import javax.validation.Valid;
import java.util.Set;


@RestController
@RequestMapping(path = "/role")
@RequiredArgsConstructor
public class RoleController {
    private final RoleService roleService;
    private final BaseUtil baseUtil;
    @GetMapping
  public ResponseEntity<?>getAll(@Valid @RequestParam(value = "size",defaultValue = SecretKeys.SIZE) Short size,@Valid @RequestParam(value = "page",defaultValue = SecretKeys.PAGE) Short page){
    return roleService.getAll(size,page);
  }
    @GetMapping(path = "/{id}/{companyId}")
  public ResponseEntity<?>getById(@Valid @PathVariable Short id,@Valid @PathVariable Integer companyId){
        baseUtil.checkRole(companyId, Set.of(RoleEnum.ADMIN));
    return roleService.getById(id);
  }
    @PostMapping
  public ResponseEntity<?>create( @Valid @RequestBody RoleCreateDto roleCreateDto){
    return roleService.create(roleCreateDto);
 }
    @PatchMapping
    public ResponseEntity<?>create( @Valid @RequestBody RoleUpdateDto updateDto){
        return roleService.update(updateDto);
    }

}
