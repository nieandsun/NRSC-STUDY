package com.nrsc.elegant.repository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.ListUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.nrsc.elegant.dto.UserInfoDTO;
import com.nrsc.elegant.pojo.UserInfo;
import com.nrsc.elegant.repository.converters.UserInfoConverter;

import lombok.extern.slf4j.Slf4j;

/**
 * @author sunchuan <sunchuan@kuaishou.com>
 * Created on 2024-11-18
 */
@Slf4j
@Repository
public class CollectionDemoService {

    @Autowired
    private CollectionDemoRepository collectionDemoRepository;

    public List<UserInfoDTO> getUserInfoListByIds_001(List<Long> userIds) {
        List<UserInfo> userInfos = collectionDemoRepository.getUserInfoListByIds(userIds);
        //为了防止下面convert2DTO方法出现NPE，
        //这里要先判断集合是否为空，如果是，直接返回空集合
        if (CollectionUtils.isEmpty(userInfos)) {
            return Collections.emptyList();
        }
        //如果集合不为空，将pojo对象转成DTO
        return userInfos.stream()
                .map(UserInfoConverter::convert2DTO)
                .collect(Collectors.toList());
    }

    public List<UserInfoDTO> getUserInfoListById_002(List<Long> userIds) {
        return ListUtils.emptyIfNull(
                        collectionDemoRepository.getUserInfoListByIds(userIds)
                )
                .stream()
                .map(UserInfoConverter::convert2DTO)
                .collect(Collectors.toList());
    }


    public UserInfoDTO getUserInfoById_001(Long userId) {
        //为了防止下面convert2DTO方法出现NPE，
        //这里要先判断对象是否为空，如果是，直接返回null
        UserInfo userInfo = collectionDemoRepository.getUserInfoById(userId);
        if (userInfo == null) {
            return null;
        }

        //如果对象不为空，将pojo对象转成DTO
        return UserInfoConverter.convert2DTO(userInfo);
    }

    public UserInfoDTO getUserInfoById_002(Long userId) {
        return Optional.ofNullable(collectionDemoRepository.getUserInfoById(userId))
                .map(UserInfoConverter::convert2DTO)//如果不为null走map方法
                .orElse(null);//如果为null，就返回null
    }
}
