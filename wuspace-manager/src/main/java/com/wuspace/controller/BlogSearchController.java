package com.wuspace.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.wuspace.server.util.StringUtils;

@Controller
public class BlogSearchController {

	@RequestMapping(value = "/posts/search", method = RequestMethod.GET)
	public String search(@RequestParam(value = "firstPage", defaultValue = "0") Integer firstPage,
			@RequestParam(value = "curPage", defaultValue = "0") Integer curPage,
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam("title") String title,
			Model model) throws Exception {
		
		//解决key乱码问题
		title = new String(title.getBytes("iso8859-1"),"utf-8");
		if (StringUtils.isEmpty(title)) {
			return "redirect:/posts/index";
		}
		return "posts/search";
	}
}
