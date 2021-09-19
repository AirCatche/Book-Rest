package com.slobodyanyuk_mykhailo99.bookrest.util

import java.util.regex.Pattern

object ValidationPattern {
    val PASSWORD_PATTERN = Pattern.compile(
        "(?=.*[a-zA-Z])" +          // a-z A-Z
                "(?=\\S+$)" +            //no white spaces
                ".{6,15}" +              //at least 6 characters
                "$"
    )

    val USERNAME_PATTERN = Pattern.compile(
        "(?=.*[a-zA-Z])" +          // a-z A-Z
                ".{3,15}" +              //at least 3 characters
                "$"
    )
}