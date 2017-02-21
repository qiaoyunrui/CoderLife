package com.juhezi.coderslife.tools;

/**
 * Created by qiao1 on 2017/1/18.
 */
public class Config {

    public static final String LOCAL_DB_NAME = "CoderLife.db";
    public static final int LOCAL_DB_VERSION = 2;

    public static final int RESULT_CODE_OK = 200;  //结果码_成功
    public static final int RESULT_CODE_ERROR = 400;    //结果码_错误

    public static final String ADD_REQUIREMENT_LOG_CONTENT = "addRequirementLogContent";

    public static final String SHOW_LOG_CONTENT_INFO = "showLogContentInfo";

    public static final String LOG_SAVED = "logSaved";

    public static final int TAG_ADD_REQUIREMENT_RETURN = 0x100;

    public static final int TAG_MAIN_FRAGMENT_TO_ADD_REQUIREMENT = 0x101;

    public static final int TAG_MAIN_FRAGMENT_TO_LOG_INFO_ACT = 0x102;

    public static final int TAG_LOG_INFO_RETURN_SAVE = 0x103;

    public static final int TAG_LOG_INFO_RETURN_DELETE = 0x104;
}
