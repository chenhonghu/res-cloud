package com.yc.res.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;

/**
 * @program: res_mvc_boot
 * @description:
 * @author: hgdd
 * @create: 2021-05-05 11:38
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)//排除空子段 在生成的json字段钟

public class JsonModel implements Serializable {
private Integer code;
private String msg;
private Object obj;
private String url;
}
