package com.github.wuchao.webproject.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
public class WebSupportConfig extends WebMvcConfigurationSupport {

//    @Bean
//    public Jackson2ObjectMapperFactoryBean jackson2ObjectMapperFactoryBean() {
//
//        ObjectMapper objectMapper = new ObjectMapper()
//                .registerModule(new ParameterNamesModule())
//                .registerModule(new Jdk8Module())
//                // new module, NOT JSR310Module
//                .registerModule(new JavaTimeModule())
//                .setSerializationInclusion(JsonInclude.Include.NON_NULL);
//
//        Hibernate5Module hm = new Hibernate5Module();
//        hm.configure(Hibernate5Module.Feature.FORCE_LAZY_LOADING, false);
//        objectMapper.registerModule(hm);
//
//        Jackson2ObjectMapperFactoryBean jackson2ObjectMapperFactoryBean = new Jackson2ObjectMapperFactoryBean();
//        jackson2ObjectMapperFactoryBean.setObjectMapper(objectMapper);
//        jackson2ObjectMapperFactoryBean.setIndentOutput(false);
//        return jackson2ObjectMapperFactoryBean;
//    }

//    @Bean
//    public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter() {
//        MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();
//        mappingJackson2HttpMessageConverter.setObjectMapper(new ObjectMapper().registerModule(new Hibernate5Module()));
//        return mappingJackson2HttpMessageConverter;
//    }

}
