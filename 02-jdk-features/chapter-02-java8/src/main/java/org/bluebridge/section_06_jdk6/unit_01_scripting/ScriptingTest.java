package org.bluebridge.section_06_jdk6.unit_01_scripting;

import org.junit.Test;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.script.Bindings;
import javax.script.SimpleBindings;

/**
 * JDK 6 Scripting API（JSR 223）测试
 *
 * JDK 6 引入了 javax.script 包，提供统一的脚本语言执行框架。
 * 通过 ScriptEngineManager 可以获取各种脚本语言的引擎（如 JavaScript、Groovy 等），
 * 在 Java 应用程序中动态执行脚本代码，并支持在 Java 和脚本语言之间传递数据。
 *
 * 注意：JDK 8 内置 Nashorn JavaScript 引擎，但 JDK 15+ 已移除。
 * 如果环境中没有可用的脚本引擎，会抛出异常，本测试用 try-catch 处理这种情况。
 *
 * @author lingwh
 * @date 2026/08/05 19:06
 */
public class ScriptingTest {

    /**
     * 测试获取 ScriptEngineManager 和 JavaScript 引擎
     */
    @Test
    public void testGetScriptEngine() {
        // 创建 ScriptEngineManager 实例
        ScriptEngineManager manager = new ScriptEngineManager();
        // 获取所有可用的脚本引擎名称
        System.out.println("可用的脚本引擎: ");
        for (String name : manager.getEngineFactories().stream()
                .map(factory -> factory.getEngineName() + " (" + factory.getLanguageName() + ")")
                .toArray(String[]::new)) {
            System.out.println("  " + name);
        }
        System.out.println("--------------------------------------");
        // 尝试获取 JavaScript 引擎（按名称获取）
        ScriptEngine engineByName = manager.getEngineByName("nashorn");
        if (engineByName == null) {
            engineByName = manager.getEngineByName("JavaScript");
        }
        if (engineByName == null) {
            engineByName = manager.getEngineByName("js");
        }
        if (engineByName != null) {
            System.out.println("成功获取脚本引擎: " + engineByName.getFactory().getEngineName());
        } else {
            System.out.println("当前环境中没有可用的 JavaScript 脚本引擎（JDK 15+ 已移除 Nashorn）");
        }
        // 按扩展名获取引擎
        ScriptEngine engineByExt = manager.getEngineByExtension("js");
        System.out.println("按扩展名 'js' 获取引擎: " + (engineByExt != null ? engineByExt.getFactory().getEngineName() : "不可用"));
        // 按 MIME 类型获取引擎
        ScriptEngine engineByMime = manager.getEngineByMimeType("text/javascript");
        System.out.println("按 MIME 类型 'text/javascript' 获取引擎: " + (engineByMime != null ? engineByMime.getFactory().getEngineName() : "不可用"));
    }

    /**
     * 测试使用 ScriptEngine 执行简单的脚本代码
     */
    @Test
    public void testExecuteScript() {
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("nashorn");
        if (engine == null) {
            engine = manager.getEngineByName("JavaScript");
        }
        if (engine == null) {
            System.out.println("跳过测试：当前环境没有可用的 JavaScript 脚本引擎");
            return;
        }
        try {
            // 执行简单的 JavaScript 表达式
            Object result1 = engine.eval("1 + 2 * 3");
            System.out.println("脚本 '1 + 2 * 3' 执行结果: " + result1);
            // 执行 JavaScript 代码块
            Object result2 = engine.eval("var x = 10; var y = 20; x + y;");
            System.out.println("脚本 'var x = 10; var y = 20; x + y' 执行结果: " + result2);
            // 执行字符串操作
            Object result3 = engine.eval("'Hello, ' + 'World!'.toUpperCase()");
            System.out.println("字符串操作结果: " + result3);
            // 执行 JavaScript 函数
            Object result4 = engine.eval("function add(a, b) { return a + b; } add(5, 7);");
            System.out.println("JavaScript 函数执行结果: " + result4);
        } catch (ScriptException e) {
            System.out.println("执行脚本时发生异常: " + e.getMessage());
        }
    }

