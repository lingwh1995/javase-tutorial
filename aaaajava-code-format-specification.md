# Java 代码格式化规范

> 本规范基于阿里巴巴 Java 开发手册（嵩山版）与 Javadoc 标准，结合项目实际约定整理而成，适用于所有 Java 源码文件的格式化处理。

---

## 一、缩进与空白

### 1.1 缩进方式

- 统一使用 **4 个空格** 作为缩进单位，禁止使用 Tab 字符
- 一个缩进层级 = 4 个空格

### 1.2 类大括号后空行

`class` / `interface` / `enum` 声明的 `{` 之后，必须**空一行**，再开始书写第一个属性、方法或注释。

正确示例：

```java
public class FlyweightFactory {

    private static FlyweightFactory factory = new FlyweightFactory();

    private FlyweightFactory() {}

    public static FlyweightFactory getInstance() {
        return factory;
    }
}
```

错误示例：

```java
public class FlyweightFactory {
    private static FlyweightFactory factory = new FlyweightFactory();
}
```

### 1.3 类成员分隔

- 类内部的成员（属性、方法、构造器、内部类）之间，**必须用一个空行分隔**
- 即使只有一个属性或一个方法，类大括号后与结束大括号前均需空行

### 1.4 花括号风格

采用 K&R 风格（Egyptian style）：
- 左花括号 `{` 不换行，跟在当前行末尾
- 右花括号 `}` 独立成行

---

## 二、类文档注释格式

### 2.1 标准格式

类文档注释（Javadoc）**必须**遵循以下结构：

```java
/**
 * 描述文本（取自原 @desc 值，或根据类名/类功能自动生成）
 *
 * @author 作者名
 * @date 创建时间
 */
public class XxxClass {
```

### 2.2 格式要点

1. **描述置顶**：类的功能描述必须放在 Javadoc 的第一行（`* ` 后），而非使用 `@desc` 标签
2. **空行分隔**：描述文本与 `@author` 之间必须有一个空行（仅 `* ` 的行）
3. **删除 `@desc`**：原 `@desc` 标签必须删除，其值提取到描述位置
4. **删除其他非标准标签**：`@version`、`@since` 等标签必须删除
5. **保留标签**：仅保留 `@author` 和 `@date`

### 2.3 转换示例

转换前：

```java
/**
 * @author lingwh
 * @desc 奥迪汽车
 * @date 2019/3/10 00:00
 */
public class Audi implements Car {
```

转换后：

```java
/**
 * 奥迪汽车
 *
 * @author lingwh
 * @date 2019/3/10 00:00
 */
public class Audi implements Car {
```

### 2.4 无 @desc 或 @desc 为空的处理

- 若原文件无 `@desc` 标签，或 `@desc` 后无值，则根据类名和类的代码内容**自动生成**简短描述
- 描述应为中文，简明扼要，概括类的职责或功能

示例：

```java
/**
 * 简单工厂中创建车的工厂类
 *
 * @author lingwh
 * @date 2026/7/9 14:30
 */
public class CarFactory {
```

### 2.5 接口与枚举

接口、枚举、注解类型的文档注释规则与类一致。

---

## 三、方法注释格式

### 3.1 单行注释转多行

方法的单行 Javadoc 注释（`/** xxx */`）必须转换为多行格式。

转换前：

```java
/** 简单工厂中创建车的方法 */
public static Car createCar(String carName) {
```

转换后：

```java
/**
 * 简单工厂中创建车的方法
 *
 * @param carName
 * @return
 */
public static Car createCar(String carName) {
```

### 3.2 带参数的方法注释

带 `@param`、`@return`、`@throws` 的方法注释格式：

```java
/**
 * 获取key对应的享元对象
 *
 * @param key 获取享元对象的key
 * @return 对应的享元对象
 */
public Flyweight getFlyweight(String key) {
```

### 3.3 无参数的方法注释格式

```java
/**
 * 计算工资，其实应该有很多参数，为了演示从简
 */
public void calcPay() {
```

---

## 四、属性注释格式

### 4.1 单行注释转多行

属性的单行 Javadoc 注释必须转换为多行格式，与方法注释规则一致。

转换前：

```java
/** 英雄昵称 */
private String nickName;
```

转换后：

```java
/**
 * 英雄昵称
 */
private String nickName;
```

### 4.2 多个属性示例

```java
public class Hero {

    /**
     * 英雄昵称
     */
    private String nickName;

    /**
     * 英雄等级
     */
    private Integer level;

    /**
     * 英雄血量
     */
    private Integer hp;
}
```

---

## 五、导包顺序规范

### 5.1 分组规则

`import` 语句按以下顺序分组，**组与组之间用一个空行分隔**：

