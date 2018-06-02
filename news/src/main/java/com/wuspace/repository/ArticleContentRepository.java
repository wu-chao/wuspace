package com.wuspace.repository;

import com.wuspace.domain.WorksContent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleContentRepository extends JpaRepository<WorksContent, Long> {

}
