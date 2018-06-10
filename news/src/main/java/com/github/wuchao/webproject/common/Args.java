package com.github.wuchao.webproject.common;

public final class Args {

    // PRIVATE
    private Args() {
        //empty - prevent construction
    }

    public static void checkForNull(Object o) {
        if (o == null) {
            throw new IllegalArgumentException(String.format("argument %s is null.", o));
        }
    }


}
