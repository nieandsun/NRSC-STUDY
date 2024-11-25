package com.nrsc.service;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.nrsc.service.param.validation.ParamsCheck;
import com.nrsc.dto.UserInfoDTO;
import com.nrsc.validation.group.UserInfoGroup;

/**
 * @author sunchuan <sunchuan@kuaishou.com>
 * Created on 2024-11-19
 */
@Service
public class UserServiceImpl {

    @ParamsCheck
    public void saveUser(UserInfoDTO userInfoDTO) {

    }

    @ParamsCheck
    public void updateUser(@Validated(UserInfoGroup.Update.class) UserInfoDTO userInfoDTO) {

    }
}
