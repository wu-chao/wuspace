package com.wuspace.repository;

import com.querydsl.core.types.EntityPath;
import com.querydsl.jpa.JPAExpressions;
import com.wuspace.domain.Blog;
import com.wuspace.domain.QBlog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;

public interface BlogRepository extends JpaRepository<Blog, Long>, QueryDslPredicateExecutor<Blog> {

    class BlogIndexQuerydslBinder implements QuerydslBinderCustomizer<QBlog> {

        @Override
        public void customize(QuerydslBindings bindings, QBlog root) {

        }
    }
}
