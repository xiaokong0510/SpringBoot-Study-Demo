## SpringBoot 配置文件
SpringBoot使用两种全局的配置文件，全局配置文件可以对一些默认配置进行修改。

- application.properties

- application.yml

  

定义好配置属性对象后，有以下两种方式进行配置注入


### 方式一：@Value 注入属性

- Spring 支持 `@value` 注解的方式来读取配置文件中的配置值；

- 添加 `@Component` 注解将配置类注入 Spring 容器中。

- 支持 List、Map 等绑定。

```java
@Data
@Component
public class DeveloperProperty {
    /**
     * 通过配置文件注入
     */
    @Value("${developer.name}")
    private String name;
    @Value("${developer.phone-number}")
    private String phoneNumber;
}
```

### 方式二：@ConfigurationProperties

步骤：

1. 定义配置类；
2. 使用 `@ConfigurationProperties` 注解也可以获取配置文件的值：

- prefix 前缀定义了哪些外部属性将绑定到类的字段上；
- 根据 Spring Boot 的宽松绑定规则，类的属性名称必须与外部属性的名称匹配；
- 可以简单地用一个值初始化一个字段来定义一个默认值； 
- 类的字段必须有公共 setter 方法

```java
@Data
@ConfigurationProperties(prefix = "user")
public class UserInfoProperty {

    private String username;
    private Integer age;
    private List<Object> lists;
    private Map<String, Object> maps;
}
```

3. 使用 `@EnableConfigurationProperties({UserInfoProperty.class})`启用该配置



也可以直接在配置类上添加 `@Component` 注解将配置类注入 Spring 容器中使用

