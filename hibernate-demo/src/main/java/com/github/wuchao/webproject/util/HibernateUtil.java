package com.github.wuchao.webproject.util;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.model.naming.ImplicitNamingStrategyComponentPathImpl;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

@Slf4j
public final class HibernateUtil {

    // 初始化一个ThreadLocal对象
    private static final ThreadLocal sessionTL = new ThreadLocal();
    private static Configuration configuration;
    private final static SessionFactory sessionFactory;

    static {
        try {
// 1. Hibernate4.0 之后的版本
//            new Configuration().configure();
//            StandardServiceRegistryBuilder serviceRegistryBuilder = new StandardServiceRegistryBuilder();
//            ServiceRegistry serviceRegistry = serviceRegistryBuilder.build();
//            sessionFactory = configuration.buildSessionFactory(serviceRegistry);

// 2. Hibernate4.1 之后的版本
//            configuration = new Configuration().configure();
//
//            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
//            sessionFactory = configuration.buildSessionFactory(serviceRegistry);

// 3. Hibernate5.x
            /**
             * 关于Hibernate 5 和 Hibernate 4 在创建SessionFactory的不同点分析（解决 org.hibernate.MappingException: Unknown entity: xx类报错问题）
             * https://www.cnblogs.com/wangxiaoha/p/6231888.html
             */
            StandardServiceRegistry standardRegistry = new StandardServiceRegistryBuilder().configure().build();
            Metadata metadata = new MetadataSources(standardRegistry).getMetadataBuilder()
                    .applyImplicitNamingStrategy(ImplicitNamingStrategyComponentPathImpl.INSTANCE).build();
            sessionFactory = metadata.getSessionFactoryBuilder().build();
            log.warn("创建 sessionFactory 成功");

        } catch (Throwable ex) {
            log.warn("创建 sessionFactory 失败");
            ex.printStackTrace();
            throw new ExceptionInInitializerError(ex);
        }
    }

    /**
     * 获取 Session
     */
    public static Session currentSession() {
        // sessionTL 的 get() 方法根据当前线程返回其对应的线程内部变量，
        // 也就是我们需要的 Session，多线程情况下共享数据库连接是不安全的。
        // ThreadLocal 保证了每个线程都有自己的 Session。
        Session session = (Session) sessionTL.get();
        // 如果 session 为 null，则打开一个新的 session
        if (session == null) {
            // 创建一个数据库连接对象 session。
            session = sessionFactory.getCurrentSession();
            // 保存该数据库连接 session 到 ThreadLocal 中。
            sessionTL.set(session);
        }
        // 如果当前线程已经访问过数据库了，
        // 则从 sessionTL 中 get() 就可以获取该线程上次获取过的数据库连接对象。
        return session;
    }

    /**
     * 关闭Session
     * <p>
     * 1、getCurrentSession()与openSession()的区别？
     * <p>
     * * 采用getCurrentSession()创建的session会绑定到当前线程中，而采用openSession()，创建的session则不会
     * <p>
     * * 采用getCurrentSession()创建的session在commit或rollback时会自动关闭，而采用openSession()，创建的session必须手动关闭
     * <p>
     * 2、使用getCurrentSession()需要在hibernate.cfg.xml文件中加入如下配置：
     * <p>
     * * 如果使用的是本地事务（jdbc事务）
     *
     * <property name="hibernate.current_session_context_class">thread</property>
     * <p>
     * * 如果使用的是全局事务（jta事务）
     *
     * <property name="hibernate.current_session_context_class">jta</property>
     * <p>
     * <p>
     * <p>
     * openSession() 与 getCurrentSession() 有何不同和关联呢？
     * <p>
     * 在 SessionFactory 启动的时候， Hibernate 会根据配置创建相应的 CurrentSessionContext ，在getCurrentSession() 被调用的时候，实际被执行的方法是 CurrentSessionContext.currentSession() 。在currentSession() 执行时，如果当前 Session 为空， currentSession 会调用 SessionFactory 的 openSession 。所以 getCurrentSession() 对于 Java EE 来说是更好的获取 Session 的方法。
     * <p>
     * <p>
     * <p>
     * 许多时候出现session is close();原因就是你在hibernate.cfg.xml里面设置了
     *
     * <property name="hibernate.current_session_context_class">thread</property>
     * <p>
     * 系统在commit();执行完之后就关闭了session，这时候你手动再关闭session就当然提示错误了
     *
     * https://blog.csdn.net/mr_hhh/article/details/50681309
     */
    @SuppressWarnings("unchecked")
    public static void close() {
        Session session = (Session) sessionTL.get();
        sessionTL.set(null);
        session.close();
    }

}