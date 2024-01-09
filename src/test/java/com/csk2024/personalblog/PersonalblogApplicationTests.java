package com.csk2024.personalblog;

import cn.hutool.core.date.DateUtil;
import cn.hutool.crypto.SecureUtil;
import com.csk2024.personalblog.entity.Article;
import com.csk2024.personalblog.entity.ArticleType;
import com.csk2024.personalblog.entity.User;
import com.csk2024.personalblog.service.ArticleService;
import com.csk2024.personalblog.service.ArticleTagService;
import com.csk2024.personalblog.service.ArticleTypeService;
import com.csk2024.personalblog.service.UserService;
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
    private ArticleTagService articleTagService;

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
            article.setArticleCentext("这是文章正文");
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
            article.setArticleCentext("这是文章正文");
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
            article.setArticleCentext("这是文章正文");
            article.setArticleGoodNumber(0);
            article.setArticleLookNumber(0);
            article.setArticleCollectionNumber(0);
            articles.add(article);
        }
        articleService.saveBatch(articles,50);

    }

}
