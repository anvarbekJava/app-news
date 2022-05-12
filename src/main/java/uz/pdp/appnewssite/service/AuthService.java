package uz.pdp.appnewssite.service;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.pdp.appnewssite.entity.Role;
import uz.pdp.appnewssite.entity.Users;
import uz.pdp.appnewssite.exseptions.ResourceNotFoundException;
import uz.pdp.appnewssite.payload.ApiResponse;
import uz.pdp.appnewssite.payload.RegisterDto;
import uz.pdp.appnewssite.repository.RoleRepository;
import uz.pdp.appnewssite.repository.UserRepository;
import uz.pdp.appnewssite.utils.AppConstans;

@Service
public class AuthService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Lazy
    @Autowired
    PasswordEncoder passwordEncoder;

    public ApiResponse register(RegisterDto dto){
        if (!dto.getPassword().equals(dto.getPrePassword()))
            return new ApiResponse("Parollar mos kelmadi", false);
        if(userRepository.existsByUsername(dto.getUsername()))
            return new ApiResponse("Bunday user mavjud", false);
        Users users= new Users();
        users.setFullName(dto.getFullName());
        users.setUsername(dto.getUsername());
        users.setPassword(passwordEncoder.encode(dto.getPassword()));
        Role role = roleRepository.findByName(AppConstans.USER).orElseThrow(() ->
                new ResourceNotFoundException("Role", "name", true));
        users.setRole(role);
        users.setEnabled(true);
        userRepository.save(users);

        return new ApiResponse("Success register", true);
    }


    public UserDetails loadUserByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(username));
    }
}
