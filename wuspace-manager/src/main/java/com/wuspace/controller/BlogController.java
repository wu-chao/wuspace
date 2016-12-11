package com.wuspace.controller;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wuspace.common.Const;
import com.wuspace.domain.Blog;
import com.wuspace.domain.BlogRepository;
import com.wuspace.domain.CommentRepository;
import com.wuspace.domain.ReplyRepository;
import com.wuspace.domain.TopicType;
import com.wuspace.domain.User;
import com.wuspace.domain.UserRepository;

@Controller
public class BlogController {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BlogRepository blogRepository;

	@PersistenceContext
	private EntityManager entityManager;

	/*
	 * 分页（Page<T>形式）Controller
	 */
	@RequestMapping(value = "/blogs", method = RequestMethod.GET)
	public String index(
			@RequestParam(value = "firstResult", defaultValue = "0") Integer firstResult,
			@RequestParam(value = "prePage", defaultValue = "0") Integer prePage,
			@RequestParam(value = "curPage", defaultValue = "0") Integer curPage,
			Model model) {

		Pageable pageRequest = new PageRequest(curPage, 10, new Sort(Direction.DESC, "createdAt"));
		Page<Blog> blogPage = blogRepository.findAll(pageRequest);
		List<Blog> blogs = blogRepository.findAll();
		
		int totalPages = blogPage.getTotalPages();
		//判断分页按钮的显示方式
		if (firstResult <= 0) {
			firstResult = 0;
		}
		if (curPage - firstResult >= 5 && curPage <= totalPages - 1) {
			firstResult++;
		}
		if (curPage < firstResult && firstResult > 0) {
			firstResult--;
		}
		
		model.addAttribute("blogPage", blogPage);
		model.addAttribute("totalPages", totalPages);
		model.addAttribute("firstResult", firstResult);
		model.addAttribute("prePage", curPage);
		model.addAttribute("topicTypes", TopicType.getTopicTypes());

		return "blogs/index";
	}

	@RequestMapping(value = "/blogs/topics/{topicType}", method = RequestMethod.GET)
	public String topic(
			@RequestParam(value = "firstPage", defaultValue = "0") Integer firstPage,
			@RequestParam(value = "curPage", defaultValue = "0") Integer curPage,
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@PathVariable("topicType") Integer topicType, Model model) {

		int totalPages = 0;
		curPage = page;
		// ？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？
		// Pageable pageRequest = new PageRequest(page, 10, new
		// Sort(Direction.DESC, "createTime"));
		// Page<Blog> blogPage = blogRepository.findAllByTopicType(topicType,
		// pageRequest);
		
		
		//http://www.oschina.net/translate/getting-started-with-spring-data-jpa?cmp
		Query query = entityManager.createQuery(
				"select p from Blog p where p.topicType = ?").setParameter(1, topicType);
		List<Blog> blogPageList = query.getResultList();

		int blogPageSize = blogPageList.size();
		totalPages = blogPageSize / 10;
		if (blogPageSize % 10 != 0) {
			totalPages++;
		}

		// 选择10个元素
		List<Blog> blogPage = new ArrayList<Blog>(10);
		for (int i = page * 10; i < page * 10 + 10 && i < blogPageSize; i++) {
			Blog blog = blogPageList.get(i);
			if (blog != null) {
				blogPage.add(blog);
			}
		}

		model.addAttribute("blogPage", blogPage);
		model.addAttribute("totalPages", totalPages);
		model.addAttribute("topicTypes", TopicType.getTopicTypes());

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
		model.addAttribute("topicType", topicType);

		return "blogs/topic";
	}

