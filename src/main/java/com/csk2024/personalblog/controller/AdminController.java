package com.csk2024.personalblog.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.system.HostInfo;
import cn.hutool.system.OsInfo;
import cn.hutool.system.SystemUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.csk2024.personalblog.dto.user.UserDto;
import com.csk2024.personalblog.dto.user.UserListPageDto;
import com.csk2024.personalblog.entity.Article;
import com.csk2024.personalblog.entity.User;
import com.csk2024.personalblog.service.ArticleService;
import com.csk2024.personalblog.service.ArticleTagService;
import com.csk2024.personalblog.service.UserService;
import com.csk2024.personalblog.utils.CommonPage;
import com.csk2024.personalblog.utils.CommonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
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
    public String userList(UserListPageDto userListPageDto, Model model){
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

    @PostMapping("/user/update")
    @ResponseBody
    public CommonResult userUpdate(UserDto userDto){
        User user = userService.getById(userDto.getUserId());
        if(Objects.isNull(user)){
            return CommonResult.failed("用户 id 错误");
        }
        String userPassword = userDto.getUserPassword();
        Date userRegisterTime = user.getUserRegisterTime();
        if(StrUtil.isNotBlank(userPassword)){
            userDto.setUserPassword(SecureUtil.md5(userPassword + userRegisterTime));
        }else {
            userDto.setUserPassword(null);
        }
        BeanUtil.copyProperties(userDto,user);
        if(userService.updateById(user)){
            return CommonResult.success("更新成功");
        }
        return CommonResult.success("更新失败");
    }
}
