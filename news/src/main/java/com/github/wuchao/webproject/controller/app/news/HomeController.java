package com.github.wuchao.webproject.controller.app.news;

import com.github.wuchao.webproject.domain.User;
import com.github.wuchao.webproject.repository.UserRepository;
import com.github.wuchao.webproject.service.news.HomeService;
import com.github.wuchao.webproject.service.news.dto.HomeDTO;
import com.github.wuchao.webproject.service.redis.RedisService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@Slf4j
public class HomeController {

    @Autowired
    private HomeService homeService;

    @Autowired
    private RedisService redisService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RedisTemplate redisTemplate;

    @GetMapping(value = {"", "/", "/index", "/home"})
    public String index(@RequestParam(defaultValue = "1") Long categoryId,
                        @RequestParam(required = false) Long subCategoryId,
                        @RequestParam(required = false) String keyword,
                        @PageableDefault(sort = "createdDate", direction = Sort.Direction.DESC) Pageable pageable,
                        Model model) {

        HomeDTO homeDTO = homeService.initHome(categoryId, subCategoryId, keyword, pageable);
        model.addAttribute("homeDTO", homeDTO);

//        log.info(System.getProperty("java.io.tmpdir"));

        int[] index = {0};
        for (int i = 0; i < 10; i++) {
            index[0] = i + 1;
            User user = redisService.getUser("user1");
            User user1 = redisService.getUser("user1");
            log.info("第{}次执行查询结果：" + user.equals(user1), index[0]);
        }
//
//        log.info("------------------------------------------------------");
//
//        for (int i = 0; i < 10; i++) {
//            index[0] = i + 1;
//            User user = redisService.getUser("user1");
//            User user1 = redisService.getUser("user1");
//            log.info("第{}次执行查询结果：" + user.equals(user1), index[0]);
//
//            Set<String> keys = redisUtil.keys();
//            if (CollectionUtils.isNotEmpty(keys)) {
//                log.info(keys.toString());
//            }
//
//            try {
//                Thread.sleep(10000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }


//        log.info("------------------------------------------------------");
//
//        for (int i = 0; i < 10; i++) {
//            index[0] = i + 1;
//            List<User> users = redisService.getUsers();
//            log.info("第{}次执行查询结果：" + users, index[0]);
//
//        }

        return "news/home";
    }
}