1. **第一组**：第三方包（非 `java.*` / `javax.*` 的包）
2. **第二组**：JDK 内部包（`java.*` / `javax.*`）

### 5.2 完整示例

```java
package org.bluebridge.xml.dom4j;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;

/**
 * 使用dom4j解析xml
 *
 * @author lingwh
 * @date 2026/4/21 19:02
 */
public class Dom4jParseXml {
```

### 5.3 特殊情况

- **仅有 JDK 包**：无需分组，直接书写即可，导包与类文档注释之间仍需空一行
- **仅有第三方包**：同上，无需空行分组
- **单一类型导入**：不需要分组
- `package` 语句与第一个 `import` 之间**不空行**
- 最后一组 `import` 与类文档注释之间**必须空一行**

仅 JDK 包示例：

```java
package action.iterator.iterator_d;

import java.util.ArrayList;
import java.util.List;

/**
 * 客户方已有的工资管理对象
 *
 * @author lingwh
 * @date 2019/8/20 9:17
 */
public class PayManager extends Aggregate {
```

---

## 六、@author 标签规范

### 6.1 统一作者名

- 所有 `@author` 标签的值统一为 `lingwh`
- 遇到 `@author ronin` 必须替换为 `@author lingwh`

### 6.2 示例

转换前：

```java
 * @author ronin
```

转换后：

```java
 * @author lingwh
```

---

## 七、@date 标签规范

### 7.1 格式

`@date` 标签使用 `yyyy/M/d HH:mm` 格式：

```java
 * @date 2019/3/10 14:30
```

### 7.2 时间为 00:00 的处理

当 `@date` 的时间部分为 `00:00` 时，按以下步骤处理：

1. **查询 Git 文件创建时间**：
   ```bash
   git log --diff-filter=A --format="%ad" --date=format:"%Y/%-m/%-d %H:%M" -- <文件路径>
   ```
2. **使用 Git 时间**：将 `@date` 的值替换为 Git 查询到的文件创建时间
3. **Git 时间仍为 00:00 的兜底处理**：若 Git 查询到的时间仍为 `00:00`，则随机生成一个合法时间值（小时范围 08-22，分钟范围 00-59）

### 7.3 时间非 00:00 的处理

若 `@date` 的时间部分**不是** `00:00`，则保留原值不动。

### 7.4 新建文件

新建文件的 `@date` 使用当前日期，时间部分若为 `00:00` 则随机生成。

---

## 八、完整文件示例

以下是一个符合本规范的完整 Java 文件示例：

```java
package structure.flyweight.flyweight_e;

import java.util.HashMap;
import java.util.Map;

/**
 * 享元工厂
 *
 * @author lingwh
 * @date 2019/8/1 13:53
 */
public class FlyweightFactory {

    private static FlyweightFactory factory = new FlyweightFactory();

    private FlyweightFactory() {}

    public static FlyweightFactory getInstance() {
        return factory;
    }

    /**
     * 缓存多个flyweight对象
     */
    private Map<String, Flyweight> fsMap = new HashMap<String, Flyweight>();

    /**
     * 获取key对应的享元对象
     *
     * @param key 获取享元对象的key
     * @return 对应的享元对象
     */
    public Flyweight getFlyweight(String key) {
        Flyweight flyweight = fsMap.get(key);
        if (flyweight == null) {
            flyweight = new AuthorizationFlyweight(key);
            fsMap.put(key, flyweight);
        }
        return flyweight;
    }
}
```

---

## 九、跳过规则

以下文件**不进行**格式化处理：

1. `module-info.java` 文件
2. 方法内部的方法级注释（方法体内的 `//` 注释和 `/* */` 注释保持原样）
3. 字段级别的非 Javadoc 注释（`//` 格式的行内注释保持原样）

> 注意：仅处理类级别的文档注释，以及方法/属性的 Javadoc 单行注释转多行格式。方法体内的代码注释不做修改。

---

## 十、规范速查表

| 序号 | 规则 | 说明 |
|------|------|------|
| 1 | 缩进 | 4 个空格，禁止 Tab |
| 2 | 类大括号后 | `{` 后空一行 |
| 3 | 类成员分隔 | 成员间空一行 |
| 4 | 类文档注释 | 描述置顶，删除 `@desc`，保留 `@author`/`@date` |
| 5 | 方法单行注释 | `/** xxx */` → 多行格式 |
| 6 | 属性单行注释 | `/** xxx */` → 多行格式 |
| 7 | 导包顺序 | 第三方包 → 空行 → JDK 包 → 空行 → 类注释 |
| 8 | @author | 统一为 `lingwh`，`ronin` 需替换 |
| 9 | @date 时间 | `00:00` 需从 Git 查询，仍为 `00:00` 则随机生成 |
| 10 | 跳过文件 | `module-info.java` 不处理 |
