package com.wuspace.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BlogRepository extends JpaRepository<Blog, Integer>{
	
	Blog findBlogByUser(User user);
	
	Page<Blog> findByUser(User user, Pageable pageRequest);
	
	@Query("select b from Blog b left join fetch b.comments "
			+ "left join fetch b.zanUsers "
			+ "left join fetch b.collectUsers "
			+ "where b.id = ?1")
	Blog findBlogWithAllById(Integer id);
	
//	@Query("select p from Blog p left join fetch p.comments c "
//			+ "left join fetch p.zanUsers "
//			+ "left join fetch p.collectUsers "
//			+ "where p.topicType = ?1")
//	Page<Blog> findAllByTopicType(TopicType topicType, Pageable pageable);
	
	@Query("select p from Blog p left join fetch p.comments "
			+ "where p.id = ?1")
	Blog findBlogWithCommentsById(Integer id);
	
	@Query("select p from Blog p left join fetch p.zanUsers "
			+ "where p.id = ?1")
	Blog findBlogWithZanusersById(Integer id);

	@Query("select p from Blog p left join fetch p.collectUsers "
			+ "where p.id = ?1")
	Blog findBlogWithCollectusersById(Integer id);
	
	Blog findBlogById(Integer id);
	
	/*@Query("select ")
	List<Blog> findTop5ByCreatetimeDesc();*/
	
}
