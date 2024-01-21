package com.csk2024.personalblog.controller;

import cn.hutool.captcha.CircleCaptcha;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.csk2024.personalblog.dto.article.ArticleListPageDto;
import com.csk2024.personalblog.entity.Ad;
import com.csk2024.personalblog.entity.Link;
import com.csk2024.personalblog.service.*;
import com.csk2024.personalblog.utils.CommonPage;
import com.csk2024.personalblog.utils.CommonUtils;
import com.csk2024.personalblog.vo.ArticleListVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/")
public class ViewController {
    @Autowired
    private UserService userService;
    @Autowired
    private ArticleTypeService articleTypeService;
    @Autowired
    private ArticleService articleService;
    @Autowired
    private AdService adService;
    @Autowired
    private LinkService linkService;

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
}