    /**
     * 测试从 Java 向脚本引擎传递变量（通过 engine.put）
     */
    @Test
    public void testPassVariableToScript() {
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("nashorn");
        if (engine == null) {
            engine = manager.getEngineByName("JavaScript");
        }
        if (engine == null) {
            System.out.println("跳过测试：当前环境没有可用的 JavaScript 脚本引擎");
            return;
        }
        try {
            // 使用 engine.put 将 Java 变量传递到脚本引擎的全局作用域
            engine.put("name", "张三");
            engine.put("age", 25);
            engine.put("score", 95.5);
            // 在脚本中使用这些变量
            Object result = engine.eval("'姓名: ' + name + ', 年龄: ' + age + ', 成绩: ' + score");
            System.out.println("从 Java 传递变量到脚本引擎: " + result);
            // 传递 Java 对象并在脚本中调用其方法
            engine.put("javaObj", new StringBuilder("Hello from Java"));
            Object sbResult = engine.eval("javaObj.append(' - modified by script'); javaObj.toString()");
            System.out.println("脚本修改 Java 对象: " + sbResult);
        } catch (ScriptException e) {
            System.out.println("传递变量时发生异常: " + e.getMessage());
        }
    }

    /**
     * 测试使用 Bindings 传递变量，并从脚本引擎获取返回值
     */
    @Test
    public void testBindingsAndReturnValue() {
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("nashorn");
        if (engine == null) {
            engine = manager.getEngineByName("JavaScript");
        }
        if (engine == null) {
            System.out.println("跳过测试：当前环境没有可用的 JavaScript 脚本引擎");
            return;
        }
        try {
            // 使用 Bindings 创建独立的作用域，不影响全局作用域
            Bindings bindings = new SimpleBindings();
            bindings.put("a", 30);
            bindings.put("b", 12);
            // 执行脚本并传入 bindings，同时获取返回值
            Object sum = engine.eval("a + b;", bindings);
            System.out.println("通过 Bindings 传递 a=30, b=12, 脚本计算结果: " + sum);
            // 更复杂的计算，脚本返回对象
            bindings.put("radius", 5.0);
            Object area = engine.eval("var pi = 3.14159; pi * radius * radius;", bindings);
            System.out.println("通过 Bindings 计算圆的面积（半径=5.0）: " + area);
            // 脚本返回字符串
            bindings.put("firstName", "John");
            bindings.put("lastName", "Doe");
            Object fullName = engine.eval("firstName + ' ' + lastName;", bindings);
            System.out.println("脚本拼接字符串结果: " + fullName);
            // 脚本返回布尔值
            bindings.put("x", 100);
            bindings.put("y", 200);
            Object isGreater = engine.eval("x > y;", bindings);
            System.out.println("脚本比较 x > y 的结果: " + isGreater);
            // 注意：eval 返回的是脚本中最后一个表达式的值
            Object multiResult = engine.eval("var result = []; for (var i = 0; i < 5; i++) { result.push(i * i); } result;", bindings);
            System.out.println("脚本返回数组结果: " + multiResult);
            System.out.println("Java 中获取到的结果类型: " + multiResult.getClass().getName());
        } catch (ScriptException e) {
            System.out.println("执行 Bindings 脚本时发生异常: " + e.getMessage());
        }
    }

    /**
     * 测试 ScriptEngine 的作用域（Scope）概念
     */
    @Test
    public void testScriptEngineScope() {
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("nashorn");
        if (engine == null) {
            engine = manager.getEngineByName("JavaScript");
        }
        if (engine == null) {
            System.out.println("跳过测试：当前环境没有可用的 JavaScript 脚本引擎");
            return;
        }
        try {
            // 引擎作用域（Engine Scope）：引擎级别的全局变量，所有脚本执行都能访问
            engine.put("globalVar", "我是全局变量");
            // 执行脚本，验证可以访问引擎作用域的变量
            Object result1 = engine.eval("globalVar");
            System.out.println("访问引擎作用域变量: " + result1);
            // 在脚本中修改引擎作用域的变量
            engine.eval("globalVar = '被脚本修改了'");
            System.out.println("脚本修改后引擎作用域变量: " + engine.get("globalVar"));
            // 创建新的 Bindings 作为新的作用域
            Bindings newScope = engine.createBindings();
            newScope.put("globalVar", "我是局部作用域的变量");
            // 传入新作用域执行脚本，不会影响引擎作用域
            Object result2 = engine.eval("globalVar", newScope);
            System.out.println("局部作用域中的变量: " + result2);
            // 验证引擎作用域未被修改
            System.out.println("引擎作用域变量未受影响: " + engine.get("globalVar"));
            // eval 方法不传 Bindings 时，使用引擎默认的上下文
            engine.eval("var localVar = '局部变量';");
            // 脚本中声明的变量默认在引擎作用域中
            Object localResult = engine.eval("localVar");
            System.out.println("脚本声明的变量在引擎作用域: " + localResult);
        } catch (ScriptException e) {
            System.out.println("作用域测试发生异常: " + e.getMessage());
        }
    }
}