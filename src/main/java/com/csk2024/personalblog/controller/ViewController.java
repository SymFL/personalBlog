package com.csk2024.personalblog.controller;

import cn.hutool.captcha.CircleCaptcha;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.csk2024.personalblog.dto.article.ArticleListPageDto;
import com.csk2024.personalblog.entity.*;
import com.csk2024.personalblog.service.*;
import com.csk2024.personalblog.utils.CommonPage;
import com.csk2024.personalblog.utils.CommonResult;
import com.csk2024.personalblog.utils.CommonUtils;
import com.csk2024.personalblog.vo.ArticleListVo;
import com.csk2024.personalblog.vo.CommentVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping("/")
public class ViewController {
    @Autowired
    private ArticleService articleService;
    @Autowired
    private AdService adService;
    @Autowired
    private LinkService linkService;
    @Autowired
    private ArticleTagService articleTagService;
    @Autowired
    private UserGoodArticleService userGoodArticleService;
    @Autowired
    private UserCollectionArticleService userCollectionArticleService;
    @Autowired
    private CommentService commentService;

    /**
     * 获取图像验证码
     */
    @GetMapping("/getCaptcha")
    public void getCaptcha(HttpServletRequest request, HttpServletResponse response) throws IOException {
        CircleCaptcha captcha = CommonUtils.getCaptcha(request);
        captcha.write(response.getOutputStream());
    }

    /**
     * 首页
     */
    @GetMapping("/index")
    public String index(HttpServletRequest request,
                        Integer aType,
                        ArticleListPageDto articleListPageDto,
                        Model model) {
        String articleType = null;
        if (ObjectUtil.isNotNull(aType)) {
            model.addAttribute("aType",aType);
            if(aType == 1) articleType = "技术分享";
            else if (aType == 2) articleType = "游戏推荐";
            else if (aType == 3) articleType = "软件推荐";
            else if (aType == 4) articleType = "其他";
        }

        Date now = DateUtil.date();
        List<Ad> ads = adService.list(Wrappers.<Ad>lambdaQuery()
                .eq(Ad::getAdTypeId, "13b107fcd9ecdca45edbc804f1b32e83")
                .le(Ad::getAdBeginTime, now)
                .ge(Ad::getAdEndTime, now)
                .orderByAsc(Ad::getAdSort));
        model.addAttribute("ads", ads);

        String articleTitle = articleListPageDto.getArticleTitle();
        IPage<ArticleListVo> page = new Page<>(articleListPageDto.getPageNumber(),articleListPageDto.getPageSize());
        IPage<ArticleListVo> articleListVoPage = articleService.listArticle(page,articleTitle,articleType);
        model.addAttribute("articlePage", CommonPage.restPage(articleListVoPage));
        if(StrUtil.isNotBlank(articleTitle)){
            model.addAttribute("articleTitle",articleTitle);
        }

        List<Link> links = linkService.list(Wrappers.<Link>lambdaQuery().orderByAsc(Link::getLinkSort));
        model.addAttribute("links",links);
        return "/view/index";
    }

    /**
     * 文章详情页面
     */
    @GetMapping("/article")
    public String articleDetails(HttpServletRequest request,@RequestParam("articleID") String articleId, Model model){
        ArticleListVo article = articleService.getArticleDetails(articleId);
        article.setUserName(CommonUtils.getHideMiddleStr(article.getUserName()));
        article.setArticleLookNumber(article.getArticleLookNumber() + 1);
        Article article1 = new Article();
        article1.setArticleId(article.getArticleId());
        article1.setArticleLookNumber(article.getArticleLookNumber());
        articleService.updateById(article1);
        model.addAttribute("article",article);
        List<ArticleTag> articleTagList = articleTagService.listTag(articleId);
        model.addAttribute("articleTags",articleTagList);
        List< CommentVo> comments = commentService.listComment(articleId);
        model.addAttribute("comments",comments);
        return "/view/article";
    }

    /**
     * 点赞文章
     */
    @GetMapping("/article/good")
    @ResponseBody
    @Transactional
    public CommonResult articleGood(HttpServletRequest request,String articleId){
        User user = (User)request.getSession().getAttribute("user");
        if(ObjectUtil.isNull(user)){
            return CommonResult.failed("请先登录在点赞哦亲~");
        }
        UserGoodArticle userGoodArticleServiceOne = userGoodArticleService.getOne(Wrappers.<UserGoodArticle>lambdaQuery().eq(UserGoodArticle::getArticleId, articleId).eq(UserGoodArticle::getUserId,user.getUserId()));
        if(Objects.nonNull(userGoodArticleServiceOne)){
            return CommonResult.failed("感谢支持，已经点过赞了哦亲~");
        }
        UserGoodArticle userGoodArticle = new UserGoodArticle();
        userGoodArticle.setArticleId(articleId);
        userGoodArticle.setUserId(user.getUserId());
        userGoodArticleService.save(userGoodArticle);
        Article article = articleService.getById(articleId);
        article.setArticleGoodNumber(article.getArticleGoodNumber()+1);
        articleService.updateById(article);
        return CommonResult.success("谢谢点赞~");
    }

    /**
     * 收藏文章
     */
    @GetMapping("/article/collection")
    @ResponseBody
    @Transactional
    public CommonResult articleCollection(HttpServletRequest request,String articleId){
        User user = (User)request.getSession().getAttribute("user");
        if(ObjectUtil.isNull(user)){
            return CommonResult.failed("请先登录在收藏哦亲~");
        }
        UserCollectionArticle userCollectionArticleServiceOne = userCollectionArticleService.getOne(Wrappers.<UserCollectionArticle>lambdaQuery().eq(UserCollectionArticle::getArticleId, articleId).eq(UserCollectionArticle::getUserId,user.getUserId()));
        if(Objects.nonNull(userCollectionArticleServiceOne)){
            return CommonResult.failed("该文章已经收藏了哦亲~ 快去个人中心看看吧~");
        }
        UserCollectionArticle userCollectionArticle = new UserCollectionArticle();
        userCollectionArticle.setArticleId(articleId);
        userCollectionArticle.setUserId(user.getUserId());
        userCollectionArticleService.save(userCollectionArticle);
        Article article = articleService.getById(articleId);
        article.setArticleCollectionNumber(article.getArticleCollectionNumber()+1);
        articleService.updateById(article);
        return CommonResult.success("已经帮亲放到个人收藏里了哦~");
    }

    /**
     * 删除评论
     */
    @PostMapping("/article/comment/delete")
    @ResponseBody
    public CommonResult deleteComment(HttpServletRequest request,String commentId,String userId){
        User user = (User) request.getSession().getAttribute("user");
        if(Objects.isNull(user)){
            return CommonResult.failed("删除失败，请先登录！");
        }
        if(!userId.equals(user.getUserId())){
            return CommonResult.failed("删除失败，此评论非您发布！");
        }
        if(commentService.removeById(commentId)){
            return CommonResult.success("删除成功！");
        }
        return CommonResult.failed("删除失败，请刷新重试！");
    }

    /**
     * 发布评论
     */
    @PostMapping("/article/comment/publish")
    @ResponseBody
    public CommonResult publishComment(HttpServletRequest request,String context,String articleId){
        Comment comment = new Comment();
        comment.setCommentContext(context);
        comment.setArticleId(articleId);
        comment.setCommentTime(DateUtil.date());
        comment.setUserId(((User) request.getSession().getAttribute("user")).getUserId());
        if(commentService.save(comment)){
            return CommonResult.success("评论发布成功");
        }
        return CommonResult.failed("评论发布失败");
    }
}
