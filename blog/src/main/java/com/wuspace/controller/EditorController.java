package com.wuspace.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class EditorController {
	
	@RequestMapping(value = "/editors/editor", method = RequestMethod.GET)
	public String editor(@RequestParam("ajaxUrlPrefix") String ajaxUrlPrefix, Model model) {
		model.addAttribute("ajaxUrlPrefix", ajaxUrlPrefix);
		return "editors/editor";
	}
	
}
