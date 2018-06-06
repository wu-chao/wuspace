package com.github.wuchao.webproject.domain;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

public class BlogSpecs {

    public static Specification<MediaInfo> findAll(final String titleOrContent) {
        return Specifications.where(filter(titleOrContent));
    }

    public static Specification<MediaInfo> filter(final String titleOrContent) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (!StringUtils.isEmpty(titleOrContent)) {
                predicates.add(cb.like(root.get("title"), titleOrContent));
            }

            if (!StringUtils.isEmpty(titleOrContent)) {
                predicates.add(cb.like(root.get("content"), titleOrContent));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
