package org.example.javalt.Controllers;

import org.example.javalt.Entities.Role;
import org.example.javalt.Entities.User;
import org.example.javalt.Repositories.RoleRepository;
import org.example.javalt.Repositories.UserRepository;
import org.example.javalt.RequestEntities.RequestCreateUser;
import org.example.javalt.RequestEntities.RequestDeleteUser;
import org.example.javalt.RequestEntities.RequestUpdateUser;
import org.example.javalt.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;

    @Autowired
    public UserController(RoleRepository roleRepository, UserRepository userRepository) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
    }

    @GetMapping("")
    public String ShowAllUsers(Model model) {
        List<User> users = userService.findAllActiveUsersWithActiveRoles();
        model.addAttribute("users", users);
        return "User/Index";
    }

    @GetMapping("/new")
    public String createForm(Model model){
        User user = new User();
        List<Role> roles = roleRepository.findAll();
        model.addAttribute("user", user);
        model.addAttribute("roles", roles);
        return "User/Create";
    }

    @PostMapping("/create")
    public String SaveCreateUser(@ModelAttribute RequestCreateUser requestCreateUser){
        Role role = requestCreateUser.getRole();
        if (role == null || roleRepository.findById(role.getRole_id()).isEmpty()) {
            throw new IllegalArgumentException("Invalid role Id: " + (role != null ? role.getRole_id() : "null"));
        }
        userService.createUser(requestCreateUser);
        return "redirect:/users";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable String id, Model model){
        User user = userService.getUserById(id);
        List<Role> roles = roleRepository.findAll();
        model.addAttribute("user", user);
        model.addAttribute("roles", roles);
        return "User/Edit";
    }

    @PostMapping("/update/{id}")
    public String SaveUpdateUser(@PathVariable String id, @ModelAttribute RequestUpdateUser requestUpdateUser){
        Role role = requestUpdateUser.getRole();
        if (role == null || roleRepository.findById(role.getRole_id()).isEmpty()) {
            throw new IllegalArgumentException("Invalid role Id: " + (role != null ? role.getRole_id() : "null"));
        }
        userService.updateUser(id, requestUpdateUser);
        return "redirect:/users";
    }
    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable String id) {
        RequestDeleteUser requestDeleteUser = new RequestDeleteUser(id);
        userService.deleteUserById(requestDeleteUser);
        return "redirect:/users";
    }
}