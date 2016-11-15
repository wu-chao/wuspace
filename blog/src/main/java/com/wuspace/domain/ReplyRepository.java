package com.wuspace.domain;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface ReplyRepository extends JpaRepository<Reply, Integer>{
	
	@Transactional
	@Modifying
	@Query("delete from Reply r where r.id = ?1")
	void deleteById(Integer id);
	
}
