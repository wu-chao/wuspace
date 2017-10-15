package com.wuspace.controller;

import com.wuspace.commons.domain.User;
import com.wuspace.commons.repository.UserRepository;
import com.wuspace.controller.blogs.BlogCreateController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.Optional;

@ControllerAdvice(basePackageClasses = {BlogCreateController.class})
public class BaseController {

    @Autowired
    private UserRepository userRepository;

    @ModelAttribute("currentUser")
    public User getCurrentUser() {
        String username;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else {
            username = principal.toString();
        }

        Optional<User> user = userRepository.findOneByUsername(username);

        return user.isPresent() ? user.get() : null;
    }
}
