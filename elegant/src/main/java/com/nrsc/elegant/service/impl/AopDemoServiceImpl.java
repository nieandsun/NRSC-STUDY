package com.nrsc.elegant.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nrsc.elegant.annotation.LogAnnotation;
import com.nrsc.elegant.pojo.UserInfo;
import com.nrsc.elegant.repository.AopDemoRepository;
import com.nrsc.elegant.service.AopDemoService;

import lombok.extern.slf4j.Slf4j;

/***
 *  @author : Sun Chuan
 *  @date : 2019/12/1 21:39
 *  Description：
 */
@Service
@Slf4j
public class AopDemoServiceImpl implements AopDemoService {

    @Autowired
    private AopDemoRepository aopRepository;

    /***
     * 臃肿的代码
     * @param positionCode
     * @return
     */
    public List<UserInfo> getUserInfoListByPositionCode111(Long positionCode) {
        //(0) 还可能先去缓存里拿数据，如果拿不到再走下面的逻辑
        //(1) 记录日志
        String loginUserName = "james"; //模拟获取当前登陆用户的方法
        log.info("【{}】调用getUserInfoListByPositionCode方法获取信息，参数为:【{}】",
                loginUserName, positionCode);
        //(2) 异常捕捉
        try {
            //(3)真正的业务代码
            List<UserInfo> userInfos = aopRepository.getUserInfoListByPositionCode(positionCode);

            //(3)-1  还可能有将拿到的数据存到缓存等其他操作
            return userInfos;
        } catch (Exception e) {
            //(4) 捕捉到异常后打个日志
            log.error("【{}】调用getUserInfoListByPositionCode方法查询数据库出错，参数为:【{}】",
                    loginUserName, positionCode);
            //(5)将异常抛出交由统一异常处理类处理
            throw e;
        }
    }

    @Override
    @LogAnnotation(key = "#positionCode", needLog = true)
    public List<UserInfo> getUserInfoListByPositionCode(Long positionCode) {
        return aopRepository.getUserInfoListByPositionCode(positionCode);
    }
}
