package com.wuspace.domain.security;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Integer> {
	
	@Query("select count(u) > 0 from User u where u.email = ?1 or u.nickname = ?1")
	boolean existsByAccount(String account);
	
	@Query("select u from User u where u.email = ?1 or u.nickname = ?1")
	User findByAccount(String account);
	
	@Query("select u from User u left join fetch u.careUsers "
			+ "where (u.email = ?1 or u.nickname = ?1) and u.password = ?2")
	User findUserWithCareusersByAccountAndPassword(String account, String password);
	
	@Query("select u from User u left join fetch u.careUsers where u.id = ?1")
	User findUserWithCareusersById(Integer id);
	
	@Query("select u from User u "
			+ "left join fetch u.blogs "
			+ "left join fetch u.comments "
			+ "left join fetch u.repliesOfReplyTo "
			+ "left join fetch u.repliesOfUser "
			+ "left join fetch u.collectBlogs "
			+ "left join fetch u.careUsers "
			+ "where u.id = ?1")
	User findAllById(Integer id);
}
