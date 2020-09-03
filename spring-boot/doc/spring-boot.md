## spring boot

### spring boot 入门
#### 1、简介
        简化spring应用开发的一个框架
        整个spring技术栈的一个大整合
        J2EE开发的一站式解决方案
#### 2、特征
        (1) 可以创建独立的Spring应用程序，并且基于其Maven或Gradle插件，可以创建可执行的JARs和WARs
        (2) 内嵌Tomcat或Jetty等Servlet容器
        (3) 提供自动配置的“starter”项目对象模型（POMS）以简化Maven配置
        (4) 尽可能自动配置Spring容器
        (5) 提供准备好的特性，如指标、健康检查和外部化配置
        (6) 绝对没有代码生成，不需要XML配置
#### 3、环境约束
        -jdk1.8: spring boot 1.7及以上
        -maven3.x: maven3.3以上版本
        -idea
#### 4、maven设置
       maven的 setting.xml添加
```xml
    <profile>      
        <id>jdk-1.8</id>      
        <activation>      
            <activeByDefault>true</activeByDefault>      
            <jdk>1.8</jdk>      
        </activation>      
        <properties>      
            <maven.compiler.source>1.8</maven.compiler.source>      
            <maven.compiler.target>1.8</maven.compiler.target>      
            <maven.compiler.compilerVersion>1.8</maven.compiler.compilerVersion>      
        </properties>      
    </profile>
```
#### 5、spring-boot hello world
    (1) 创建maven工程
    (2) 导入springboot依赖
```xml
    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>2.3.2.RELEASE</version>
        <relativePath/> <!-- lookup parent from repository -->
    </parent>
    
    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
    
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
            <exclusions>
                <exclusion>
                    <groupId>org.junit.vintage</groupId>
                    <artifactId>junit-vintage-engine</artifactId>
                </exclusion>
            </exclusions>
        </dependency>
    </dependencies>
```
    (3)创建spring boot启动类
```java
    @SpringBootApplication
    public class Run {
        public static void main(String[] args) {
            SpringApplication.run(Run.class);
        }
    }
```
    (4)创建Controller类
```java
    @Controller
    public class HelloController {
    
        @ResponseBody
        @RequestMapping("sayHello")
        public String sayHello() {
            return "Hello world!";
        }
    
    }
```        
    浏览器返回:http://localhost:8080/sayHello,返回Hello world!
    (5)简化部署
```xml
    <!-- 这个插件可以将项目打包成可执行jar包  可以直接使用java -jar的命令执行-->
    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
            </plugin>
        </plugins>
    </build>
```        
    (6)pom.xml文件
```xml
    <!-- spring boot 引入依赖 -->
    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>2.3.2.RELEASE</version>
    </parent>
    
    
    <!--
        spring-boot-starter-parent 的依赖 spring-boot-dependencies 
        spring-boot-dependencies 里面包含各种jar的版本, 引入依赖是默认是dependencies里面的jar包版本 
        如果要引入的jar包, 不在dependencies里,需要自己设置jar包版本
    -->
    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-dependencies</artifactId>
        <version>2.3.2.RELEASE</version>
    </parent>
    
    <!-- starter-web 包含web项目所需依赖,web场景启动器   spring-boot-starter为场景启动器,所有starter包都依赖该包 -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
```
    (7) spring boot 主程序类,入口
        **@SpringBootApplication**: spring boot应用标注在某个类上表明该类是主配置类, spring boot就是运行该类的main方法启动spring boot应用
```java
    @SpringBootConfiguration
    @EnableAutoConfiguration
    @ComponentScan(
        excludeFilters = {@Filter(
        type = FilterType.CUSTOM,
        classes = {TypeExcludeFilter.class}
    ), @Filter(
        type = FilterType.CUSTOM,
        classes = {AutoConfigurationExcludeFilter.class}
    )}
    )
    public @interface SpringBootApplication {}    
``` 
        **@SpringBootConfiguration**:spring boot的配置类;
            标注在某个类上,表示这是spring boot的配置类
            @Configuration: spring 配置类---配置文件; 也是容器中的一个组件@Component
            
        **@EnableAutoConfiguration**:开启自动配置功能
```java
    @AutoConfigurationPackage
    @Import({AutoConfigurationImportSelector.class})
    public @interface EnableAutoConfiguration {}
```
        @AutoConfigurationPackage: 自动配置包
            @Import({Registrar.class}): spring 容器注解@Import给容器导入一个组件;导入的组件AutoConfigurationPackages.Registrar

        `将主配置类(@SpringBootApplication标注的类)的所在包及下面所有子包的组件扫描到spring容器中`
        AutoConfigurationImportSelector:导入哪些组件的选择器;
            将所有需要导入的组件以全类名的方式返回;
            这些组件会被添加到容器中
#### 6、使用Spring Initializr 快速创建spring boot项目
    可以联网导入自己需要的模块
            
### spring boot 配置
#### 1、全局配置文件
    spring boot使用一个全局配置文件,配置文件名是固定的
        (1)application.properties
        (2)application.yml
    配置文件的作用:修改spring boot默认的配置值
        
    application.properties
       配置端口号:server.port=8565
       

### spring boot 日志


### spring boot web开发


### spring boot docker集成


### spring boot 数据库访问


### spring boot 启动配置原理


### spring boot 自定义starters


### spring boot 缓存


### spring boot 消息


### spring boot 检索


### spring boot 任务


### spring boot 安全


### spring boot 分布式


### spring boot 开发热部署


### spring boot 监控管理



