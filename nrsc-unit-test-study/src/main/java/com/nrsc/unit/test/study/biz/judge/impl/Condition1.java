package com.nrsc.unit.test.study.biz.judge.impl;

import org.springframework.stereotype.Component;

import com.nrsc.unit.test.study.biz.judge.CompensateJudge;
import com.nrsc.unit.test.study.dto.CompensateJudgeDetail;

/**
 * @author sunchuan <sunchuan@kuaishou.com>
 * Created on 2024-11-10
 */
@Component
public class Condition1 implements CompensateJudge {
    @Override
    public CompensateJudgeDetail judge(Object object) {
        return new CompensateJudgeDetail();
    }
}
