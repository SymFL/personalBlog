package com.csk2024.personalblog.controller;

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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.constraints.NotBlank;
import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * 用户控制类
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private ArticleService articleService;
    @Autowired
    private ArticleTypeService articleTypeService;
    @Autowired
    private ArticleTagService articleTagService;
    @Autowired
    private ImgService imgService;
    @Autowired
    private ArticleTagListService articleTagListService;

    /**
     * 用户注册
     */
    @GetMapping("/register")
    public String userRegister() {
        return "/user/register";
    }

    /**
     * 用户注册提交
     */
    @PostMapping("/registerAction")
    @ResponseBody
    public CommonResult userRegisterAction(HttpServletRequest request,
                                           @NotBlank(message = "用户名不能为空！") String userName,
                                           @NotBlank(message = "密码不能为空！") String userPassword,
                                           String verifyCode) {
        HttpSession session = request.getSession();
        if (StrUtil.isBlank(verifyCode) || !verifyCode.equals(session.getAttribute("circleCaptchaCode"))) {
            session.removeAttribute("circleCaptchaCode");
            return CommonResult.failed("验证码不正确");
        }
        User user = userService.getOne(Wrappers.<User>lambdaQuery().eq(User::getUserName, userName), false);
        if (ObjectUtil.isNotNull(user)) {
            session.removeAttribute("circleCaptchaCode");
            return CommonResult.failed("用户名已存在！");
        }
        User user1 = new User();
        user1.setUserName(userName);
        user1.setUserPassword(userPassword);
        user1.setUserRegisterTime(DateUtil.date());
        user1.setUserFrozen(0);
        if (userService.save(user1)) {
            return CommonResult.success("创建成功！");
        }
        return CommonResult.failed("创建失败！");
    }

    /**
     * 用户登录
     */
    @GetMapping("/login")
    public String userLogin() {
        return "/user/login";
    }

    @PostMapping("/loginAction")
    @ResponseBody
    public CommonResult userLoginAction(HttpServletRequest request,
                                        @NotBlank(message = "用户名不存在！") String userName,
                                        @NotBlank(message = "密码错误！") String userPassword,
                                        String verifyCode) {
        HttpSession session = request.getSession();
        if (StrUtil.isBlank(verifyCode) || !verifyCode.equals(session.getAttribute("circleCaptchaCode"))) {
            session.removeAttribute("circleCaptchaCode");
            return CommonResult.failed("验证码不正确");
        }
        User user = userService.getOne(Wrappers.<User>lambdaQuery().eq(User::getUserName, userName)
                .eq(User::getUserPassword, userPassword), false);
        if (ObjectUtil.isNull(user)) {
            return CommonResult.failed("用户名或密码错误！");
        }
        if (user.getUserFrozen() == 1) {
            return CommonResult.failed("用户名已被冻结，请联系站长！");
        }
        session.setAttribute("user", user);
        return CommonResult.success("登陆成功！");
    }

    /**
     * 退出登录
     */
    @GetMapping("/logout")
    public String userLogout(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.removeAttribute("user");
        return "redirect:/index";
    }

    /**
     * 个人中心
     */
    @GetMapping("/")
    public String userPersonal(HttpServletRequest request, Model model) {
        if (ObjectUtil.isNotNull(request.getSession().getAttribute("user"))) {
            User user = (User) request.getSession().getAttribute("user");
            model.addAttribute("registerTime", user.getUserRegisterTime());
            model.addAttribute("name", user.getUserName());
        }
        return "/user/personal";
    }

    /**
     * 用户收藏
     */
    @GetMapping("/collection")
    public String userCollection(HttpServletRequest request, ArticleListPageDto articleListPageDto, Model model) {
        User user = (User) request.getSession().getAttribute("user");
        IPage<Article> page = new Page<>(articleListPageDto.getPageNumber(), articleListPageDto.getPageSize());
        IPage<ArticleListVo> articleListVoPage = articleService.listCollectionArticle(page, user.getUserId(), articleListPageDto.getArticleTitle());
        model.addAttribute("articlePage", CommonPage.restPage(articleListVoPage));
        if (StrUtil.isNotBlank(articleListPageDto.getArticleTitle())) {
            model.addAttribute("articleTitle", articleListPageDto.getArticleTitle());
        }
        return "/user/collection";
    }

    /**
     * 用户个人文章
     */
    @GetMapping("/article")
    public String userArticle(HttpServletRequest request, ArticleListPageDto articleListPageDto, Model model) {
        User user = (User) request.getSession().getAttribute("user");
        IPage<Article> page = new Page<>(articleListPageDto.getPageNumber(), articleListPageDto.getPageSize());
        IPage<ArticleListVo> articleListVoPage = articleService.listUserArticle(page, user.getUserId(), articleListPageDto.getArticleTitle());
        model.addAttribute("articlePage", CommonPage.restPage(articleListVoPage));
        if (StrUtil.isNotBlank(articleListPageDto.getArticleTitle())) {
            model.addAttribute("articleTitle", articleListPageDto.getArticleTitle());
        }
        return "/user/article";
    }

    @GetMapping("/article/public")
    public String articlePublic(Model model) {
        List<ArticleType> articleTypes = articleTypeService.list(Wrappers.<ArticleType>lambdaQuery().orderByAsc(ArticleType::getArticleTypeSort));
        model.addAttribute("articleTypes", articleTypes);

        List<ArticleTag> articleTags = articleTagService.list(Wrappers.<ArticleTag>lambdaQuery().orderByDesc(ArticleTag::getArticleTagAddTime));
        model.addAttribute("articleTags", articleTags);

        return "/user/articlePublic";
    }

    @PostMapping("/upload/img")
    @ResponseBody
    public Map<String, Object> uploadImg(@RequestParam("img") MultipartFile file) {
        Img one = imgService.getOne(Wrappers.<Img>lambdaQuery().eq(Img::getImgSize, file.getSize()));
        Map<String, Object> map = new HashMap<>();
        if(Objects.nonNull(one)){
            map.put("errno", 0);
            Map<String, Object> data = new HashMap<>();
            data.put("url", one.getImgUrl());
            data.put("alt", "");
            data.put("href", "");
            map.put("data", data);
            return map;
        }

        String savePath = CommonUtils.getUploadPath();
        String filename = file.getOriginalFilename();
        String substring = filename.substring(0, filename.lastIndexOf("."));
        filename = substring + "_" + DateUtil.format(DateUtil.date(), "yyyyMMddHHmmss") + filename.substring(filename.lastIndexOf("."));
        File dest = new File(savePath + File.separator + filename);


        // 保存文件
        try {
            file.transferTo(dest);
            Img img = new Img();
            img.setImgAddTime(DateUtil.date());
            img.setImgUrl(CommonUtils.getUploadFilePath() + filename);
            img.setImgSize(file.getSize());
            imgService.save(img);

            map.put("errno", 0);
            Map<String, Object> data = new HashMap<>();
            data.put("url", CommonUtils.getUploadFilePath() + filename);
            data.put("alt", "");
            data.put("href", "");
            map.put("data", data);

            return map;

        } catch (IOException e) {
            map.put("errno", 1);
            map.put("message", "上传失败！");
            return map;
        }
    }

    @PostMapping("/article/upload")
    @ResponseBody
    @Transactional(rollbackFor = Exception.class)
    public CommonResult articleUpload(HttpServletRequest request,@NotBlank(message = "文章标题不能为空") String articleTitle,@NotBlank(message = "文章类型不能为空") String articleTypeId,@NotBlank(message = "文章内容不能为空") String html,String articleTagIds){
        String[] artTagIds = articleTagIds.split(",");
        User user = (User)request.getSession().getAttribute("user");
        Article article = new Article();
        article.setUserId(user.getUserId());
        article.setArticleTypeId(articleTypeId);
        article.setArticleTitle(articleTitle);
        article.setArticleAddTime(DateUtil.date());
        article.setArticleContext(html);
        article.setArticleGoodNumber(0);
        article.setArticleLookNumber(0);
        article.setArticleCollectionNumber(0);

        List<ArticleTagList> articleTagList = new ArrayList<>();
        if(articleService.save(article)){
            for (String articleTagId : artTagIds) {
                ArticleTagList articleTag = new ArticleTagList();
                articleTag.setArticleId(article.getArticleId());
                articleTag.setArticleTagId(articleTagId);
                articleTagList.add(articleTag);
            }
            articleTagListService.saveBatch(articleTagList,50);
            return CommonResult.success("上传成功!");
        }
        return CommonResult.failed("上传失败！");
    }

}
