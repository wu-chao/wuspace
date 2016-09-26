package com.wuspace.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wuspace.common.Const;
import com.wuspace.model.Blog;
import com.wuspace.model.BlogRepository;
import com.wuspace.model.TopicType;
import com.wuspace.model.User;
import com.wuspace.model.UserRepository;
import com.wuspace.service.UploadService;
import com.wuspace.service.UserService;
import com.wuspace.util.StringUtils;

@Controller
public class UserController {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserService userServiceImpl;
	
	@Autowired
	private BlogRepository blogRepository;
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Autowired
	private UploadService uploadServiceImpl;
	
	/*管理员操作*/
	@RequestMapping(value = "/admins/adminLogin", method = RequestMethod.GET)
	public String adminLogin() {
		return "admins/login";
	}
	
	@RequestMapping(value = "/admins/adminLogin", method = RequestMethod.POST)
	public String adminLogin(@ModelAttribute("email") String email, 
			@ModelAttribute("password") String password, HttpServletRequest request) {
		
		if (!StringUtils.isEmpty(email) && !StringUtils.isEmpty(password)) {
			User user = userRepository.findUserWithCareusersByAccountAndPassword(email, password);
			if (user != null && user.getIsAdmin() == 1) {
				HttpSession session = request.getSession();
				session.setAttribute(Const.LOGIN_ADMIN, user);
				session.setAttribute(Const.LOGIN_USER, user);
				return "redirect:/admins/notices";
			}
		}
		return "redirect:/blogs/index";
	}
	
	@RequestMapping(value = "/admins/adminUsers", method = RequestMethod.GET)
	public String adminUsers(@RequestParam(value = "firstPage", defaultValue = "0") Integer firstPage,
			@RequestParam(value = "curPage", defaultValue = "0") Integer curPage,
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			Model model, HttpServletRequest request) {
		
		if (request.getSession().getAttribute(Const.LOGIN_ADMIN) == null) {
			return "redirect:/blogs/index";
		}

		int totalPages = 0;
		curPage = page;
		Pageable pageRequest = new PageRequest(page, 10, new Sort(Direction.DESC, "createTime"));
		Page<User> userPage = userRepository.findAll(pageRequest);
		totalPages = userPage.getTotalPages();
		model.addAttribute("userPage", userPage);
		model.addAttribute("totalPages", totalPages);
		
		if (page - firstPage >= 5 && page <= totalPages - 1) {
			firstPage++;
		}
		if (curPage < firstPage && firstPage > 0) {
			firstPage--;
		}
		
		model.addAttribute("firstPage", firstPage);
		model.addAttribute("curPage", curPage);
		return "admins/users";
	}
	
	@RequestMapping(value = "/adminUsers/{userId}", method = RequestMethod.GET)
	public String adminShow(@PathVariable("userId") Integer userId, Model model) {
		User user = userRepository.findAllById(userId);
		if (user == null) {
			
		}
		model.addAttribute("user", user);
		model.addAttribute("newUser", new User());
		return "admins/user";
	}
	
