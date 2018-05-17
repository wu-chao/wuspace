package com.wuspace;

import com.wuspace.domain.User;
import com.wuspace.util.HibernateUtil;
import org.hibernate.Session;
import org.junit.Test;

import java.time.ZonedDateTime;

public class HibernateTest {

    /**
     * 测试：
     * https://www.cnblogs.com/whgk/p/6103038.html
     * https://www.cnblogs.com/xiaoluo501395377/p/3380270.html
     */
    @Test
    public void testHibernate() {
        Session session = HibernateUtil.currentSession();
        session.beginTransaction();
        User user = new User();
        user.setUsername("user1");
        user.setPassword("123456");
        user.setActivated(true);
        user.setCreatedBy("1");
        user.setCreatedDate(ZonedDateTime.now());
        session.save(user);
        System.out.println("保存 user");
        user.setDescription("description for user");
        session.getTransaction().commit();
    }
}
