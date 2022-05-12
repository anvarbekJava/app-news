package uz.pdp.appnewssite.controller;

import org.apache.catalina.realm.JAASRealm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appnewssite.aop.CheckPermission;
import uz.pdp.appnewssite.entity.Role;
import uz.pdp.appnewssite.payload.ApiResponse;
import uz.pdp.appnewssite.payload.RoleDto;
import uz.pdp.appnewssite.service.RoleService;

import java.util.List;

@RestController
@RequestMapping(value = "/api/role")
public class RoleController {

    @Autowired
    RoleService roleService;

    @PreAuthorize(value = "hasAuthority('ADD_ROLE')")
    @PostMapping("/addRole")
    public HttpEntity<?> addRole(@RequestBody RoleDto dto){
        ApiResponse apiResponse = roleService.addRole(dto);
        return ResponseEntity.status(apiResponse.isSuccess()?201:409).body(apiResponse);
    }

    //@PreAuthorize(value = "hasAuthority('EDET_ROLE')")
    @CheckPermission(huquq = "EDET_ROLE")
    @PutMapping("/edetRole/{id}")
    public HttpEntity<?> edetRole(@PathVariable Integer id, @RequestBody RoleDto dto){
        ApiResponse apiResponse = roleService.edetRole(id, dto);
        return ResponseEntity.status(apiResponse.isSuccess()?201:409).body(apiResponse);
    }

    @PreAuthorize(value = "hasAuthority('VIEW_ROLE')")
    @GetMapping("/get")
    public HttpEntity<?> getRole(){
        List<Role> list = roleService.getAllRole();
        return ResponseEntity.ok(list);
    }

    @PreAuthorize(value = "hasAuthority('DELETE_ROLE')")
    @DeleteMapping("/delete/{id}")
    public HttpEntity<?> deleteRole(@PathVariable Integer id){
        ApiResponse apiResponse = roleService.deleteRole(id);
        return ResponseEntity.status(apiResponse.isSuccess()?202:409).body(apiResponse);
    }

    @PreAuthorize(value = "hasAuthority('VIEW_ROLE')")
    @GetMapping(value = "/getById/{id}")
    public HttpEntity<?> getById(@PathVariable Integer id){
        Role role = roleService.getById(id);
        return ResponseEntity.ok(role);
    }
}
