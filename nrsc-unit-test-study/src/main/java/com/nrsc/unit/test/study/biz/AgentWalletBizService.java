package com.nrsc.unit.test.study.biz;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.nrsc.unit.test.study.util.TransactionUtil;

import lombok.extern.slf4j.Slf4j;

/**
 * @author sunchuan <sunchuan@kuaishou.com>
 * Created on 2024-11-08
 */
@Slf4j
@Service
public class AgentWalletBizService {
    @Resource
    //假设该类为一个rpc服务
    private AccountWalletRPCService accountWalletRPCService;

    public String transferFromAgent2Account(Object transferRequest) {

        TransactionUtil.transactional(
                () -> {
                    //从代理商钱包扣款
                    log.info("从代理商钱包扣款，并生成代理商维度扣款流水");
                    //生成广告主充值流水
                    log.info("生成广告主维度充值流水,状态为待充值");
                });

        boolean rechargeFlag = false;
        try {
            log.info("rpc -- 进行广告主钱包充值");
            accountWalletRPCService.recharge(new Object());
            //充值成功
            rechargeFlag = true;
        } catch (Exception e) {
            log.info("rpc -- 查询是否真的充值失败", e);
            boolean status = accountWalletRPCService.queryRechargeStatus(new Object());
            //充值成功
            if (status) {
                rechargeFlag = true;
            } else {
                log.info("异步重试，若仍失败，修改广告主维度充值流水状态为待重试");
            }
        }

        if (rechargeFlag) {
            log.info("修改广告主维度充值流水状态为充值成功");
            return "充值成功";
        }
        return "充值中";
    }


    public String transferFromAgent2Account2(Object transferRequest) {

        //参数为一个Callable
        return TransactionUtil.transactional2(
                () -> {
                    //从代理商钱包扣款
                    log.info("从代理商钱包扣款，并生成代理商维度扣款流水");
                    //生成广告主充值流水
                    log.info("生成广告主维度充值流水,状态为待充值");
                    return "ok";
                });

    }
}