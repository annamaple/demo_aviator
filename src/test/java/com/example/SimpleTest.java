package com.example;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.NumberUtil;
import com.googlecode.aviator.AviatorEvaluator;
import lombok.extern.slf4j.Slf4j;
import org.junit.Ignore;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.BigInteger;
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


    /**
     * 基本使用
     */
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


    /**
     * 整数计算默认返回类型
     */
    @Test
    public void test2() {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("x", 12);
        paramMap.put("y", 10);
        String expression = "x + y";
        Assert.isTrue(AviatorEvaluator.compile(expression).execute(paramMap).getClass() == Long.class,
            "aviator整数计算默认不是返回long");
        paramMap.put("x", BigInteger.valueOf(12));
        Assert.isTrue(AviatorEvaluator.compile(expression).execute(paramMap).getClass() == BigInteger.class,
            "aviator整数计算有BigInteger时没回BigInteger");
        expression = "x + y + 10N";
        paramMap.put("x", 12);
        Assert.isTrue(AviatorEvaluator.compile(expression).execute(paramMap).getClass() == BigInteger.class,
            "aviator整数计算表达式中有以N结尾的整数时时没回BigInteger");
        log.info("test success");
    }


    /**
     * 浮点数计算默认返回类型
     */
    @Test
    public void test3() {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("x", 12);
        paramMap.put("y", 10);
        // 表达式中有浮点数
        String expression = "x + y + 10.1";
        Assert.isTrue(AviatorEvaluator.compile(expression).execute(paramMap).getClass() == Double.class,
            "aviator浮点数计算默认不是返回double");
        // 参数时浮点数
        expression = "x + y";
        paramMap.put("x", 11.1F);
        Assert.isTrue(AviatorEvaluator.compile(expression).execute(paramMap).getClass() == Double.class,
            "aviator浮点数计算默认不是返回double");
        paramMap.put("x", 11.1D);
        Assert.isTrue(AviatorEvaluator.compile(expression).execute(paramMap).getClass() == Double.class,
            "aviator浮点数计算默认不是返回double");
        log.info("test success");
    }


    /**
     * BigDecimal计算
     */
    @Test
    public void test4() {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("x", 12);
        paramMap.put("y", 10);
        // 表达式中有以M结尾的数字浮点数
        String expression = "x + y + 10M";
        Assert.isTrue(AviatorEvaluator.compile(expression).execute(paramMap).getClass() == BigDecimal.class,
            "aviator精确数计不是返回BigDecimal");
        // 参数有BigDecimal
        expression = "x + y";
        paramMap.put("x", BigDecimal.valueOf(11));
        Assert.isTrue(AviatorEvaluator.compile(expression).execute(paramMap).getClass() == BigDecimal.class,
            "aviator精确数计不是返回BigDecimal");
        log.info("test success");
    }
}
