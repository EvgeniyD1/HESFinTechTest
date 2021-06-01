package com.example.hes_fin_tech_test.controller;

import com.example.hes_fin_tech_test.domain.UserAccount;
import com.example.hes_fin_tech_test.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
public class MainController {

    private final UserService userService;

    public MainController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String home() {
        return "home";
    }

    @GetMapping("/list")
    public String findAllUsers(@RequestParam(required = false, defaultValue = "") String username,
                               @RequestParam(required = false, defaultValue = "") String role,
                               @PageableDefault(sort = {"id"}, direction = Sort.Direction.ASC, size = 15) Pageable pageable,
                               Model model) {
        Page<UserAccount> users = userService.findAllUsersByUsernameAndRole(pageable, username, role.toUpperCase());
        model.addAttribute("users", users);
        model.addAttribute("username", username);
        model.addAttribute("role", role);
        model.addAttribute("url", "/list?username=" + username + "&role=" + role + "&");
        return "userList";
    }

    @GetMapping("/user/{id}")
    public String userPage(@PathVariable("id") Long userId,
                           Model model){
        UserAccount user = userService.findById(userId).orElseThrow();
        model.addAttribute("user", user);
        return "userProfile";
    }

    @GetMapping("/user/{id}/edit")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String userEdit(@PathVariable("id") Long userId,
                           Model model){
        UserAccount userAccount = userService.findById(userId).orElseThrow();
        model.addAttribute("userAccount", userAccount);
        return "userProfileEditor";
    }

    @PostMapping("/user/{id}/edit")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String userEdit(@PathVariable("id") Long userId,
                           /*@RequestParam String role,
                           @RequestParam String status,*/
                           @Valid UserAccount userAccount,
                           BindingResult bindingResult,
                           Model model){
        UserAccount userFromDB = userService.findById(userId).orElseThrow();
        if (bindingResult.hasErrors()){
            Map<String, String> errorsMap = bindingResult.getFieldErrors().stream().collect(
                    Collectors.toMap(fieldError -> fieldError.getField() + "Error", FieldError::getDefaultMessage));
            model.mergeAttributes(errorsMap);
            model.addAttribute("userAccount", userAccount);
            return "userProfileEditor";
        }
        userService.save(userAccount);
        return "redirect:" + "/user/" + userId;
    }
}
