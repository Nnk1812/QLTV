package com.practice.QLTV.Service;

import com.practice.QLTV.DTO.Request.RoleRequest;
import com.practice.QLTV.DTO.Response.RoleResponse;
import com.practice.QLTV.Entity.Role;
import com.practice.QLTV.Mapper.Mapper;
import com.practice.QLTV.Repository.RoleRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RoleService {
    Mapper mapper;
    RoleRepository roleRepository;
    public Role createRole(RoleRequest roleRequest) {
        Role role = mapper.toRole(roleRequest);
        return roleRepository.save(role);
                /*mapper.toRole(roleRepository.save(role));*/
    }
    public List<Role> getall(){
        return (roleRepository.findAll());
    }
    public Role getRolebyName(String roleName){
        return roleRepository.findByRoleName(roleName).orElseThrow(()-> new RuntimeException("Rolename not exists"));
    }
    public Role updateRole(RoleRequest roleRequest,String roleName) {
        roleRepository.findByRoleName(roleName).orElseThrow(()-> new RuntimeException("Rolename not exists"));
        Role role = mapper.toRole(roleRequest);
        return roleRepository.save(role);
    }
    public String deleteRole(String roleName) {
        roleRepository.findByRoleName(roleName).orElseThrow(() -> new RuntimeException("Rolename not exists") );
        roleRepository.deleteById(roleName);
        return "Role have been delete";
    }
}
