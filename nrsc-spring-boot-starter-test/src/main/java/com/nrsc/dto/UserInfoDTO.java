package com.nrsc.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import com.nrsc.service.param.validation.NeedValidateBean;
import com.nrsc.validation.group.UserInfoGroup;

import lombok.Data;

/**
 * @author sunchuan <sunchuan@kuaishou.com>
 * Created on 2024-11-19
 */
@Data
public class UserInfoDTO implements NeedValidateBean {
    @NotBlank(message = "用户名不能为空")
    private String name;

    @Min(value = 18, message = "年龄必须大于18岁")
    private Integer age;

    @Length(max = 10, message = "描述信息不能大于10个字符", groups = UserInfoGroup.Update.class)
    private String descInfo;


}
