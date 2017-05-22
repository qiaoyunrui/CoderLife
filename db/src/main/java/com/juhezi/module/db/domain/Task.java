package com.juhezi.module.db.domain;

import java.util.Date;
import java.util.UUID;

/**
 * Created by Juhezi[juhezix@163.com] on 2017/5/16.
 * 每日任务，即原来的日志
 * id:String
 * Title:String [删除]，任务不需要这个属性
 * Content:String
 * TagId:long [Tag 的 ID] -> 取消这个属性，暂时先不做
 * CreateDate:Date  创建日期
 * LimitDate:Date   截至日期，默认为当天夜里 11:59
 * State:Boolean   状态
 */
public class Task {

    public static final String TABLE_NAME = "daily_task";
    public static final String ID = "id";
    public static final String CONTENT = "content";
    public static final String CREATE_DATE = "create_date";
    public static final String LIMIT_DATE = "limit_date";
    public static final String STATE = "state";

    //Test OK
    public final static String CREATE_SQL =
            "create table if not exists " + TABLE_NAME +
                    "(" +
                    ID + " text primary key," +
                    CONTENT + " text," +
                    CREATE_DATE + " integer," +
                    LIMIT_DATE + " integer," +
                    STATE + " integer" +
                    ")";    //建表语句

    private String id;
    private String content;
    private Date createDate;
    private Date limitDate;
    private boolean state;

    public Task(String id, String content, Date createDate, Date limitDate, boolean state) {
        this.id = id;
        this.content = content;
        this.createDate = createDate;
        this.limitDate = limitDate;
        this.state = state; //false -> 未完成 true -> 已完成
    }

    public static Task create() {
        String id = UUID.randomUUID().toString();
        Date sDate = new Date();  //当前时间设置为开始
        Date lDate = new Date(sDate.getYear(), sDate.getMonth(), sDate.getDate(), 23, 59, 59);    //将当天的最后一刻设置为截至时间.
        return new Task(id, "", sDate, lDate, false);
    }

    public String getId() {
        return id;
    }

    public Task setId(String id) {
        this.id = id;
        return this;
    }

    public String getContent() {
        return content;
    }

    public Task setContent(String content) {
        this.content = content;
        return this;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public Task setCreateDate(Date createDate) {
        this.createDate = createDate;
        return this;
    }

    public Date getLimitDate() {
        return limitDate;
    }

    public Task setLimitDate(Date limitDate) {
        this.limitDate = limitDate;
        return this;
    }

    public boolean getState() {
        return state;
    }

    public Task setState(boolean state) {
        this.state = state;
        return this;
    }
}
