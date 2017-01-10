package com.wuspace.service.impl;

import com.wuspace.common.Const;
import com.wuspace.domain.security.User;
import com.wuspace.domain.security.UserRepository;
import com.wuspace.service.UserService;
import com.wuspace.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	public Map<String, Object> register(String account, String password) {
		Map<String, Object> map = new HashMap<String, Object>();
		String msg = "error";

		if (!StringUtils.isEmpty(account) && !StringUtils.isEmpty(password)) {
			if (!userRepository.existsByAccount(account)) {
				User user = new User();
				if (account.contains("@")) {
					user.setEmail(account);
				}
				user.setNickname(account);
				user.setPassword(password);

				Integer avatarIndex = (int) (Math.random() * 29) + 1;
				user.setAvatar("avatar_" + avatarIndex + ".jpg");
				user.setCreatedAt(new Timestamp(new Date().getTime()));
				userRepository.save(user);
				msg = "success";
			} else {
				msg = "isexist";
			}
		}

		map.put("msg", msg);
		return map;
	}

	/**
	 * 登录
	 * 
	 */
	public Map<String, Object> login(String account, String password,
			Integer cookieMark, HttpServletRequest request,
			HttpServletResponse response) {

		Map<String, Object> map = new HashMap<String, Object>();
		String msg = "error";

		if (!StringUtils.isEmpty(account) && !StringUtils.isEmpty(password)) {
			if (!userRepository.existsByAccount(account)) {
				map.put("msg", "notexist");
				return map;
			}

			User user = userRepository
					.findUserWithCareusersByAccountAndPassword(account,
							password);
			if (user != null) {
				request.getSession().setAttribute(Const.LOGIN_USER, user);
				msg = "success";
				// 设置Cookie
				// Cookie cookie = new Cookie(Const.LOGIN_USER,
				// StringUtils.encryption(account, 256));
				// cookie.setMaxAge(7 * 24 * 60 * 60); //7天
				// response.addCookie(cookie);
			}
		}

		map.put("msg", msg);
		return map;
	}

	/**
	 * 退出登录
	 * 
	 */
	public void logout(HttpServletRequest request, HttpServletResponse response) {
		// 注销Session
		request.getSession().invalidate();
		// 注销Cookie
		Cookie cookie = new Cookie(Const.LOGIN_USER, null);
		cookie.setMaxAge(0);
		response.addCookie(cookie);
	}
}
