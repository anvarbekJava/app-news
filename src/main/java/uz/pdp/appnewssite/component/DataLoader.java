package uz.pdp.appnewssite.component;

import net.bytebuddy.implementation.auxiliary.AuxiliaryType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import uz.pdp.appnewssite.entity.Role;
import uz.pdp.appnewssite.entity.Users;
import uz.pdp.appnewssite.entity.enums.Permission;
import uz.pdp.appnewssite.repository.RoleRepository;
import uz.pdp.appnewssite.repository.UserRepository;
import uz.pdp.appnewssite.utils.AppConstans;

import java.util.Arrays;

import static uz.pdp.appnewssite.entity.enums.Permission.*;


@Component
public class DataLoader implements CommandLineRunner {
    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Lazy
    @Autowired
    PasswordEncoder passwordEncoder;

    @Value("${spring.datasource.initialization-mode}")
    private String initialMode;
    @Override
    public void run(String... args) throws Exception {
       if (initialMode.equals("always")){
           Permission[] roles = Permission.values();
           Role admin = roleRepository.save(new Role(AppConstans.ADMIN,
                   Arrays.asList(roles),
                   "Admin boshqaradi"
           ));

           Role user = roleRepository.save(new Role(AppConstans.USER,
                   Arrays.asList(ADD_COMMENT, EDET_COMMENT, DELETE_MY_COMMENT)
                   ,"User boshqaradi"
           ));
           userRepository.save(new Users(
                   "Anvarbek Turdaliyev",
                   "admin",
                   passwordEncoder.encode("123"),
                   admin,
                   true
           ));
           userRepository.save(new Users(
                   "Asrorbek Turdaliyev",
                   "user",
                   passwordEncoder.encode("123"),
                   user,
                   true
           ));
       }
    }
}
