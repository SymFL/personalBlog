package com.csk2024.personalblog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.csk2024.personalblog.entity.Comment;
import com.csk2024.personalblog.service.CommentService;
import com.csk2024.personalblog.mapper.CommentMapper;
import org.springframework.stereotype.Service;

/**
* @author 24387
* @description 针对表【comment(文章评论)】的数据库操作Service实现
* @createDate 2024-01-04 20:17:22
*/
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment>
    implements CommentService{

}




