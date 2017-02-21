package com.juhezi.coderslife.model;

import com.juhezi.coderslife.entry.LogContent;
import com.juhezi.coderslife.function.draft_box.bean.LogDraftBean;
import com.juhezi.coderslife.tools.Action;
import com.juhezi.coderslife.tools.Action1;

import java.util.List;

/**
 * Created by qiao1 on 2017/1/18.
 */
public interface Response {

    /**
     * 添加日志
     *
     * @param logContent
     * @param result
     */
    void addLogContent(LogContent logContent, Action1<Integer> result);

    /**
     * 获取今日所有日志
     *
     * @param time
     * @param action
     */
    void getTodayAllLogs(String time, Action1<List<LogContent>> action);

    /**
     * 更新某条日志
     *
     * @param logContent
     */
    void updateLog(LogContent logContent, Action1<Integer> action1);

    /**
     * 删除某条记录
     *
     * @param id
     * @param action
     */
    void removeLog(String id, Action1<Integer> action);

    void deleteDayAllLogs(String time, Action1<Integer> action);

    void getAllLogs(Action1<List<LogContent>> action);

    /**
     * 分页查询
     *
     * @param start
     * @param offset
     * @param action
     */
    void getPartLogs(int start, int offset, Action1<List<LogContent>> action);

    void addDraft(LogDraftBean draft, Action1<Integer> action);

    void getDrafts(Action1<List<LogDraftBean>> action);

}
