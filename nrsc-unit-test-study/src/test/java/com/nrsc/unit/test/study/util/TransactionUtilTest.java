package com.nrsc.unit.test.study.util;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.transaction.PlatformTransactionManager;

/**
 * @author sunchuan <sunchuan@kuaishou.com>
 * Created on 2024-11-06
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({SpringContextUtil.class})
public class TransactionUtilTest {

    @Test
    public void testTransactional_001() throws Exception {
        PlatformTransactionManager mock = mock(PlatformTransactionManager.class);
        PowerMockito.mockStatic(SpringContextUtil.class);
        when(SpringContextUtil.getBeanByType(eq(PlatformTransactionManager.class))).thenReturn(mock);
        TransactionUtil.transactional(() -> System.out.println("test"));
    }

    @Test
    public void testTransactional_002() throws Exception {
        PowerMockito.mockStatic(SpringContextUtil.class);
        PlatformTransactionManager mock = mock(PlatformTransactionManager.class);
        when(SpringContextUtil.getBeanByType(eq(PlatformTransactionManager.class))).thenReturn(mock);
        assertThatThrownBy(
                () -> TransactionUtil.transactional(
                        () -> {
                            throw new RuntimeException("1122");
                        })
        ).isInstanceOf(RuntimeException.class).hasMessage("1122");
    }


    @Test
    public void testTransactional2_001() throws Exception {
        PlatformTransactionManager mock = mock(PlatformTransactionManager.class);
        PowerMockito.mockStatic(SpringContextUtil.class);
        when(SpringContextUtil.getBeanByType(eq(PlatformTransactionManager.class))).thenReturn(mock);
        String result = TransactionUtil.transactional2(() -> "1122");
        Assert.assertEquals(result, "1122");
    }

    @Test
    public void testTransactional2_002() throws Exception {
        PowerMockito.mockStatic(SpringContextUtil.class);
        PlatformTransactionManager mock = mock(PlatformTransactionManager.class);
        when(SpringContextUtil.getBeanByType(eq(PlatformTransactionManager.class))).thenReturn(mock);
        assertThatThrownBy(
                () -> TransactionUtil.transactional2(
                        () -> {
                            throw new RuntimeException("1122");
                        })
        ).isInstanceOf(RuntimeException.class).hasMessage("1122");
    }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme