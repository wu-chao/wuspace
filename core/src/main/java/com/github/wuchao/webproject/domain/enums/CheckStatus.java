package com.github.wuchao.webproject.domain.enums;

/**
 * 审核状态
 */
public enum CheckStatus {
    /**
     * 等待审查
     */
    PENDING,

    /**
     * 审查中
     */
//    CHECKING,

    /**
     * 审查已通过
     */
    QUALIFIED,

    /**
     * 审查未通过
     */
    UNQUALIFIED
}
