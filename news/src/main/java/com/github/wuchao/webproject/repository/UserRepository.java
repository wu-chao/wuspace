package com.github.wuchao.webproject.repository;

import com.alicp.jetcache.anno.CacheRefresh;
import com.alicp.jetcache.anno.CacheType;
import com.alicp.jetcache.anno.Cached;
import com.github.wuchao.webproject.domain.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional(rollbackFor = Exception.class)
public interface UserRepository extends JpaRepository<User, Long> {

    @EntityGraph(attributePaths = "authorities")
    Optional<User> findOneByUsername(String username);

    @Cached(expire = 60, cacheType = CacheType.REMOTE)
    @CacheRefresh(refresh = 50)
    @EntityGraph(attributePaths = "authorities")
    User findByUsername(String username);

    @EntityGraph(attributePaths = "authorities")
    Optional<User> findOneWithAuthoritiesByUsername(String username);

    @Override
    @EntityGraph(attributePaths = "authorities")
    List<User> findAll();

}
