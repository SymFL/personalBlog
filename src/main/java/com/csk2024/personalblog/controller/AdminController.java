package com.csk2024.personalblog.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.system.HostInfo;
import cn.hutool.system.OsInfo;
import cn.hutool.system.SystemUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.csk2024.personalblog.dto.ad.AdTypeUpdateDto;
import com.csk2024.personalblog.dto.ad.AdUpdateDto;
import com.csk2024.personalblog.dto.article.ArticleListPageDto;
import com.csk2024.personalblog.dto.article.ArticleTagAddOrUpdateDto;
import com.csk2024.personalblog.dto.article.ArticleTypeAddOrUpdateDto;
import com.csk2024.personalblog.dto.link.LinkUpdateDto;
import com.csk2024.personalblog.dto.user.UserDto;
import com.csk2024.personalblog.dto.user.UserListPageDto;
import com.csk2024.personalblog.entity.*;
import com.csk2024.personalblog.service.*;
import com.csk2024.personalblog.utils.CommonPage;
import com.csk2024.personalblog.utils.CommonResult;
import com.csk2024.personalblog.vo.AdListVo;
import com.csk2024.personalblog.vo.ArticleListVo;
import com.csk2024.personalblog.vo.ArticleTypeVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.List;
import java.util.Objects;


@Controller
@RequestMapping("/csk2024")
public class AdminController {

    @Autowired
    private ArticleService articleService;
    @Autowired
    private ArticleTagService articleTagService;
    @Autowired
    private UserService userService;
    @Autowired
    private ArticleTypeService articleTypeService;
    @Autowired
    private ArticleTagListService articleTagListService;
    @Autowired
    private LinkService linkService;
    @Autowired
    private AdService adService;
    @Autowired
    private AdTypeService adTypeService;
    @Autowired
    private AdminService adminService;

    /**
     * 管理员登录页面
     */
    @GetMapping("/login")
    public String Login(){
        return "/admin/adminLogin";
    }

