package com.juhezi.coderslife.model;

import com.juhezi.coderslife.entry.LogContent;
import com.juhezi.coderslife.tools.Action1;

/**
 * Created by qiao1 on 2017/1/18.
 */
public interface Response {

    /**
     * 添加日志
     * @param logContent
     * @param result
     */
    void addLogContent(LogContent logContent, Action1<Integer> result);

}
