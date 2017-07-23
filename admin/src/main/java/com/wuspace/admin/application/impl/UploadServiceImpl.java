package com.wuspace.admin.application.impl;

import java.io.File;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wuspace.admin.application.UploadService;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.stereotype.Service;

@Service
public class UploadServiceImpl implements UploadService {

	public String upload(HttpServletRequest request,
			HttpServletResponse response) {

		String path = request.getServletContext().getRealPath("/upload/images");
		File file = new File(path);
		if (!file.exists()) {
			file.mkdirs();
		}
		String fileName = "";// 文件名称

		/** 上传文件处理内容 **/
		DiskFileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload sfu = new ServletFileUpload(factory);
		sfu.setHeaderEncoding("UTF-8"); // 处理中文问题
		sfu.setSizeMax(1024 * 1024); // 限制文件大小
		List<FileItem> fileItems;
		try {
			fileItems = sfu.parseRequest(request);
			for (FileItem fi : fileItems) {
				fileName = UUID.randomUUID()
						+ fi.getName().substring(fi.getName().lastIndexOf("."),
								fi.getName().length());
				fi.write(new File(path, fileName));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		String imgUrl = "/blog/upload/images/" + fileName;
		response.setContentType("text/text;charset=utf-8");

		return imgUrl;
	}

	public String uploadAvatar(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		String path = request.getServletContext().getRealPath("/resources/images/avatars");
		File file = new File(path);
		if (!file.exists()) {
			file.mkdirs();
		}
		String fileName = "";// 文件名称

		// 建立磁盘文件项目工厂类
		FileItemFactory fileItemFactory = new DiskFileItemFactory();
		// 创建文件上传处理类
		ServletFileUpload servletFileUpload = new ServletFileUpload(
				fileItemFactory);
		// 设置文件上传所容许的最大文件大小
		// servletFileUpload.setSizeMax(maxFileSize);
		// 防止中文文件名乱码
		servletFileUpload.setHeaderEncoding("utf-8");

		// 处理获取的文件
		List<FileItem> fileItems = null; // fileItems.iterator()相当于查询数据库中的ResultSet
		try {
			// 解析获取的文件
			fileItems = servletFileUpload.parseRequest(request);
			if (fileItems != null) {
				Iterator<FileItem> fileItemsIterator = fileItems.iterator();
				while (fileItemsIterator.hasNext()) {
					FileItem fileItem = (FileItem) fileItemsIterator.next();
					//
					if (fileItem.isFormField()) {
						continue;
					} else {
						fileName = fileItem.getName();
						// 上传的文件上传到服务器中的目标文件
						File savedFile = new File(path, fileName);
						// 从容器的临时目录下写到（复制）到服务器的具体（目标）目录下
						fileItem.write(savedFile);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		response.setContentType("text/text;charset=utf-8");
		System.out.println(fileName);
		return fileName;
	}
}
