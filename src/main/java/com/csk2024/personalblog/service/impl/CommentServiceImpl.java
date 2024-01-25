package com.csk2024.personalblog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.csk2024.personalblog.entity.Comment;
import com.csk2024.personalblog.service.CommentService;
import com.csk2024.personalblog.mapper.CommentMapper;
import com.csk2024.personalblog.vo.CommentVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author 24387
* @description 针对表【comment(文章评论)】的数据库操作Service实现
* @createDate 2024-01-25 21:26:39
*/
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment>
    implements CommentService{

    @Autowired
    private CommentMapper commentMapper;

    @Override
    public List<CommentVo> listComment(String articleId) {
        return commentMapper.listComment(articleId);
    }
}




