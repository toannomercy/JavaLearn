package org.example.javalt.Controllers;

import org.example.javalt.Entities.Role;
import org.example.javalt.RequestEntities.RequestCreateRole;
import org.example.javalt.RequestEntities.RequestDeleteRole;
import org.example.javalt.Services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/roles")
public class RoleController {
    @Autowired
    private RoleService roleService;
    @GetMapping("")
    public String ShowAllRoles(Model model) {
        List<Role> roles = roleService.findAllActiveRoles();
        model.addAttribute("roles", roles);
        return "Role/Index";
    }
    @GetMapping("/new")
    public String createForm(Model model){
        Role role = new Role();
        model.addAttribute("role", role);
        return "Role/Create";
    }
    @PostMapping("/create")
    public String SaveCreateRole(@ModelAttribute RequestCreateRole requestCreateRole){
        roleService.createRole(requestCreateRole);
        return "redirect:/roles";
    }
    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable String id, Model model){
        Role role = roleService.getRoleById(id);
        model.addAttribute("role", role);
        return "Role/Edit";
    }
    @PostMapping("/update/{id}")
    public String SaveUpdateRole(@PathVariable String id, @ModelAttribute RequestCreateRole requestCreateRole){
        roleService.updateRole(id, requestCreateRole);
        return "redirect:/roles";
    }
    @GetMapping("/delete/{id}")
    public String deleteRole(@PathVariable String id) {
        RequestDeleteRole requestDeleteRole = new RequestDeleteRole(id);
        roleService.deleteRoleById(requestDeleteRole);
        return "redirect:/roles";
    }
}
