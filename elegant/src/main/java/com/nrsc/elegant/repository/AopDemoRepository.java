package com.nrsc.elegant.repository;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.nrsc.elegant.pojo.UserInfo;

/***
 *  @author : Sun Chuan
 *  @date : 2019/12/1 22:08
 *  Description：
 */
@Repository
public class AopDemoRepository {

    public List<UserInfo> getUserInfoListByPositionCode(Long positionCode) {
        Long i = 1 / positionCode;
        UserInfo u1 = new UserInfo("小明", 18, "male");
        UserInfo u2 = new UserInfo("小红", 18, "female");
        return Arrays.asList(u1, u2);
    }
}
