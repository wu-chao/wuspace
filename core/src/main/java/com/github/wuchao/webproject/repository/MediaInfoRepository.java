package com.github.wuchao.webproject.repository;

import com.github.wuchao.webproject.domain.MediaInfo;
import com.github.wuchao.webproject.domain.enumeration.MediaType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MediaInfoRepository extends JpaRepository<MediaInfo, Long> {

    /**
     * 查询 Articles
     *
     * @param specification
     * @param pageable
     * @return
     */
    @EntityGraph(attributePaths = {"author", "category", "mediaContent"})
    Page<MediaInfo> findAll(Specification<MediaInfo> specification, Pageable pageable);

    @Override
    Page<MediaInfo> findAll(Pageable pageable);

    /**
     * 查询 Article
     *
     * @param id
     * @param mediaType
     * @return
     */
    @EntityGraph(attributePaths = {"author", "category", "mediaContent"})
    Optional<MediaInfo> findByIdAndMediaType(Long id, MediaType mediaType);


    @EntityGraph(attributePaths = {"author", "category", "mediaContent"})
    Optional<MediaInfo> findOneById(Long id);

}
