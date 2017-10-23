package com.wuspace.controller.blogs;

import com.wuspace.commons.domain.User;
import com.wuspace.commons.mapper.UserMapper;
import com.wuspace.commons.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.Optional;

@ControllerAdvice
@Component(value = "blogBaseController")
public class BaseController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @ModelAttribute("currentUser")
    public User getCurrentUser() {
        User userOptional = userMapper.findOneWithAuthoritiesByUsername("user");

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
