package com.wuspace.application;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface UploadService {

	String upload(HttpServletRequest request, HttpServletResponse response);

	String uploadAvatar(HttpServletRequest request, HttpServletResponse response) throws Exception;

}
