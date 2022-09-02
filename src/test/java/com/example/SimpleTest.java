package com.example;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.NumberUtil;
import com.googlecode.aviator.AviatorEvaluator;
import lombok.extern.slf4j.Slf4j;
import org.junit.Ignore;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;


/**
 * Aviator简单使用
 *
 * @author xionglei
 */
@Ignore
@Slf4j
public class SimpleTest {

    @Test
    public void test1() {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("x", 12);
        paramMap.put("y", 10);
        String expression = "x + y";
        Long value = (Long) AviatorEvaluator.compile(expression).execute(paramMap);
        Assert.isTrue(NumberUtil.equals(value, 22L));
        log.info("计算成功： {} = {}", expression, value);
    }
}
