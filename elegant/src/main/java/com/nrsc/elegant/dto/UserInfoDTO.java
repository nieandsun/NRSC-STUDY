package com.nrsc.elegant.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author sunchuan <sunchuan@kuaishou.com>
 * Created on 2024-11-18
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserInfoDTO {

    private Long id;

    private String name;

    private Integer age;

    private String sex;

}
