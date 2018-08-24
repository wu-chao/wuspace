package com.github.wuchao.webproject.controller.app;

import com.github.wuchao.webproject.repository.UserRepository;
import com.github.wuchao.webproject.util.JacksonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class BaseController {

    private final static Logger log = LoggerFactory.getLogger(BaseController.class);

    @Autowired
    private UserRepository userRepository;

    @ModelAttribute
    public void testELK() {

        while (true) {
            try {
                Thread.sleep(10000);

                userRepository.findOneWithAuthoritiesByUsername("admin").ifPresent(user -> {

                    log.warn(user.toString());
                    log.error(JacksonUtil.serializeAsString(user));

                });

                log.warn("aaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
                log.error("bbbbbbbbbbbbbbbbbbbbbbbbbbbbb");

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

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
