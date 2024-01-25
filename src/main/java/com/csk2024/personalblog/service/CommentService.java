package com.csk2024.personalblog.service;

import com.csk2024.personalblog.entity.Comment;
import com.baomidou.mybatisplus.extension.service.IService;
import com.csk2024.personalblog.vo.CommentVo;

import java.util.List;

/**
* @author 24387
* @description 针对表【comment(文章评论)】的数据库操作Service
* @createDate 2024-01-25 21:26:39
*/
public interface CommentService extends IService<Comment> {

    List<CommentVo> listComment(String articleId);
}
