## Java表达式计算工具-Aviator的使用

添加依赖:
```
<!-- https://mvnrepository.com/artifact/com.googlecode.aviator/aviator -->
<dependency>
    <groupId>com.googlecode.aviator</groupId>
    <artifactId>aviator</artifactId>
    <version>5.3.0</version>
</dependency>
```

Aviator 有两种常用的使用方式： 
- 直接在Java代码中使用(表达式运算)
- 在.av脚本文件中使用

### 直接在Java代码中
基本使用: 引入依賴直接在Java代码中传入需要计算的表达式和变量的值
```java
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
```

如上面代码所示：基本使用非常简单。直接使用`AviatorEvaluator`类提供的方法即可， 使用Map<String, Object> 
来传递表达式的参数。需要注意的是，传递参数的Map的Value类型必须是Object， 
且计算返回的值默认是Object类型，需要根据代码上下文转换为所需要的类型。

**返回类型需要注意：**
`Aviator`在计算整数时没有 byte/short/int 等类型，统一都为 long，支持的范围也跟 java 语言一 
样：-9223372036854774808~9223372036854774807。

###  av脚本中使用

AviatorScript语法：https://www.yuque.com/boyan-avfmj/aviatorscript/lvabnw
Aviator在Java中的使用：https://juejin.cn/post/7002606430519853069