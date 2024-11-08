package com.nrsc.unit.test.study.util;

import java.util.concurrent.Callable;

import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

/**
 * @author sunchuan
 * Created on 2024-11-06
 * 编程式事务工具类
 */
@Slf4j
public class TransactionUtil {
    private TransactionUtil() {
    }

    public static void transactional(Runnable runnable) {
        PlatformTransactionManager transactionManager =
                SpringContextUtil.getBeanByType(PlatformTransactionManager.class);
        TransactionStatus status =
                transactionManager.getTransaction(new DefaultTransactionDefinition());
        try {
            runnable.run();
            transactionManager.commit(status);
        } catch (Exception e) {
            transactionManager.rollback(status);
            log.error("编程式事务业务异常回滚", e);
            throw e;
        }
    }


    @SneakyThrows
    public static <T> T transactional2(Callable<T> callable)  {
        PlatformTransactionManager transactionManager =
                SpringContextUtil.getBeanByType(PlatformTransactionManager.class);
        TransactionStatus status =
                transactionManager.getTransaction(new DefaultTransactionDefinition());
        try {
            T call = callable.call();
            transactionManager.commit(status);
            return call;
        } catch (Exception e) {
            transactionManager.rollback(status);
            log.error("编程式事务业务异常回滚", e);
            throw e;
        }
    }

}
