package com.csk2024.personalblog.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 评论回复
 * @TableName comment_reply
 */
@TableName(value ="comment_reply")
@Data
public class CommentReply implements Serializable {
    /**
     * 评论回复id
     */
    @TableId(value = "comment_reply_id")
    private String commentReplyId;

    /**
     * 评论id
     */
    @TableField(value = "comment_id")
    private String commentId;

    /**
     * 回复评论的人id
     */
    @TableField(value = "reply_user_id")
    private String replyUserId;

    /**
     * 继续回复的人id
     */
    @TableField(value = "secondly_user_id")
    private String secondlyUserId;

    /**
     * 评论回复的时间
     */
    @TableField(value = "comment_reply_time")
    private Date commentReplyTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        CommentReply other = (CommentReply) that;
        return (this.getCommentReplyId() == null ? other.getCommentReplyId() == null : this.getCommentReplyId().equals(other.getCommentReplyId()))
            && (this.getCommentId() == null ? other.getCommentId() == null : this.getCommentId().equals(other.getCommentId()))
            && (this.getReplyUserId() == null ? other.getReplyUserId() == null : this.getReplyUserId().equals(other.getReplyUserId()))
            && (this.getSecondlyUserId() == null ? other.getSecondlyUserId() == null : this.getSecondlyUserId().equals(other.getSecondlyUserId()))
            && (this.getCommentReplyTime() == null ? other.getCommentReplyTime() == null : this.getCommentReplyTime().equals(other.getCommentReplyTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getCommentReplyId() == null) ? 0 : getCommentReplyId().hashCode());
        result = prime * result + ((getCommentId() == null) ? 0 : getCommentId().hashCode());
        result = prime * result + ((getReplyUserId() == null) ? 0 : getReplyUserId().hashCode());
        result = prime * result + ((getSecondlyUserId() == null) ? 0 : getSecondlyUserId().hashCode());
        result = prime * result + ((getCommentReplyTime() == null) ? 0 : getCommentReplyTime().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", commentReplyId=").append(commentReplyId);
        sb.append(", commentId=").append(commentId);
        sb.append(", replyUserId=").append(replyUserId);
        sb.append(", secondlyUserId=").append(secondlyUserId);
        sb.append(", commentReplyTime=").append(commentReplyTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}