package com.wuspace.repository;

import com.wuspace.domain.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

public interface ArticleRepository extends JpaRepository<Article, Long>, QueryDslPredicateExecutor<Article>
//        , QuerydslBinderCustomizer<QBlog>
{

//    default void customize(QuerydslBindings bindings, QBlog root) {
//        bindings.bind(root.id).first((path, value) -> path.gt(value));
//        bindings.bind(root.title).first((StringPath path, String value) -> path.containsIgnoreCase(value));
//    }

    Page<Article> findAll(Specification<Article> specification, Pageable pageable);
}
