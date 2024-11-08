package com.nrsc.unit.test.study.biz;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.concurrent.Callable;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.slf4j.Logger;

import com.nrsc.unit.test.study.util.TransactionUtil;

/**
 * @author sunchuan <sunchuan@kuaishou.com>
 * Created on 2024-11-08
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({TransactionUtil.class})
public class AgentWalletBizServiceTest {
    @Mock
    Logger log;
    @Mock
    AccountWalletRPCService accountWalletRPCService;
    @InjectMocks
    AgentWalletBizService agentWalletBizService;

    //@Before
    //public void setUp() throws Exception {
    //    //因为transactional为一个静态方法，所以你可能想着我直接将其mock掉就ok了
    //    PowerMockito.mockStatic(TransactionUtil.class);
    //    PowerMockito.doNothing().when(TransactionUtil.class, "transactional", any());
    //}

    //@Before
    //public void setUp() throws Exception {
    //    //第一个参数为要mock的class
    //    //第二个参数为给要mock的class中的某个方法（该方法并未指定）给定一个默认的答案
    //    PowerMockito.mockStatic(TransactionUtil.class, new Answer() {
    //        @Override
    //        public Object answer(InvocationOnMock invocation) throws Throwable {
    //            //将要mock的方法的参数取出，并将其转为Runnable
    //            Runnable runnable = (Runnable) invocation.getArguments()[0];
    //            //执行该Runnable对应的方法
    //            runnable.run();
    //            //这里直接返回null就OK了
    //            return null;
    //        }
    //    });
    //}

    @Before
    public void setUp() throws Exception {
        //第一个参数为要mock的class
        //第二个参数为给要mock的class中的某个方法（该方法并未指定）给定一个默认的答案
        PowerMockito.mockStatic(TransactionUtil.class, invocation -> {
            //将要mock的方法的参数取出，并将其转为Runnable
            Runnable runnable = (Runnable) invocation.getArguments()[0];
            //执行该Runnable对应的方法
            runnable.run();
            //这里直接返回null就OK了
            return null;
        });
    }


    @Test
    public void testTransferFromAgent2Account_001() throws Exception {
        String result = agentWalletBizService.transferFromAgent2Account("r");
        Assert.assertEquals("充值成功", result);
    }

    @Test
    public void testTransferFromAgent2Account_002() throws Exception {
        when(accountWalletRPCService.recharge(any())).thenThrow(new RuntimeException("E"));
        when(accountWalletRPCService.queryRechargeStatus(any())).thenReturn(true);
        String result = agentWalletBizService.transferFromAgent2Account("r");
        Assert.assertEquals("充值成功", result);
    }

    @Test
    public void testTransferFromAgent2Account_003() throws Exception {
        when(accountWalletRPCService.recharge(any())).thenThrow(new RuntimeException("E"));
        when(accountWalletRPCService.queryRechargeStatus(any())).thenReturn(false);
        String result = agentWalletBizService.transferFromAgent2Account("r");
        Assert.assertEquals("充值中", result);
    }


    @Test
    public void testTransferFromAgent2Account_004() throws Exception {

        //第一个参数为要mock的class
        //第二个参数为给要mock的class中的某个方法（该方法并未指定）给定一个默认的答案
        PowerMockito.mockStatic(TransactionUtil.class, invocation -> {
            //将要mock的方法的参数取出，并将其转为Runnable
            Callable<String> callable = (Callable<String>) invocation.getArguments()[0];

            return callable.call();
        });

        String result = agentWalletBizService.transferFromAgent2Account2("r");
        Assert.assertEquals("ok", result);
    }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme