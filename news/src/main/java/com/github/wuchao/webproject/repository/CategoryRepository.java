package com.github.wuchao.webproject.repository;

import com.alicp.jetcache.anno.CacheRefresh;
import com.alicp.jetcache.anno.CacheType;
import com.alicp.jetcache.anno.Cached;
import com.github.wuchao.webproject.domain.Category;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Cached(expire = 3600, name = "CategoryRepository.", cacheType = CacheType.REMOTE)
    @CacheRefresh(refresh = 3595)
    @EntityGraph(attributePaths = "categories")
    List<Category> findAllBy();
}