    /**
     * 管理员登出
     */
    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        request.getSession().removeAttribute("admin");
        return "redirect:/csk2024/login";
    }

    /**
     * 管理员登录提交
     */
    @PostMapping("/adminLogin")
    @ResponseBody
    public CommonResult adminLogin(HttpServletRequest request,
                                   String adminName,
                                   String adminPassword,
                                   String verifyCode) {
        HttpSession session = request.getSession();
        if (StrUtil.isBlank(verifyCode) || !verifyCode.equals(session.getAttribute("circleCaptchaCode"))) {
            session.removeAttribute("circleCaptchaCode");
            return CommonResult.failed("验证码不正确");
        }
        Admin admin = adminService.getOne(Wrappers.<Admin>lambdaQuery()
                .eq(Admin::getAdminName, adminName)
                .eq(Admin::getAdminPassword, adminPassword), false);
        if (Objects.isNull(admin)) {
            session.removeAttribute("circleCaptchaCode");
            return CommonResult.failed("用户名或者密码不正确");
        }
        session.setAttribute("admin", admin);
        return CommonResult.success("登录成功");
    }


    /**
     * 管理端基础数据页面
     */
    @GetMapping("/")
    public String adminIndex(Model model){
        //系统信息
        OsInfo osInfo = SystemUtil.getOsInfo();
        HostInfo hostInfo = SystemUtil.getHostInfo();
        model.addAttribute("osName",osInfo.getName());
        model.addAttribute("hostAddress",hostInfo.getAddress());
        //用户数量
        int userCount = userService.count();
        model.addAttribute("userCount",userCount);
        //文章数量
        int articleCount = articleService.count();
        int articleTagCount = articleTagService.count();
        model.addAttribute("articleCount",articleCount);
        model.addAttribute("articleTagCount",articleTagCount);

        return "/admin/index";
    }

    /**
     * 用户列表
     */
    @GetMapping ("/user/list")
    public String userList(@Valid UserListPageDto userListPageDto, Model model){
        Integer pageNumber = userListPageDto.getPageNumber();
        Integer pageSize = userListPageDto.getPageSize();

        Page<User> userPage = new Page<>(pageNumber,pageSize);
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<User>().orderByDesc(User::getUserRegisterTime);
        if(userListPageDto.getUserName()!=null){
            model.addAttribute("userName",userListPageDto.getUserName());
            queryWrapper.like(User::getUserName,userListPageDto.getUserName());
        }
        IPage<User> userIpage = userService.page(userPage,queryWrapper);
        model.addAttribute("userPage", CommonPage.restPage(userIpage));


        return "/admin/userList";

    }

    /**
     * 用户删除
     */
    @PostMapping("/user/del")
    @ResponseBody
    public CommonResult userDelete(String userId){
        if(StrUtil.isBlank(userId)){
            return CommonResult.failed("用户id不能为空");
        }
        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<Article>().eq(Article::getUserId,userId);
        if(articleService.count(queryWrapper)>0){
            return CommonResult.failed("该用户存在文章，无法删除,请进行冻结");
        }
        if(userService.removeById(userId)){
            return CommonResult.success("删除成功");
        }
        return CommonResult.failed("删除失败");
    }

    /**
     * 用户修改
     */
    @PostMapping("/user/update")
    @ResponseBody
    public CommonResult userUpdate(@Valid UserDto userDto){
        User user = userService.getById(userDto.getUserId());
        if(Objects.isNull(user)){
            return CommonResult.failed("用户 id 错误");
        }
        String userPassword = userDto.getUserPassword();
        if(StrUtil.isBlank(userPassword)){
            userDto.setUserPassword(null);
        }
        BeanUtil.copyProperties(userDto,user);
        if(userService.updateById(user)){
            return CommonResult.success("更新成功");
        }
        return CommonResult.success("更新失败");
    }

    /**
     * 文章类型列表
     */
    @GetMapping ("/article/type/list")
    public String articleTypeList(Model model){
        List<ArticleTypeVo> articleTypeVoList = articleTypeService.articleTypeVoList();
        model.addAttribute("typeList", articleTypeVoList);
        return "/admin/articleTypeList";
    }

    /**
     * 删除文章类型
     */
    @PostMapping("/article/type/del")
    @ResponseBody
    public CommonResult articleTypeDelete(@NotBlank(message = "文章类型 id 不能为空") String articleTypeId){

        LambdaQueryWrapper<Article> wrapper = new LambdaQueryWrapper<Article>().eq(Article::getArticleTypeId, articleTypeId);
        if(articleService.count(wrapper) > 0){
            return CommonResult.failed("当前类型还有文章使用，请先删除文章或者取消文章对该类型的使用");
        }

        if(articleTypeService.removeById(articleTypeId)){
            return CommonResult.success("删除成功！");
        }
        return CommonResult.failed("删除失败！");
    }

    /**
     * 增加或修改文章类型
     */
    @PostMapping("/article/type/addOrUpdate")
    @ResponseBody
    public CommonResult articleTypeAddOrUpdate(@Valid ArticleTypeAddOrUpdateDto articleTypeAddOrUpdateDto){
        ArticleType articleType = new ArticleType();
        BeanUtil.copyProperties(articleTypeAddOrUpdateDto,articleType);
        articleType.setArticleTypeAddTime(DateUtil.date());

        String articleTypeId = articleType.getArticleTypeId();
        if(StrUtil.isNotBlank(articleTypeId)){
            if(articleTypeService.updateById(articleType)){
                return CommonResult.success("修改成功！");
            }
            return CommonResult.failed("修改失败！");
        }

        if(articleTypeService.save(articleType)){
            return CommonResult.success("保存成功！");
        }
        return CommonResult.failed("保存失败！");
    }

    /**
     * 文章标签列表
     */
    @GetMapping("/article/tag/list")
    public String articleTagList(Model model){
        LambdaQueryWrapper<ArticleTag> wrapper = new LambdaQueryWrapper<ArticleTag>().orderByDesc(ArticleTag::getArticleTagAddTime);
        List<ArticleTag> articleTagList = articleTagService.list(wrapper);
        model.addAttribute("articleTags",articleTagList);
        return "/admin/articleTagList";
    }

    /**
     * 增加或修改文章标签
     */
    @PostMapping("/article/tag/addOrUpdate")
    @ResponseBody
    public CommonResult articleTagAddOrUpdate(@Valid ArticleTagAddOrUpdateDto articleTagAddOrUpdateDto){
        String articleTagId = articleTagAddOrUpdateDto.getArticleTagId();
        ArticleTag articleTag = new ArticleTag();
        BeanUtil.copyProperties(articleTagAddOrUpdateDto,articleTag);
        if(StrUtil.isNotBlank(articleTagId)){
            if(articleTagService.updateById(articleTag)){
                return CommonResult.success("更新成功！");
            }
            return CommonResult.failed("更新失败！");
        }
        articleTag.setArticleTagAddTime(DateUtil.date());
        if(articleTagService.save(articleTag)){
            return CommonResult.success("添加成功");
        }
        return CommonResult.failed("添加失败");
    }

    /**
     * 删除文章标签
     */
    @PostMapping("/article/tag/delete")
    @ResponseBody
    public CommonResult articleTageDelete(@NotBlank(message = "标签 id 不能为空") String articleTagId){
        LambdaQueryWrapper<ArticleTagList> wrapper = new LambdaQueryWrapper<ArticleTagList>().eq(ArticleTagList::getArticleTagId, articleTagId);
        if(articleTagListService.count(wrapper) > 0){
            return CommonResult.failed("当前标签尚在使用中，请先取消文章与标签的关联");
        }
        if(articleTagService.removeById(articleTagId)){
            return CommonResult.success("删除成功!");
        }
        return CommonResult.failed("删除失败！");
    }

    /**
     * 文章列表
     */
    @GetMapping("/article/list")
    public String articleList(ArticleListPageDto articleListPageDto,Model model){
        String articleTitle = articleListPageDto.getArticleTitle();
        IPage<ArticleListVo> page = new Page<>(articleListPageDto.getPageNumber(),articleListPageDto.getPageSize());
        IPage<ArticleListVo> articleListVoPage = articleService.listArticleListVo(page,articleTitle);
        model.addAttribute("articlePage",CommonPage.restPage(articleListVoPage));
        if(StrUtil.isNotBlank(articleTitle)){
            model.addAttribute("articleTitle",articleTitle);
        }
        return "/admin/articleList";
    }

    /**
     * 文章删除
     */
    @PostMapping("/article/delete")
    @ResponseBody
    public CommonResult articleDelete(@NotBlank(message = "文章 id 不允许为空") String articleId){
        if(articleService.removeById(articleId)){
            return CommonResult.success("删除成功！");
        }
        return CommonResult.failed("删除失败！");
    }

    /**
     * 友链列表
     */
    @GetMapping("/link/list")
    public String linkList(Model model){
        List<Link> links = linkService.list(Wrappers.<Link>lambdaQuery().orderByAsc(Link::getLinkSort));
        model.addAttribute("links",links);
        return "/admin/linkList";
    }

    /**
     * 友链增加或修改
     */
    @PostMapping("/link/addOrUpdate")
    @ResponseBody
    public CommonResult linkAddOrUpdate(@Valid LinkUpdateDto linkUpdateDto){
        String LinkId = linkUpdateDto.getLinkId();
        Link link = new Link();
        BeanUtil.copyProperties(linkUpdateDto,link);
        if(StrUtil.isNotBlank(LinkId)){
            if(linkService.updateById(link)){
                return CommonResult.success("更新成功！");
            }
            return CommonResult.failed("更新失败！");
        }
        link.setLinkAddTime(DateUtil.date());
        if(linkService.save(link)){
            return CommonResult.success("更新成功！");
        }
        return CommonResult.failed("更新失败！");
    }

    /**
     * 友链删除
     */
    @PostMapping("/link/delete")
    @ResponseBody
    public CommonResult linkDelete(@NotBlank(message = "友链 ID 不能为空") String linkId){
        if(linkService.removeById(linkId)){
            return CommonResult.success("删除成功！");
        }
        return CommonResult.failed("删除失败！");
    }

    /**
     * 广告列表
     */
    @GetMapping("/ad/list")
    public String adList(String adTypeId, Model model){
        List<AdType> adTypes = adTypeService.list(Wrappers.<AdType>lambdaQuery().orderByAsc(AdType::getAdTypeSort));
        model.addAttribute("adTypes",adTypes);

        List<AdListVo> ads = adService.adList(adTypeId);
        model.addAttribute("ads",ads);

        return "/admin/adList";
    }

    /**
     * 广告类型添加或修改
     */
    @PostMapping("/ad/type/addOrUpdate")
    @ResponseBody
    public CommonResult adTypeAddOrUpdate(@Valid AdTypeUpdateDto adTypeUpdateDto){
        String adTypeId = adTypeUpdateDto.getAdTypeId();
        AdType adType = new AdType();
        BeanUtil.copyProperties(adTypeUpdateDto,adType);

        if(StrUtil.isNotBlank(adTypeId)){
            if(adTypeService.updateById(adType)){
                return CommonResult.success("更新成功！");
            }
            return CommonResult.failed("更新失败！");
        }
        adType.setAdTypeAddTime(DateUtil.date());
        if(adTypeService.save(adType)){
            return CommonResult.success("保存成功！");
        }
        return CommonResult.failed("保存失败！");
    }

    /**
     * 广告类型删除
     */
    @PostMapping("/ad/type/delete")
    @ResponseBody
    public CommonResult adTypeDelete(@NotBlank(message = "类型 Id 不允许为空") String adTypeId){
        if(adTypeService.removeById(adTypeId)){
            return CommonResult.success("删除成功！");
        }
        return CommonResult.failed("删除失败！");
    }

    /**
     * 广告删除
     */
    @PostMapping("/ad/delete")
    @ResponseBody
    public CommonResult adDelete(@NotBlank(message = "广告 Id 不允许为空") String adId){
        if(adService.removeById(adId)){
            return CommonResult.success("删除成功！");
        }
        return CommonResult.failed("删除失败！");
    }

    /**
     * 广告添加/修改
     */
    @PostMapping("/ad/addOrUpdate")
    @ResponseBody
    public CommonResult adAddOrUpdate(@Valid AdUpdateDto adUpdateDto){
        String adId = adUpdateDto.getAdId();
        Date adBeginTime = DateUtil.parse(adUpdateDto.getAdBeginTime());
        Date adEndTime = DateUtil.parse(adUpdateDto.getAdEndTime());
        Ad ad = new Ad();
        BeanUtil.copyProperties(adUpdateDto,ad);
        ad.setAdBeginTime(adBeginTime);
        ad.setAdEndTime(adEndTime);

        if(StrUtil.isNotBlank(adId)){
            if(adService.updateById(ad)){
                return CommonResult.success("更新成功！");
            }
            return CommonResult.failed("更新失败！");
        }
        ad.setAdAddTime(DateUtil.date());
        if(adService.save(ad)){
            return CommonResult.success("保存成功！");
        }
        return CommonResult.failed("保存失败！");
    }
}
