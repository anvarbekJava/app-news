package uz.pdp.appnewssite.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.appnewssite.entity.Role;
import uz.pdp.appnewssite.payload.ApiResponse;
import uz.pdp.appnewssite.payload.RoleDto;
import uz.pdp.appnewssite.repository.RoleRepository;

import java.util.List;
import java.util.Optional;

@Service
public class RoleService {
    @Autowired
    RoleRepository roleRepository;

    public ApiResponse addRole(RoleDto roleDto) {
        if (roleRepository.existsByName(roleDto.getName()))
            return new ApiResponse("Bunday role mavjub", false);
        Role role = new Role();
        role.setName(roleDto.getName());
        role.setDescription(roleDto.getDesctription());
        role.setRoleList(roleDto.getRoleList());
        roleRepository.save(role);

        return new ApiResponse("Role saqlandi", true);
    }


    public ApiResponse edetRole(Integer id, RoleDto dto) {
        Optional<Role> optionalRole = roleRepository.findById(id);
        if(!optionalRole.isPresent())
            return new ApiResponse("Bunday role mavjud emas", false);
        Role role = optionalRole.get();
        role.setName(dto.getName());
        role.setDescription(dto.getDesctription());
        role.setRoleList(dto.getRoleList());
        roleRepository.save(role);
        return new ApiResponse("O'zgartirildi", true);
    }

    public List<Role> getAllRole() {
        List<Role> all = roleRepository.findAll();
        return all;
    }

    public ApiResponse deleteRole(Integer id) {
        try {
            roleRepository.deleteById(id);
            return new ApiResponse("O'chirildi", true);
        }catch (Exception e){
            return new ApiResponse("O'chira olmadi", false);
        }
    }

    public Role getById(Integer id) {
        try {
            Optional<Role> byId = roleRepository.findById(id);
            Role role = byId.get();
            return role;
        }catch (Exception e){
            return null;
        }
    }
}
