package com.github.wuchao.webproject.repository;

import com.github.wuchao.webproject.domain.MediaContent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MediaContentRepository extends JpaRepository<MediaContent, Long> {

}
