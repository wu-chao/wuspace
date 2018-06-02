package com.wuspace.repository;

import com.wuspace.domain.MediaInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MediaInfoRepository extends JpaRepository<MediaInfo, Long> {

    @EntityGraph(attributePaths = {"author", "category", "articleContent"})
    Page<MediaInfo> findAll(Specification<MediaInfo> specification, Pageable pageable);
}
