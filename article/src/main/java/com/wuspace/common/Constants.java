package com.wuspace.common;

public final class Constants {

    public static final String SPRING_PROFILE_DEVELOPMENT = "dev";

    public static final String SYSTEM_ACCOUNT = "system";
    public static final String ANONYMOUS_USER = "anonymoususer";

    public static final String SESSION_CURRENT_USER = "session_current_user";

    private Constants() {
        throw new RuntimeException();
    }
}