	@RequestMapping(value = "/users/{userId}/delete", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> adminDelete(@PathVariable("userId") Integer userId) {
		User user = userRepository.findOne(userId);
		if (user == null) {
			
		}
		
		Map<String, Object> map = new HashMap<String, Object>();
		String msg = "fail";
		
		userRepository.delete(userId);
		if (!userRepository.exists(userId)) {
			msg = "success";
		}
		
		map.put("msg", msg);
		return map;
	}

	/*用户操作*/
	@RequestMapping(value = "/users/register", method = RequestMethod.GET)
	public String register() {
		return "users/register";
	}
	
	@RequestMapping(value = "/users/register", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> register(@RequestParam("account") String account, 
			@RequestParam("password") String password) {
		
		return userServiceImpl.register(account, password);
	}

	@RequestMapping(value = "/users/login", method = RequestMethod.GET)
	public String login() {
		return "users/login";
	}
	
	@RequestMapping(value = "users/validate", method = RequestMethod.GET)
	@ResponseBody
	public String validateAccount(@RequestParam("account") String account) throws Exception {
		
		String id = "0";
		if (!StringUtils.isEmpty(account)) {
			account = new String(account.getBytes("iso8859-1"),"utf-8");
			User user = userRepository.findByAccount(account);
			if (user != null) {
				id = user.getId().toString();
			}
		}
		
		return id;
	}
	
	@RequestMapping(value = "/users/login", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> login(@RequestParam("account") String account, 
			@RequestParam("password") String password, 
			@RequestParam("cookieMark") Integer cookieMark, 
			HttpServletRequest request, HttpServletResponse response) {

		return userServiceImpl.login(account, password, cookieMark, request, response);
	}
	
	/**
	 * 退出登录
	 * @param userId
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/users/{userId}/logout", method = RequestMethod.GET)
	public String logout(@PathVariable("userId") Integer userId, 
			HttpServletRequest request, HttpServletResponse response){
		
		userServiceImpl.logout(request, response);
		return "redirect:/blogs/index";
	}
	
	/**
	 * 个人中心
	 * @param userId
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/users/{userId}", method = RequestMethod.GET)
	public String show(
			@RequestParam(value = "firstResult", defaultValue = "0") Integer firstResult,
			@RequestParam(value = "prePage", defaultValue = "0") Integer prePage,
			@RequestParam(value = "curPage", defaultValue = "0") Integer curPage,
			@PathVariable("userId") Integer userId, 
			Model model, 
			HttpServletRequest request) {
		
		boolean isSelf = false;
		boolean isCare = false;
		User loginUser = null;
		User user = userRepository.findOne(userId);
		
		if (user == null) {
			
		}
		if ((loginUser = (User) request.getSession().getAttribute(Const.LOGIN_USER)) != null) {
			isCare = loginUser.existsCareUser(user);
		}
		//用户已登录且打开的个人中心是自己的个人中心
		//两个Integer对象之间比较相等
		if (loginUser != null && loginUser.getId().equals(userId)) {
			isSelf = true;
		} else {
			Pageable pageRequest = new PageRequest(curPage, 10, new Sort(Direction.DESC, "createTime"));
			Page<Blog> blogPage = blogRepository.findByUser(user, pageRequest);
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
		}
		
		
		model.addAttribute("isSelf", isSelf);
		model.addAttribute("isCare", isCare);
		model.addAttribute("user", user);
		model.addAttribute("userId", userId);
		return "users/show";
	}
	
	/**
	 * 修改个人信息
	 * @param userId
	 * @param newUser
	 * @return
	 */
	@RequestMapping(value = "users/{userId}/update", method = RequestMethod.POST)
	public String update(
			@PathVariable("userId") Integer userId, 
			@RequestParam("nickname") String nickname, 
			@RequestParam("email") String email, 
			@RequestParam("phone") String phone, 
			@RequestParam("description") String description) {
		
		User user = userRepository.findOne(userId);
		if (user == null) {
			
		}

		//email/phone/description前面自动出现“,”？？？？？？？？？？？？？？？？？？？？
		//System.out.println("11111111111111:"+email+"==="+phone+"==="+description);
		user.updateInfo(nickname.substring(1), email.substring(1), phone.substring(1), description.substring(1));
		//打印多条语句？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？
		userRepository.save(user);
		
		return "redirect:/users/" + userId;
	}
	
	@RequestMapping(value = "/users/{userId}/updatePassword", method = RequestMethod.POST)
	@ResponseBody
	public String updatePassword(
			@PathVariable("userId") Integer userId, 
			@RequestParam("oldPassword") String oldPassword, 
			@RequestParam("newPassword") String newPassword) {
		
		User user = userRepository.findOne(userId);
		if (user == null) {}
		if (user.validatePassword(oldPassword)) {
			user.updatePassword(newPassword);
			userRepository.save(user);
			return "success";
		} else {
			return "fail";
		}
	}
	
	@RequestMapping(value = "/users/{userId}/uploadAvatar")
	@ResponseBody
	public String uploadAvatar(
			@PathVariable("userId") Integer userId, 
			HttpServletRequest request, 
			HttpServletResponse response) throws Exception {
		
		User user = userRepository.findOne(userId);
		if (user == null) {
			
		}

		String avatarfileName = uploadServiceImpl.uploadAvatar(request, response);
		user.setAvatar(avatarfileName);
		userRepository.save(user);
		
		return "http://localhost:8080/blog/resources/images/avatars/" + avatarfileName;
	}
	
	@RequestMapping(value = "/users/{userId}/care/{blogUserId}", method = RequestMethod.POST)
	@ResponseBody
	public String care(
			@PathVariable("userId") Integer userId, 
			@PathVariable("blogUserId") Integer blogUserId, 
			HttpServletRequest request) {
		
		User user = userRepository.findUserWithCareusersById(userId);
		User caredUser = userRepository.findOne(blogUserId);
		if (user == null || caredUser == null) {
			
		}

		if (!user.existsCareUser(caredUser)) {
			user.addCareUser(caredUser);
			userRepository.save(user);
			User loginUser = (User)request.getSession().getAttribute(Const.LOGIN_USER);
			loginUser.addCareUser(caredUser);
			return "success";
		} else {
			return "fail";
		}
	}
	
	@RequestMapping(value = "/users/{userId}/cancelCare/{caredUserId}", method = RequestMethod.GET)
	@ResponseBody
	public String cancelCare(
			@PathVariable("userId") Integer userId, 
			@PathVariable("caredUserId") Integer caredUserId, 
			HttpServletRequest request) {
		
		User user = userRepository.findUserWithCareusersById(userId);
		User caredUser = userRepository.findOne(caredUserId);
		if (user == null || caredUser == null) {
			
		}

		if (user.existsCareUser(caredUser)) {
			user.removeCareUser(caredUser);
			userRepository.save(user);
			
			//user与loginUser是同一个对象，但是。。。。。。。？？？？？？？？？？？？？？？？？？？？？？？？？
			//加关注时也一样
			User loginUser = (User)request.getSession().getAttribute(Const.LOGIN_USER);
			loginUser.removeCareUser(caredUser);
			return "success";
		} else {
			return "fail";
		}
	}
	
	/**
	 * 分页（List<T>形式）Controller
	 */
	@RequestMapping(value = "/users/{userId}/blogs", method = RequestMethod.GET)
	public String blogs(
		@RequestParam(value = "firstResult", defaultValue = "0") Integer firstResult,
		@RequestParam(value = "prePage", defaultValue = "0") Integer prePage,
		@RequestParam(value = "curPage", defaultValue = "0") Integer curPage,
		@PathVariable("userId") Integer userId,
		Model model) {

		Query query = entityManager.createQuery("select u.blogs from User u where u.id = ?").setParameter(1, userId);
		//List<blog> blogPage = query.setFirstResult(page).setMaxResults(10).getResultList();
		//totalPages = query.getResultList().size();java.lang.IllegalStateException: EntityManager is closed
		List<Blog> blogPageList = query.getResultList();
		int blogPageSize = blogPageList.size();
		int totalPages = blogPageSize / 10;
		if (blogPageSize % 10 != 0) {
			totalPages++;
		}
		
		//选择要显示的10个元素
		List<Blog> blogPage = new ArrayList<Blog>(10);
		for (int i = curPage * 10; i < curPage * 10 + 10 && i < blogPageSize; i++) {
			Blog blog = blogPageList.get(i);
			if (blog != null) {
				blogPage.add(blog);
			}
		}
		
		if (firstResult <= 0) {
			firstResult = 0;
		}
		if (curPage - firstResult >= 5 && curPage <= totalPages - 1) {
			firstResult++;
		}
		if (prePage < firstResult && firstResult > 0) {
			firstResult--;
		}
		
		model.addAttribute("blogPage", blogPage);
		model.addAttribute("topicTypes", TopicType.getTopicTypes());
		model.addAttribute("totalPages", totalPages);
		model.addAttribute("firstResult", firstResult);
		model.addAttribute("prePage", curPage);
		
		return "users/blogs";
	}
	
	@RequestMapping(value = "/users/{userId}/collects", method = RequestMethod.GET)
	public String collects(
		@RequestParam(value = "firstPage", defaultValue = "0") Integer firstPage,
		@RequestParam(value = "curPage", defaultValue = "0") Integer curPage,
		@RequestParam(value = "page", defaultValue = "0") Integer page, 
		@PathVariable("userId") Integer userId,
		Model model) {

		int totalPages = 0;
		curPage = page;
		Query query = entityManager.createQuery("select u.collectBlogs from User u where u.id = ?").setParameter(1, userId);
		List<Blog> blogPageList = query.getResultList();
		
		int blogPageSize = blogPageList.size();
		totalPages = blogPageSize / 10;
		if (blogPageSize % 10 != 0) {
			totalPages++;
		}
		
		//选择10个元素
		List<Blog> blogPage = new ArrayList<Blog>(10);
		for (int i = page * 10; i < page * 10 + 10 && i < blogPageSize; i++) {
			Blog blog = blogPageList.get(i);
			if (blog != null) {
				blogPage.add(blog);
			}
		}

		/*当收藏的帖子数少于5个时，firstPage是负数？？？？？？？？？？？？？？？？？？？？？？？？？？？
		 * 在页面判断？？？？如果firstPage<0,就赋值为零
		 * */
		if (firstPage <= 0) {
			firstPage = 0;
		}
		if (page - firstPage >= 5 && page <= totalPages - 1) {
			firstPage++;
		}
		if (curPage < firstPage && firstPage > 0) {
			firstPage--;
		}
		
		model.addAttribute("blogPage", blogPage);
		model.addAttribute("totalPages", totalPages);
		model.addAttribute("firstPage", firstPage);
		model.addAttribute("curPage", curPage);
		model.addAttribute("topicTypes", TopicType.getTopicTypes());
		
		return "users/collects";
	}
	
	@RequestMapping(value = "/users/{userId}/careUsers", method = RequestMethod.GET)
	public String careUsers(
		@RequestParam(value = "firstPage", defaultValue = "0") Integer firstPage,
		@RequestParam(value = "curPage", defaultValue = "0") Integer curPage,
		@RequestParam(value = "page", defaultValue = "0") Integer page,
		@PathVariable("userId") Integer userId,
		Model model) {

		int totalPages = 0;
		curPage = page;
		Query query = entityManager.createQuery("select u.careUsers from User u where u.id = ?").setParameter(1, userId);
		List<User> userPageList = query.getResultList();
		
		int userPageSize = userPageList.size();
		totalPages = userPageSize / 10;
		if (userPageSize % 10 != 0) {
			totalPages++;
		}
		
		//选择10个元素
		List<User> userPage = new ArrayList<User>(10);
		for (int i = page * 10; i < page * 10 + 10 && i < userPageSize; i++) {
			User user = userPageList.get(i);
			if (user != null) {
				userPage.add(user);
			}
		}
		
		if (firstPage <= 0) {
			firstPage = 0;
		}
		if (page - firstPage >= 5 && page <= totalPages - 1) {
			firstPage++;
		}
		if (curPage < firstPage && firstPage > 0) {
			firstPage--;
		}

		model.addAttribute("userPage", userPage);
		model.addAttribute("totalPages", totalPages);
		model.addAttribute("firstPage", firstPage);
		model.addAttribute("curPage", curPage);
		
		return "users/cares";
	}

}
