package com.nrsc.elegant.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.nrsc.elegant.pojo.UserInfo;

/**
 * @author sunchuan <sunchuan@kuaishou.com>
 * Created on 2024-11-18
 */
@Repository
public class CollectionDemoRepository {
    public List<UserInfo> getUserInfoListByIds(List<Long> userIds) {
        return null;
    }

    public UserInfo getUserInfoById(Long userId) {
        return null;
    }
}
