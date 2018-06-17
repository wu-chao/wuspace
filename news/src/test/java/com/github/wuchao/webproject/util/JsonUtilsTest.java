package com.github.wuchao.webproject.util;

import com.github.wuchao.webproject.domain.User;
import org.junit.Test;

public class JsonUtilsTest {

    @Test
    public void testJson() {
        String json2 = "{\"username\":\"test_user1\",\"email\":\"111@qq.com\",\"avatarUrl\":\"http://www.baidu.com/image.jpg\"}";
        String json3 = "{\"user\":{\"username\":\"test_user2\",\"email\":\"222@qq.com\",\"avatarUrl\":\"http://www.baidu.com/image.jpg\"}}";

        User user2 = (User) JacksonUtil.deserialize(json2, User.class);
        System.out.println("\njson2:" + user2.getUsername());

        User user3 = (User) JacksonUtil.deserialize(json3, User.class);
        System.out.println("\njson3:" + user3.getUsername());
    }
}
