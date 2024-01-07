package com.csk2024.personalblog.dto.user;

import com.csk2024.personalblog.dto.base.BasePageDto;
import lombok.Data;

@Data
public class UserListPageDto extends BasePageDto {
    /**
     * 用户名
     */
    private String userName;
}
