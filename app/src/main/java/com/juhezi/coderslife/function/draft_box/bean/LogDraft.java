package com.juhezi.coderslife.function.draft_box.bean;

import android.databinding.Bindable;
import android.support.annotation.IntDef;

import com.juhezi.coderslife.entry.LogContent;

/**
 * Created by qiao1 on 2017/2/17.
 */
public class LogDraft extends LogContent {

    private static final int TYPE_DRAFT_ADD = 0x100;
    private static final int TYPE_DRAFT_EDIT = 0x101;

    @IntDef({TYPE_DRAFT_ADD, TYPE_DRAFT_EDIT})
    public @interface DraftType {
    }

    private int draftType;  //草稿类型

    public LogDraft(LogContent log, @DraftType int draftType) {
        super(log.getId(),
                log.getContent(),
                log.getContentType(),
                log.getState(),
                log.getTime());
        this.draftType = draftType;
    }

    @Bindable
    public int getDraftType() {
        return draftType;
    }

    @Bindable
    public void setDraftType(int draftType) {
        this.draftType = draftType;
    }
}
