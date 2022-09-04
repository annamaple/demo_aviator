package com.example;

import com.googlecode.aviator.AviatorEvaluator;
import com.googlecode.aviator.Expression;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;


/**
 * 在av脚本中使用
 */
@Slf4j
public class SimpleAvScriptTest {

    /**
     * 基本使用
     */
    @SneakyThrows
    @Test
    public void test1() {
        Expression expression = AviatorEvaluator.getInstance().compileScript("av/test1.av");
        expression.execute();
        log.info("execute success");
    }

    /**
     * 接受av脚本返回值
     */
    @SneakyThrows
    @Test
    public void test2() {
        Expression expression = AviatorEvaluator.getInstance().compileScript("av/test2.av");
        log.info("result: {}", expression.execute());
        log.info("execute success");
    }
}
