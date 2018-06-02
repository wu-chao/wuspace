package com.wuspace.repository;

import com.wuspace.domain.Works;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorksRepository extends JpaRepository<Works, Long> {



}
