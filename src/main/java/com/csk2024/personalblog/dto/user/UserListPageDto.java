package com.csk2024.personalblog.dto.user;

import com.csk2024.personalblog.dto.base.BasePageDto;
import lombok.Data;

/**
 * 前端返回用户分页数据类
 */
@Data
public class UserListPageDto extends BasePageDto {
    /**
     * 用户名
     */
    private String userName;
}
