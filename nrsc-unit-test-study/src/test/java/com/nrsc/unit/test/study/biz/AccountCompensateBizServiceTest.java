package com.nrsc.unit.test.study.biz;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.stream.Stream;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;

import com.nrsc.unit.test.study.biz.judge.CompensateJudge;
import com.nrsc.unit.test.study.dto.CompensateJudgeDetail;

/**
 * @author sunchuan <sunchuan@kuaishou.com>
 * Created on 2024-11-10
 */
public class AccountCompensateBizServiceTest {

    @Mock
    CompensateJudge compensateJudge;
    @Mock //使用@Mock创建的是一个完全模拟对象，它的任何方法都不会被真正调用，
    // 都需要模拟，本方案就需要对compensateJudgeList的stream()进行模拟
    List<CompensateJudge> compensateJudgeList;
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
        //模拟compensateJudge的judge方法
        when(compensateJudge.judge(any())).thenReturn(detail);
        //模拟compensateJudgeList的stream()方法
        //【注意！！！】代码是用lambda表达式写的，如果用的for循环，这里可就不好mock了
        when(compensateJudgeList.stream()).thenReturn(Stream.of(compensateJudge));
        Object res = accountCompensateBizService.calculateCompensateAmount("object");
        Assert.assertEquals(res.toString(), "需赔付，已计算赔付金额");
    }

    @Test
    public void testCalculateCompensateAmount002() throws Exception {
        CompensateJudgeDetail detail = new CompensateJudgeDetail();
        detail.setLegal(false);
        detail.setReason("XXX");
        //模拟compensateJudge的judge方法
        when(compensateJudge.judge(any())).thenReturn(detail);
        //模拟compensateJudgeList的stream()方法，
        //【注意！！！】代码是用lambda表达式写的，如果用的for循环，这里可就不好mock了
        when(compensateJudgeList.stream()).thenReturn(Stream.of(compensateJudge));
        Object res = accountCompensateBizService.calculateCompensateAmount("object");
        Assert.assertEquals(res.toString(), "无需赔付");
    }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme