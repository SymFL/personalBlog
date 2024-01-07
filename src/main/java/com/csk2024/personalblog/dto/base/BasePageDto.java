package com.csk2024.personalblog.dto.base;

import lombok.Data;

@Data
public class BasePageDto {
    private Integer pageNumber = 1;
    private Integer pageSize = 20;
}
