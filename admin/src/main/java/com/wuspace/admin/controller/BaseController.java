package com.wuspace.admin.controller;

import com.wuspace.domain.User;
import com.wuspace.domain.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.Optional;

@Controller
public class BaseController {

    @Autowired
    private UserRepository userRepository;

    @ModelAttribute
    public void getCurrentUser(Model model) {
        String username;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else {
            username = principal.toString();
        }

        Optional<User> user = userRepository.findOneByUsername(username);
        model.addAttribute("user", user);
    }
}
