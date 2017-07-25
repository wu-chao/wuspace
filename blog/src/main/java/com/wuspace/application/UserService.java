package com.wuspace.application;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface UserService {

	void logout(HttpServletRequest request, HttpServletResponse response);

	Map<String, Object> login(String account, String password,
							  Integer cookieMark, HttpServletRequest request,
							  HttpServletResponse response);

	Map<String, Object> register(String account, String password);

}