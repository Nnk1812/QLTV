package com.practice.QLTV.Controller;

import com.practice.QLTV.DTO.Request.RoleRequest;
import com.practice.QLTV.DTO.Response.APIResponse;
import com.practice.QLTV.Entity.Role;
import com.practice.QLTV.Service.RoleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/role")
public class RoleController {
    @Autowired
    private RoleService roleService;

    @PostMapping
    APIResponse<Role> createRole(@RequestBody @Valid RoleRequest request) {
        APIResponse<Role> role = new APIResponse<>();
        role.setData(roleService.createRole(request));
        return role;
    }
    @GetMapping
    List<Role> getAllRoles() {
        return roleService.getall();
    }
    @GetMapping("/{roleName}")
    Role getRoleById(@PathVariable String roleName) {
        return roleService.getRolebyName(roleName);
    }
    @PutMapping("/{roleName}")
    Role updateRole(@RequestBody RoleRequest request,@PathVariable String roleName) {
        return roleService.updateRole(request,roleName);
    }
    @DeleteMapping("/{roleName}")
    String deleteRole(@PathVariable String roleName) {
        return roleService.deleteRole(roleName);
    }

}
