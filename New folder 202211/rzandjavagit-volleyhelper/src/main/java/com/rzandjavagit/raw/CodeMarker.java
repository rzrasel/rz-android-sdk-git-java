package com.rzandjavagit.raw;

public @interface CodeMarker {
    /**
     * @return the desired name of the field when it is serialized or deserialized
     */
    String value();

    /**
     * @return the alternative names of the field when it is deserialized
     */
    String[] alternate() default {};
}