	/**
	 * 帖子详情页
	 * 
	 * @param blogId
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/blogs/{blogId}", method = RequestMethod.GET)
	public String show(
			@PathVariable("blogId") Integer blogId,
			HttpServletRequest request, 
			Model model) {

		boolean isZan = false;
		boolean isCollect = false;
		boolean isCare = false;
		
		Blog blog = blogRepository.findBlogWithAllById(blogId);
		if (blog == null) {
			
		}
		blog.visited();
		blogRepository.save(blog);
		model.addAttribute("blog", blog);
		model.addAttribute("comments", blog.getComments());
		
		User loginUser = null;
		if ((loginUser = (User) request.getSession().getAttribute("loginUser")) != null) {
			isZan = blog.existsZanUser(loginUser);
			isCollect = blog.existsCollectUser(loginUser);
			isCare = loginUser.existsCareUser(blog.getUser());
		}
		
		//侧边栏-最新发表
		Query query = entityManager.createQuery("select p from Blog p order by p.createdAt desc").setFirstResult(0).setMaxResults(5);
		List<Blog> newestBlogs = query.getResultList();
		model.addAttribute("newestBlogs", newestBlogs);
		model.addAttribute("topicTypes", TopicType.getTopicTypes());
		model.addAttribute("isZan", isZan);
		model.addAttribute("isCollect", isCollect);
		model.addAttribute("isCare", isCare);
		return "blogs/show";
	}
	
	@RequestMapping(value = "/blogs/publish", method = RequestMethod.GET)
	public String publish(Model model) {
		return "blogs/editor";
	}

	@RequestMapping(value = "/blogs/publish", method = RequestMethod.POST)
	public String publish() {


		return "redirect:/blogs";
	}
	
	@RequestMapping(value = "/blogs/{blogId}/update", method = RequestMethod.GET)
	public String update(@PathVariable("blogId") Integer blogId, Model model) {
		Blog blog = blogRepository.findOne(blogId);
		if (blog == null) {}
		model.addAttribute("blog", blog);
		model.addAttribute("topicTypes", TopicType.getTopicTypes());
		return "/blogs/update";
	}
	
	@RequestMapping(value = "/blogs/{blogId}/update", method = RequestMethod.POST)
	public String update(@PathVariable("blogId") Integer blogId, 
			@RequestParam("title") String title, 
			@RequestParam("content") String content) {
		
		Blog blog = blogRepository.findOne(blogId);
		if (blog == null) {}
		blog.updateBlog(title, content);
		blogRepository.save(blog);
		return "redirect:/blogs/" + blogId;
	}

	/**
	 * 删除帖子
	 * 
	 * @param blogId
	 * @return
	 */
	@RequestMapping(value = "/blogs/{blogId}/delete", method = RequestMethod.GET)
	@ResponseBody
	public String delete(@PathVariable("blogId") Integer blogId)
			throws Exception {

		String msg = "fail";
		blogRepository.delete(blogId);

		return msg;
	}

	/**
	 * 点赞
	 * 
	 * @param blogId
	 * @param userId
	 * @return
	 */
	@RequestMapping(value = "/blogs/{blogId}/zan", method = RequestMethod.POST)
	@ResponseBody
	public String zan(@PathVariable("blogId") Integer blogId,
			@RequestParam("userId") Integer userId) {

		Blog blog = blogRepository.findBlogWithZanusersById(blogId);
		User loginUser = userRepository.findOne(userId);

		if (loginUser == null) {
			System.out.println("user is null");
		}
		if (blog == null) {
			System.out.println("blog is null");
		}

		if (!blog.existsZanUser(loginUser)) {
			blog.addZanUser(loginUser);
			blogRepository.save(blog);
		}

		return "success";
	}

	/**
	 * 收藏
	 * 
	 * @param blogId
	 * @param userId
	 * @return
	 */
	@RequestMapping(value = "/blogs/{blogId}/collect", method = RequestMethod.POST)
	@ResponseBody
	public String collect(@PathVariable("blogId") Integer blogId,
			@RequestParam("userId") Integer userId) {

		Blog blog = blogRepository.findBlogWithCollectusersById(blogId);
		User loginUser = userRepository.findOne(userId);

		if (loginUser == null) {
			System.out.println("user is null");
		}
		if (blog == null) {
			System.out.println("blog is null");
		}

		if (!blog.existsCollectUser(loginUser)) {
			blog.addCollectUser(loginUser);
			blogRepository.save(blog);
		}

		return "success";
	}
	
	@RequestMapping(value = "/blogs/{blogId}/cancelCollect", method = RequestMethod.GET)
	@ResponseBody
	public String cancelCollect(
			@PathVariable("blogId") Integer blogId, 
			@RequestParam("userId") Integer userId) {
		
		Blog blog = blogRepository.findBlogWithCollectusersById(blogId);
		User user = userRepository.findOne(userId);
		if (blog == null || user == null) {
			
		}
		blog.removeCollectUser(user);
		//多对多的删除要刷新，对象的删除为什么不要，还有reply的删除
		blogRepository.save(blog);
		return "success";
	}

}
