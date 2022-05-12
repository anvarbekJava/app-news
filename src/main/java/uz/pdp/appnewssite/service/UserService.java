package uz.pdp.appnewssite.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.pdp.appnewssite.entity.Role;
import uz.pdp.appnewssite.entity.Users;
import uz.pdp.appnewssite.payload.ApiResponse;
import uz.pdp.appnewssite.payload.UserDto;
import uz.pdp.appnewssite.repository.RoleRepository;
import uz.pdp.appnewssite.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    public ApiResponse addUser(UserDto userDto) {
        if (userRepository.existsByUsername(userDto.getUsername()))
            return new ApiResponse("Bunday foydalanuvchi mavjud", false);
        Users users = new Users();
        users.setUsername(userDto.getUsername());
        users.setFullName(userDto.getFullName());
        users.setPassword(passwordEncoder.encode(userDto.getPassword()));
        Optional<Role> optionalRole = roleRepository.findById(userDto.getRoleId());
        if (!optionalRole.isPresent())
            return new ApiResponse("Bunday role mavjud emas", false);
        Role role = optionalRole.get();
        users.setRole(role);
        users.setEnabled(true);
        userRepository.save(users);
        return new ApiResponse("User saqlandi", true);
    }

    public ApiResponse edetUser(Integer id, UserDto userDto) {
        Optional<Users> optionalUsers = userRepository.findById(id);
        if (!optionalUsers.isPresent())
            return new ApiResponse("Bunda users mavjud emas", false);
        Users users = optionalUsers.get();
        users.setFullName(userDto.getFullName());
        users.setUsername(userDto.getUsername());
        users.setPassword(passwordEncoder.encode(userDto.getPassword()));
        Optional<Role> optionalRole = roleRepository.findById(userDto.getRoleId());
        if (!optionalRole.isPresent())
            return new ApiResponse("Bunday role mavjud emas", false);
        Role role = optionalRole.get();
        users.setRole(role);
        users.setEnabled(true);
        userRepository.save(users);
        return new ApiResponse("User edet qilindi", true);
    }

    public ApiResponse completedUser(Integer id){
        Optional<Users> optionalUsers = userRepository.findById(id);
        if (!optionalUsers.isPresent())
            return new ApiResponse("Bunday user mavjud emas", false);
        Users users = optionalUsers.get();
        if (users.isEnabled()) {
            users.setEnabled(false);
            userRepository.save(users);
            return new ApiResponse("User ochirildi", true);
        }else {
            users.setEnabled(true);
            userRepository.save(users);
            return new ApiResponse("User yoqildi", true);
        }
    }

    public List<Users> getAllUser() {
        List<Users> usersList = userRepository.findAll();
        return usersList;
    }
}