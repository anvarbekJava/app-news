package uz.pdp.appnewssite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appnewssite.entity.Users;
import uz.pdp.appnewssite.payload.ApiResponse;
import uz.pdp.appnewssite.payload.UserDto;
import uz.pdp.appnewssite.service.UserService;

import java.util.List;

@RestController
@RequestMapping(value = "/api/user")
public class UserController {

    @Autowired
    UserService userService;

    @PreAuthorize(value = "hasAuthority('ADD_USER')")
    @PostMapping("/addUser")
    public HttpEntity<?> addUser(@RequestBody UserDto userDto){
        ApiResponse apiResponse = userService.addUser(userDto);
        return ResponseEntity.status(apiResponse.isSuccess()?201:409).body(apiResponse);
    }

    @PreAuthorize(value = "hasAuthority('EDET_USER')")
    @PutMapping("/edetUser/{id}")
    public HttpEntity<?> edetUser(@PathVariable Integer id, @RequestBody UserDto userDto){
        ApiResponse apiResponse = userService.edetUser(id, userDto);
        return ResponseEntity.status(apiResponse.isSuccess()?202:409).body(apiResponse);
    }

    @PreAuthorize(value = "hasAuthority('EDET_USER')")
    @PutMapping("/completedUser/{id}")
    public HttpEntity<?> completedUser(@PathVariable Integer id){
        ApiResponse apiResponse = userService.completedUser(id);
        return ResponseEntity.status(apiResponse.isSuccess()?202:409).body(apiResponse);
    }

    @PreAuthorize(value = "hasAuthority('VIEW_USER')")
    @GetMapping("/getAll")
    public HttpEntity<?> getAllUser(){
       List<Users> list = userService.getAllUser();
       return ResponseEntity.ok(list);
    }
}
