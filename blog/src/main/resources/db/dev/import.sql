--
-- INSERT INTO groups(id,role_name,group_name) VALUES (1,'super_admins','超级管理员');
-- INSERT INTO groups(id,role_name,group_name) VALUES (2,'admins','普通管理员');

INSERT INTO users(id,username,nickname,password,enabled) VALUES (1,'admin','管理员','password',TRUE );
INSERT INTO users(id,username,nickname,password,enabled) VALUES (2,'chao','chao','password',TRUE );

INSERT INTO authorities(id, username, authority) VALUES (1,'admin','ROLE_ADMIN');
INSERT INTO authorities(id, username, authority) VALUES (2,'chao','ROLE_USER');

-- INSERT INTO group_members(id,username,group_id) VALUES (1,'admin',1);
-- INSERT INTO group_members(id,username,group_id) VALUES (2,'chao',1);
-- INSERT INTO group_members(id,username,group_id) VALUES (3,'xin',2);
-- INSERT INTO group_members(id,username,group_id) VALUES (4,'qian',2);
--
-- INSERT INTO group_authorities(id,group_id,authority) VALUES (1,1,'super_admin');
-- INSERT INTO group_authorities(id,group_id,authority) VALUES (2,2,'admin');

INSERT INTO blogs(id,created_at,updated_at,title,content,viewed_times,user_id) VALUES (1,'2016-11-20 13:50:00','2016-11-20 13:50:00','Spring Cloud 构建微服务架构：Hystrix 监控面板【Dalston 版】','在上一篇《服务容错保护（hystrix 断路器）》的介绍中，我们提到断路器是根据一段时间窗内的请求情况来判断并操作断路器的打开和关闭状态的。而这些请求情况的指标信息都是 HystrixCommand 和 HystrixObservableCommand 实例在执行过程中记录的重要度量信息，它们除了 Hystrix 断路器实现中使用之外，对于系统运维也有非常大的帮助。这些指标信息会以 “滚动时间窗” 与 “桶” 结合的方式进行汇总，并在内存中驻留一段时间，以供内部或外部进行查询使用，Hystrix Dashboard 就是这些指标内容的消费者之一。',0,2);
INSERT INTO blogs(id,created_at,updated_at,title,content,viewed_times,user_id) VALUES (2,'2016-11-20 13:50:00','2016-11-20 13:50:00','Java 微服务实践 - Spring Boot 系列讲座','这里推荐一个不错的 Spring Boot 系列讲座，讲师简介如下： 小马哥，阿里巴巴技术专家，从事十余年 Java EE 开发，国内微服务技术讲师。目前主要负责微服务技术推广、架构设计、基础设施、迁移等。重点关注云计算、微服务以及软件架构等领域。获得过 SUN Java（SCJP、SCWCD、SCBCD）以及 Oracle OCA 等的认证。 讲座大纲 Java 微服务实践 - Sprin...',0,2);
INSERT INTO blogs(id,created_at,updated_at,title,content,viewed_times,user_id) VALUES (3,'2016-11-20 13:50:00','2016-11-20 13:50:00','云原生应用的 12 要素','简介如今，软件通常会作为一种服务来交付，它们被称为网络应用程序，或软件即服务（SaaS）。12-Factor 为构建如下的 SaaS 应用提供了方法论： 使用标准化流程自动配置，从而使新的开发者花费最少的学习成本加入这个项目。 和操作系统之间尽可能的划清界限，在各个系统中提供最大的可移植性。 适合部署在现代的云计算平台，从而在服务器和系统管理方面节省资源。 将开发环境和生产环境的差异降至最...',0,2);
INSERT INTO blogs(id,created_at,updated_at,title,content,viewed_times,user_id) VALUES (4,'2016-11-20 13:50:00','2016-11-20 13:50:00','Spring Cloud 构建微服务架构：服务容错保护（Hystrix 断路器）【Dalston 版】','前言在前两篇《Spring Cloud 构建微服务架构：服务容错保护（Hystrix 服务降级）》和《Spring Cloud 构建微服务架构：服务容错保护（Hystrix 依赖隔离）》中，我们对 Hystrix 提供的服务降级和依赖隔离有了基本的认识。下面我们将继续说说 Hystrix 的另外一个重要元件：断路器。 断路器断路器模式源于 Martin Fowler 的 Circuit Breaker 一文。“断路...',0,2);
INSERT INTO blogs(id,created_at,updated_at,title,content,viewed_times,user_id) VALUES (5,'2016-11-20 13:50:00','2016-11-20 13:50:00','Spring Cloud 构建微服务架构：服务容错保护（Hystrix 依赖隔离）【Dalston 版】','前言在上一篇《Spring Cloud 构建微服务架构：服务容错保护（Hystrix 服务降级）》中，我们已经体验了如何使用 @HystrixCommand 来为一个依赖资源定义服务降级逻辑。实现方式非常简单，同时对于降级逻辑还能实现一些更加复杂的级联降级等策略。之前对于使用 Hystrix 来实现服务容错保护时，除了服务降级之外，我们还提到过线程隔离、断路器等功能。那么在本篇中我们就来具体说说线程隔离...',0,2);

-- INSERT INTO blog_zan(id,blog_id,user_id) VALUES (1,1,2);
--
-- INSERT INTO blog_collect(id,blog_id,user_id) VALUES (1,1,2);
--
-- INSERT INTO comments(id,created_at,updated_at,content,user_id,blog_id) VALUES (1,'2016-11-20 13:50:00','2016-11-20 13:50:00','fwefwefa',1,1);
--
-- INSERT INTO comment_zan(id,comment_id,user_id) VALUES (1,1,2);
--
-- INSERT INTO comment_cai(id,comment_id,user_id) VALUES (1,1,3);
--
-- INSERT INTO replies(id,created_at,updated_at,content,user_id,comment_id,reply_to_id) VALUES (1,'2016-11-20 13:50:00','2016-11-20 13:50:00','fewfwaegfwaef',2,1,1);
