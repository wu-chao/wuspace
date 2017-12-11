package com.wuspace.config;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import javax.sql.DataSource;
import java.io.IOException;

@Configuration
public class MybatisConfig {

    // mybatis 配置路径
    private static final String MYBATIS_CONFIG = "mybatis-config.xml";

    // mybatis mapper
    private static final String MAPPER_PATH = "/mapper/**.xml";

    private static final String TYPE_ALIAS_PACKAGE = "com.wuspace.domain";

    @Autowired
    private DataSource dataSource;

    /**
     * 创建 sqlSessionFactoryBean 实例
     * 并且设置 configuration 如驼峰命名.等等
     * 设置 mapper 映射路径
     * 设置 datasource 数据源
     *
     * @return
     */
//    @Bean
//    public SqlSessionFactoryBean sqlSessionFactoryBean() throws IOException {
//        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
//
//        // 设置 mybatis configuration 扫描路径
//        sqlSessionFactoryBean.setConfigLocation(new ClassPathResource(MYBATIS_CONFIG));
//
//        // 添加 mapper 扫描路径
//        PathMatchingResourcePatternResolver pathMatchingResourcePatternResolver = new PathMatchingResourcePatternResolver();
//        String packageSearchPath = ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX + MAPPER_PATH;
//        sqlSessionFactoryBean.setMapperLocations(pathMatchingResourcePatternResolver.getResources(packageSearchPath));
//
//        // 设置 datasource
//        sqlSessionFactoryBean.setDataSource(dataSource);
//
//        // 设置 typeAlias 包扫描路径
//        sqlSessionFactoryBean.setTypeAliasesPackage(TYPE_ALIAS_PACKAGE);
//
//        return sqlSessionFactoryBean;
//    }
}
