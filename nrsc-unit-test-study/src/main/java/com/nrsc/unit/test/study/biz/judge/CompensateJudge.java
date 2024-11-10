package com.nrsc.unit.test.study.biz.judge;

import com.nrsc.unit.test.study.dto.CompensateJudgeDetail;

/**
 * @author sunchuan <sunchuan@kuaishou.com>
 * Created on 2024-11-10
 */
public interface CompensateJudge {
    CompensateJudgeDetail judge(Object object);
}
