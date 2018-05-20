package com.wuspace.config.filter;

import com.wuspace.common.Const;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.ZonedDateTime;

/**
 * 验证码过滤器
 */
@Component
public class VerificationCodeAuthenticationFilter extends GenericFilterBean {

    private String verificationCodeParameter = Const.REQUEST_LOGIN_VERIFICATION_CODE_PARAMETER;
    private String verificationCode = Const.SESSION_LOGIN_VERIFICATION_CODE;
    private String verificationCodeActivatedTime = Const.SESSION_LOGIN_VERIFICATION_ACTIVATED_TIME_KEY;
    private int verificationTimeout = 60; //单位：s
    private String loginProcessingUrl = "/authentication";
    private SimpleUrlAuthenticationFailureHandler failureHandler;

    public VerificationCodeAuthenticationFilter() {
    }

    public VerificationCodeAuthenticationFilter(SimpleUrlAuthenticationFailureHandler failureHandler) {
        this.failureHandler = failureHandler;
    }

    public VerificationCodeAuthenticationFilter(String loginProcessingUrl, SimpleUrlAuthenticationFailureHandler failureHandler) {
        this.loginProcessingUrl = loginProcessingUrl;
        this.failureHandler = failureHandler;
    }

    public VerificationCodeAuthenticationFilter(String loginProcessingUrl, SimpleUrlAuthenticationFailureHandler failureHandler, String verificationCodeParameter, int verificationTimeout) {
        this.loginProcessingUrl = loginProcessingUrl;
        this.failureHandler = failureHandler;
        this.verificationCodeParameter = verificationCodeParameter;
        this.verificationTimeout = verificationTimeout;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;

        if (StringUtils.isEmpty(loginProcessingUrl)) {
            setLoginProcessingUrl("/authentication");
        }

        if (loginProcessingUrl.equals(httpRequest.getRequestURI())) {
            String verificationCode = obtainVerificationCode(request);

            if (verificationCode == null) {
                verificationCode = "";
            }

            verificationCode = verificationCode.trim();

            HttpSession session = httpRequest.getSession();
            String sessionVerificationCode = String.valueOf(session.getAttribute(this.verificationCode));
            if (verificationCode.equalsIgnoreCase(sessionVerificationCode)) {
                ZonedDateTime time = (ZonedDateTime) session.getAttribute(this.verificationCodeActivatedTime);
                if (time == null || time.plusSeconds(this.verificationTimeout).isBefore(ZonedDateTime.now())) {
                    //验证码已过期
                    if (logger.isDebugEnabled()) {
                        logger.error("the verification code is timeout.");
                    }
                    failureHandler.onAuthenticationFailure(httpRequest, (HttpServletResponse) response, new AuthenticationServiceException("verification timeout"));
                    return;
                }
            } else {
                //验证码错误
                if (logger.isDebugEnabled()) {
                    logger.error("the verification code is wrong.");
                }
                failureHandler.onAuthenticationFailure(httpRequest, (HttpServletResponse) response, new AuthenticationServiceException("verification wrong"));
                return;
            }

        }

        chain.doFilter(request, response);
    }

    protected String obtainVerificationCode(ServletRequest request) {
        return request.getParameter(verificationCodeParameter);
    }

    public String getVerificationCode() {
        return verificationCode;
    }

    public void setVerificationCode(String verificationCodeValue) {
        this.verificationCode = verificationCode;
    }

    public String getVerificationCodeActivatedTime() {
        return verificationCodeActivatedTime;
    }

    public void setVerificationCodeActivatedTime(String verificationCodeActivatedTime) {
        this.verificationCodeActivatedTime = verificationCodeActivatedTime;
    }

    public int getVerificationTimeout() {
        return verificationTimeout;
    }

    public void setVerificationTimeout(int verificationTimeout) {
        this.verificationTimeout = verificationTimeout;
    }

    public String getLoginProcessingUrl() {
        return loginProcessingUrl;
    }

    public void setLoginProcessingUrl(String loginProcessingUrl) {
        this.loginProcessingUrl = loginProcessingUrl;
    }
}
