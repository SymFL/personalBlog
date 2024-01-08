package com.csk2024.personalblog.dto.user;

import com.csk2024.personalblog.dto.base.BasePageDto;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * 前端返回用户数据类
 */
@Data
public class UserDto extends BasePageDto {
    @NotBlank(message = "用户 id 不能为空")
    private String userId;

    private String userName;

    private String userPassword;

    private Integer userFrozen;
}
