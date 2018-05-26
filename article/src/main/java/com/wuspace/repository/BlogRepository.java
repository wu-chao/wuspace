package com.wuspace.repository;

import com.querydsl.core.types.dsl.StringPath;
import com.wuspace.domain.Blog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;

public interface BlogRepository extends JpaRepository<Blog, Long>, QueryDslPredicateExecutor<Blog>
//        , QuerydslBinderCustomizer<QBlog>
{

//    default void customize(QuerydslBindings bindings, QBlog root) {
//        bindings.bind(root.id).first((path, value) -> path.gt(value));
//        bindings.bind(root.title).first((StringPath path, String value) -> path.containsIgnoreCase(value));
//    }

    Page<Blog> findAll(Specification<Blog> specification, Pageable pageable);
}
