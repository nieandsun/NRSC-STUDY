package com.nrsc.unit.test.study.biz;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.nrsc.unit.test.study.biz.judge.CompensateJudge;
import com.nrsc.unit.test.study.dto.CompensateJudgeDetail;

import lombok.extern.slf4j.Slf4j;

/**
 * @author sunchuan <sunchuan@kuaishou.com>
 * Created on 2024-11-10
 */
@Slf4j
@Service
public class AccountCompensateBizService {


    @Autowired
    private List<CompensateJudge> compensateJudgeList;

    public Object calculateCompensateAmount(Object object) {
        //判断是否满足赔付条件，
        //如果有不满足的赔付条件，拿到不满足的具体详情
        List<CompensateJudgeDetail> failureDetails =
                compensateJudgeList.stream()
                        //判断是否要赔付
                        .map(j -> j.judge(object))
                        //过滤出不满足赔付的详情
                        .filter(r -> !r.isLegal())
                        .collect(Collectors.toList());

        if (!CollectionUtils.isEmpty(failureDetails)) {
            log.info("该广告主本周期内无需赔付,并记录无需赔付的具体原因");
            return "无需赔付";
        }

        log.info("该广告主本周期内需要赔付，并计算赔付金额;");
        return "需赔付，已计算赔付金额";
    }
}
