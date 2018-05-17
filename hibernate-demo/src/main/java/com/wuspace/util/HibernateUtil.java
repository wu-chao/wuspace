package com.wuspace.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.model.naming.ImplicitNamingStrategyComponentPathImpl;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

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

        } catch (Throwable ex) {
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
            session = sessionFactory.openSession();
            // 保存该数据库连接 session 到 ThreadLocal 中。
            sessionTL.set(session);
        }
        // 如果当前线程已经访问过数据库了，
        // 则从 sessionTL 中 get() 就可以获取该线程上次获取过的数据库连接对象。
        return session;
    }

    /**
     * 关闭Session
     */
    @SuppressWarnings("unchecked")
    public static void close() {
        Session session = (Session) sessionTL.get();
        sessionTL.set(null);
        session.close();
    }

}