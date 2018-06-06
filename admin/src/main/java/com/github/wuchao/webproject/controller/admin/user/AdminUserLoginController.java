package com.github.wuchao.webproject.controller.admin.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminUserLoginController {

    /**
     * /user/login
     * Ambiguous handler methods mapped for HTTP path 'http://localhost:8009/admin/login':
     *
     * @return
     */
    @GetMapping(value = {"/login"})
    public String login() {
        return "user/login";
    }

//    @RequestMapping("/admin/verificationCode")
//    public void verificationCode(HttpServletRequest request, HttpServletResponse response) {
//        //设置响应的类型格式为图片格式
//        response.setContentType("image/jpeg");
//        //禁止图像缓存
//        response.setHeader("Pragma", "no-cache");
//        response.setHeader("Cache-Control", "no-cache");
//        response.setDateHeader("Expires", 0);
//
//        HttpSession session = request.getSession();
//
//        VerificationCodeGenerator codeGenerator = new VerificationCodeGenerator(92, 34, 4, 100);
//        session.setAttribute(Const.SESSION_LOGIN_VERIFICATION_CODE, codeGenerator.getCode());
//        session.setAttribute(Const.SESSION_LOGIN_VERIFICATION_ACTIVATED_TIME_KEY, ZonedDateTime.now());
//
//        try {
//            codeGenerator.write(response.getOutputStream());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
}
