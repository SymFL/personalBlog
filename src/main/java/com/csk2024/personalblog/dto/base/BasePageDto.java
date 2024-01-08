package com.csk2024.personalblog.dto.base;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 前端返回分页数据基础类
 */
@Data
public class BasePageDto {
    @NotNull(message = "未找到当前页码")
    private Integer pageNumber = 1;
    private Integer pageSize = 20;
}
