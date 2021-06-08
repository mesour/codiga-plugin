package com.code_inspector.plugins.intellij;

import com.code_inspector.plugins.intellij.annotators.CodeInspectionAnnotation;
import com.google.common.collect.ImmutableList;

public class Constants {
    public static Long INVALID_PROJECT_ID = 0L;
    public static final String LOGGER_NAME = "CodeInspector";
    public static final java.util.List<CodeInspectionAnnotation> NO_ANNOTATION = ImmutableList.of();
    public static final long REAL_TIME_FEEDBACK_TIMEOUT_MILLIS = 10 * 1000 ; // 10 seconds
    public static final long SLEEP_BETWEEN_FILE_ANALYSIS_MILLIS = 500;
}
