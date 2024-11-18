package com.nrsc.elegant.repository.converters;

import com.nrsc.elegant.dto.UserInfoDTO;
import com.nrsc.elegant.pojo.UserInfo;

/**
 * @author sunchuan <sunchuan@kuaishou.com>
 * Created on 2024-11-18
 */
public class UserInfoConverter {
    public static UserInfoDTO convert2DTO(UserInfo userInfo) {
        return UserInfoDTO.builder()
                .id(userInfo.getId())
                .name(userInfo.getName())
                .age(userInfo.getAge())
                .sex(userInfo.getSex())
                .build();
    }
}
