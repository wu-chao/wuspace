package com.wuspace.repository;

import com.wuspace.domain.Blog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

public interface BlogRepository extends JpaRepository<Blog, Long>, QueryDslPredicateExecutor<Blog> {


}
