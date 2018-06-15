package com.github.wuchao.webproject.controller.app;

import com.github.wuchao.webproject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class BaseController {

    @Autowired
    private UserRepository userRepository;

//    @ModelAttribute("currentUser")
//    public User getCurrentUser() {
//        String username;
//        try {
//            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//
//            if (principal instanceof UserDetails) {
//                username = ((UserDetails) principal).getUsername();
//            } else {
//                username = principal.toString();
//            }
//
//            Optional<User> user = userRepository.findOneByUsername(username);
//
//            return user.isPresent() ? user.get() : null;
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
//    }

    @ModelAttribute("fileAccessPrefix")
    public String fileAccessPrefix() {
        return "/";
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ModelAndView handleException(RuntimeException e) {
        System.out.println("exception message : " + e.getClass() + " : " + e.getMessage());

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("error/404");
        return modelAndView;
    }
}
