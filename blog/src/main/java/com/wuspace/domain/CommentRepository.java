package com.wuspace.domain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Integer>{
	
	Comment findById(Integer id);
	
//	@Query("select c from Comment c left join fetch c.zanUsers where c.id = ?1")
//	Comment findCommentWithZanusersById(Integer id);
//	
//	@Query("select c from Comment c left join fetch c.caiUsers where c.id = ?1")
//	Comment findCommentWithCaiusersById(Integer id);
}
