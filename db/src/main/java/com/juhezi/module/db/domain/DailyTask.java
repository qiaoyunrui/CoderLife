package com.juhezi.module.db.domain;

import java.util.Date;
import java.util.UUID;

/**
 * Created by Juhezi[juhezix@163.com] on 2017/5/16.
 * 每日任务，即原来的日志
 * id:String
 * Title:String
 * Content:String
 * TagId:long [Tag 的 ID] -> 取消这个属性，暂时先不做
 * CreateDate:Date  创建日期
 * LimitDate:Date   截至日期，默认为当天夜里 11:59
 * State:Boolean   状态
 */
public class DailyTask {

    public static final String TABLE_NAME = "daily_task";
    public static final String ID = "id";
    public static final String TITLE = "title";
    public static final String CONTENT = "content";
    public static final String CREATE_DATE = "create_date";
    public static final String LIMIT_DATE = "limit_date";
    public static final String STATE = "state";

    //Test OK
    public final static String CREATE_SQL =
            "create table if not exists " + TABLE_NAME +
                    "(" +
                    ID + " text primary key," +
                    TITLE + " text," +
                    CONTENT + " text," +
                    CREATE_DATE + " integer," +
                    LIMIT_DATE + " integer," +
                    STATE + " integer" +
                    ")";    //建表语句

    private String id;
    private String title;
    private String content;
    private Date createDate;
    private Date limitDate;
    private boolean state;

    public DailyTask(String id, String title, String content, Date createDate, Date limitDate, boolean state) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.createDate = createDate;
        this.limitDate = limitDate;
        this.state = state; //false -> 未完成 true -> 已完成
    }

    public static DailyTask create() {
        String id = UUID.randomUUID().toString();
        Date sDate = new Date();  //当前时间设置为开始
        Date lDate = new Date(sDate.getYear(), sDate.getMonth(), sDate.getDate(), 23, 59, 59);    //将当天的最后一刻设置为截至时间.
        return new DailyTask(id, "", "", sDate, lDate, false);
    }

    public String getId() {
        return id;
    }

    public DailyTask setId(String id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public DailyTask setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getContent() {
        return content;
    }

    public DailyTask setContent(String content) {
        this.content = content;
        return this;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public DailyTask setCreateDate(Date createDate) {
        this.createDate = createDate;
        return this;
    }

    public Date getLimitDate() {
        return limitDate;
    }

    public DailyTask setLimitDate(Date limitDate) {
        this.limitDate = limitDate;
        return this;
    }

    public boolean getState() {
        return state;
    }

    public DailyTask setState(boolean state) {
        this.state = state;
        return this;
    }
}
