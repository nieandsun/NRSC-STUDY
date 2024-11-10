package com.nrsc.unit.test.study.biz;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.slf4j.Logger;

import com.nrsc.unit.test.study.biz.judge.CompensateJudge;
import com.nrsc.unit.test.study.dto.CompensateJudgeDetail;

/**
 * @author sunchuan <sunchuan@kuaishou.com>
 * Created on 2024-11-10
 */
public class AccountCompensateBizServiceTest2 {

    @Mock
    CompensateJudge compensateJudge;
    @Spy
    //使用@Spy创建的对象，其方法会执行真实逻辑，
    // 因此也可以调用List对象的add方法为其add元素
    // 但@Mock却不行（任何方法都不会被真正调用，都需要模拟）
    List<CompensateJudge> compensateJudgeList = new ArrayList<>();
    @Mock
    Logger log;
    @InjectMocks
    AccountCompensateBizService accountCompensateBizService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

    }

    @Test
    public void testCalculateCompensateAmount001() throws Exception {
        CompensateJudgeDetail detail = new CompensateJudgeDetail();
        detail.setLegal(true);
        detail.setReason(null);
        when(compensateJudge.judge(any())).thenReturn(detail);
        //为compensateJudgeList添加一个mock的对象
        compensateJudgeList.add(compensateJudge);
        Object res = accountCompensateBizService.calculateCompensateAmount("object");
        Assert.assertEquals(res.toString(), "需赔付，已计算赔付金额");
    }

    @Test
    public void testCalculateCompensateAmount() throws Exception {
        CompensateJudgeDetail detail = new CompensateJudgeDetail();
        detail.setLegal(false);
        detail.setReason("XXX");
        when(compensateJudge.judge(any())).thenReturn(detail);
        //为compensateJudgeList添加一个mock的对象
        compensateJudgeList.add(compensateJudge);
        Object res = accountCompensateBizService.calculateCompensateAmount("object");
        Assert.assertEquals(res.toString(), "无需赔付");
    }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme