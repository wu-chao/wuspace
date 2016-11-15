package com.wuspace.controller;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wuspace.common.Const;
import com.wuspace.domain.Notice;
import com.wuspace.domain.NoticeRepository;
import com.wuspace.domain.User;
import com.wuspace.domain.UserRepository;

@Controller
public class NoticeController {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private NoticeRepository noticeRepository;
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@RequestMapping(value = "/admins/{noticeId}")
	public String adminShow(@PathVariable("noticeId") Integer noticeId, Model model) {
		
		Notice notice = noticeRepository.findOne(noticeId);
		if (notice == null) {
			
		};
		model.addAttribute("notice", notice);
		return "/admins/notice";
	}

	/**
	 * 发表公告
	 * @param adminId
	 * @param title
	 * @param content
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/notices/publish", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> publish(
			@RequestParam("adminId") Integer adminId,
			@RequestParam("title") String title,
			@RequestParam("content") String content) throws Exception {

		User admin = userRepository.findOne(adminId);
		if (admin == null) {};
		Map<String, Object> map = new HashMap<String, Object>();
		Notice post = noticeRepository.save(new Notice(admin, title,
				content, new Timestamp(new Date().getTime())));

		map.put("id", post.getId());
		map.put("createTime", post.getCreateTime());
		map.put("msg", "success");
		return map;
	}
	
	/**
	 * 删除公告
	 * @param noticeId
	 * @return
	 */
	@RequestMapping(value = "/notices/{noticeId}/delete", method = RequestMethod.GET)
	@ResponseBody
	public String delete(@PathVariable("noticeId") Integer noticeId) {
		Notice notice = noticeRepository.findOne(noticeId);
		if (notice == null) {
			System.out.println("is null .........................");
			return "fail";
		}
		noticeRepository.delete(notice);
		if (noticeRepository.exists(noticeId)) {
			return "fail";
		} else {
			return "success";
		}
	}
	
	/**
	 * 公告列表
	 * @param firstPage
	 * @param curPage
	 * @param page
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/admins/notices", method = RequestMethod.GET)
	public String notices(
			@RequestParam(value = "firstPage", defaultValue = "0") Integer firstPage,
			@RequestParam(value = "curPage", defaultValue = "0") Integer curPage,
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			Model model, 
			HttpServletRequest request) {
		
		if (request.getSession().getAttribute(Const.LOGIN_ADMIN) == null) {
			return "redirect:/posts/index";
		}

		int totalPages = 0;
		curPage = page;
		Query query = entityManager.createQuery(
				"select n from Notice n order by n.createTime desc");
		List<Notice> noticePageList = query.getResultList();
		
		int noticePageSize = noticePageList.size();
		totalPages = noticePageSize / 10;
		if (noticePageSize % 10 != 0) {
			totalPages++;
		}

		// 选择10个元素
		List<Notice> noticePage = new ArrayList<Notice>(10);
		for (int i = page * 10; i < page * 10 + 10 && i < noticePageSize; i++) {
			Notice notice = noticePageList.get(i);
			if (notice != null) {
				noticePage.add(notice);
			}
		}

		model.addAttribute("noticePage", noticePage);
		model.addAttribute("totalPages", totalPages);

		if (firstPage <= 0) {
			firstPage = 0;
		}
		if (page - firstPage >= 5 && page <= totalPages - 1) {
			firstPage++;
		}
		if (curPage < firstPage && firstPage > 0) {
			firstPage--;
		}
		
		model.addAttribute("firstPage", firstPage);
		model.addAttribute("curPage", curPage);
		return "admins/notices";
	}
	
	/**
	 * 进入公告详情页
	 * @param noticeId
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/notices/{noticeId}")
	public String show(@PathVariable("noticeId") Integer noticeId, Model model) {
		
		Notice notice = noticeRepository.findOne(noticeId);
		if (notice == null) {
			
		};
		model.addAttribute("notice", notice);
		return "notices/notice";
	}
	
	@RequestMapping(value = "/notice/{noticeId}/update", method = RequestMethod.POST)
	public String update(
			@PathVariable("noticeId") Integer noticeId, 
			@RequestParam("title") String title, 
			@RequestParam("content") String content) {
		
		Notice notice = noticeRepository.findOne(noticeId);
		if (notice == null) {
			
		}
		notice.setTitle(title.substring(1));
		notice.setContent(content.substring(1));
		noticeRepository.save(notice);
		return "redirect:/notices/" + noticeId;
	}

}
