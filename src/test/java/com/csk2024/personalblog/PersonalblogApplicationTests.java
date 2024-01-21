package com.csk2024.personalblog;

import cn.hutool.core.date.DateUtil;
import cn.hutool.crypto.SecureUtil;
import com.csk2024.personalblog.entity.*;
import com.csk2024.personalblog.service.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@SpringBootTest
class PersonalblogApplicationTests {

    @Autowired
    private UserService userService;
    @Autowired
    private ArticleTypeService articleTypeService;
    @Autowired
    private ArticleService articleService;
    @Autowired
    private LinkService linkService;
    @Autowired
    private AdTypeService adTypeService;
    @Autowired
    private AdService adService;

    @Test
    void users() {
        List<User> users = new ArrayList<>();

        for (int i = 0; i < 50; i++) {
            User user = new User();
            user.setUserName("test" + i);
            user.setUserPassword(SecureUtil.md5("123456"));
            user.setUserRegisterTime(DateUtil.date());
            user.setUserFrozen(0);
            users.add(user);
        }
        userService.saveBatch(users,50);

    }

    @Test
    void articleType() {
        List<ArticleType> articleTypes = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            ArticleType articleType = new ArticleType();
            articleType.setArticleTypeName("测试类型" + i);
            articleType.setArticleTypeSort(10);
            articleType.setArticleTypeAddTime(DateUtil.date());
            articleTypes.add(articleType);
        }
        articleTypeService.saveBatch(articleTypes,3);

    }

    @Test
    void article() {
        List<Article> articles = new ArrayList<>();
        List<User> users = userService.list();
        List<ArticleType> types = articleTypeService.list();

        for (int i = 0; i < 50; i++) {
            Article article = new Article();
            article.setUserId(users.get(ThreadLocalRandom.current().nextInt(users.size())).getUserId());
            article.setArticleTypeId(types.get(0).getArticleTypeId());
            article.setArticleTitle("测试文章" + i);
            article.setArticleAddTime(DateUtil.date());
            article.setArticleContext("这是文章正文");
            article.setArticleGoodNumber(0);
            article.setArticleLookNumber(0);
            article.setArticleCollectionNumber(0);
            articles.add(article);
        }
        for (int i = 50; i < 100; i++) {
            Article article = new Article();
            article.setUserId(users.get(ThreadLocalRandom.current().nextInt(users.size())).getUserId());
            article.setArticleTypeId(types.get(1).getArticleTypeId());
            article.setArticleTitle("测试文章" + i);
            article.setArticleAddTime(DateUtil.date());
            article.setArticleContext("这是文章正文");
            article.setArticleGoodNumber(0);
            article.setArticleLookNumber(0);
            article.setArticleCollectionNumber(0);
            articles.add(article);
        }
        for (int i = 100; i < 150; i++) {
            Article article = new Article();
            article.setUserId(users.get(ThreadLocalRandom.current().nextInt(users.size())).getUserId());
            article.setArticleTypeId(types.get(2).getArticleTypeId());
            article.setArticleTitle("测试文章" + i);
            article.setArticleAddTime(DateUtil.date());
            article.setArticleContext("这是文章正文");
            article.setArticleGoodNumber(0);
            article.setArticleLookNumber(0);
            article.setArticleCollectionNumber(0);
            articles.add(article);
        }
        articleService.saveBatch(articles,50);

    }

    @Test
    void addLink(){
        List<Link> links = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Link link = new Link();
            link.setLinkTitle("百度" + i);
            link.setLinkSort(10);
            link.setLinkUrl("www.baidu.com");
            link.setLinkLogoUrl("https://www.baidu.com/img/PCtm_d9c8750bed0b3c7d089fa7d55720d6cf.png");
            link.setLinkAddTime(DateUtil.date());
            links.add(link);
        }
        linkService.saveBatch(links,10);
    }

    @Test
    void addAdType(){
        List<AdType> adTypes = new ArrayList<>();

        AdType adType1 = new AdType();
        adType1.setAdTypeTitle("首页轮播图广告");
        adType1.setAdTypeTag("HeadAd");
        adType1.setAdTypeSort(1);
        adType1.setAdTypeAddTime(DateUtil.date());
        adTypes.add(adType1);

        AdType adType2 = new AdType();
        adType2.setAdTypeTitle("文章页面广告");
        adType2.setAdTypeTag("ArticleAd");
        adType2.setAdTypeSort(2);
        adType2.setAdTypeAddTime(DateUtil.date());
        adTypes.add(adType2);

        adTypeService.saveBatch(adTypes,2);
    }

    @Test
    void addAd(){
        ArrayList<Ad> ads = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            Ad ad = new Ad();
            ad.setAdTypeId("");
            ad.setAdTitle("广告" + i);
            ad.setAdImgUrl("https://www.baidu.com/img/PCtm_d9c8750bed0b3c7d089fa7d55720d6cf.png");
            ad.setAdLinkUrl("https://www.baidu.com/");
            ad.setAdSort(10);
            ad.setAdBeginTime(DateUtil.date());
            ad.setAdEndTime(DateUtil.date());
            ad.setAdAddTime(DateUtil.date());
            ads.add(ad);
        }
        adService.saveBatch(ads,4);
    }

}
