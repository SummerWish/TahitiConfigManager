# TahitiConfigManager

[![Build Status](https://travis-ci.org/SummerWish/TahitiConfigManager.svg?branch=master)](https://travis-ci.org/SummerWish/TahitiConfigManager)
[![Coverage Status](https://coveralls.io/repos/github/SummerWish/TahitiConfigManager/badge.svg?branch=master)](https://coveralls.io/github/SummerWish/TahitiConfigManager?branch=master)
[![Dependency Status](https://www.versioneye.com/user/projects/57091970fcd19a00415b1011/badge.svg)](https://www.versioneye.com/user/projects/57091970fcd19a00415b1011)

一个简单的配置管理框架。

- 支持动态加载配置

- 支持从多个位置加载配置

- 支持序列化到 Java Bean

- 支持多种配置格式，如 YAML, JSON, ini

## 下载

### Maven

您可以使用 Maven 下载这个库到您的项目中。请在 pom.xml 中添加我们的 repository 和这个项目：

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">

    <!-- 此处可以有其他内容，已省略 -->
    
    <repositories>
        <repository>
            <id>tahiti</id>
            <url>http://10.60.40.241:8888/repository/internal/</url>
            <releases>
                <enabled>false</enabled>
            </releases>
            <snapshots>
                <enabled>true</enabled>
            </snapshots>
        </repository>
    </repositories>

    <dependencies>
        <!-- 此处可以有其他内容，已省略 -->
        <dependency>
            <groupId>octoteam.tahiti</groupId>
            <artifactId>tahiti-config</artifactId>
            <version>1.0-SNAPSHOT</version>
        </dependency>
    </dependencies>

</project>
```

另外，视您配置文件的格式，您还需要分别添加各个序列化库的依赖。

对于 JSON 格式的配置文件：

```xml
<dependency>
    <groupId>com.alibaba</groupId>
    <artifactId>fastjson</artifactId>
    <version>1.2.8</version>
</dependency>
```

对于 YAML 格式的配置文件：

```xml
<dependency>
    <groupId>org.yaml</groupId>
    <artifactId>snakeyaml</artifactId>
    <version>1.17</version>
</dependency>
```

对于 ini 格式的配置文件：

```xml
<dependency>
    <groupId>org.ini4j</groupId>
    <artifactId>ini4j</artifactId>
    <version>0.5.4</version>
</dependency>
```

### 手工下载

- [tahiti-config](http://10.60.40.241:8888/repository/snapshots/octoteam/tahiti/tahiti-config/1.0-SNAPSHOT/tahiti-config-1.0-20160409.155109-1.jar)

除了这个库本身以外，视您配置文件的格式，您还需要分别添加各个序列化库的依赖：

- 对于 JSON 格式的配置文件：[fastjson](http://central.maven.org/maven2/com/alibaba/fastjson/1.2.8/fastjson-1.2.8.jar)

- 对于 YAML 格式的配置文件：[snakeyaml](http://central.maven.org/maven2/org/yaml/snakeyaml/1.17/snakeyaml-1.17.jar)

- 对于 ini 格式的配置文件：[ini4j](http://central.maven.org/maven2/org/ini4j/ini4j/0.5.4/ini4j-0.5.4.jar)

## 示例

### 从多个位置依次尝试加载 JSON 格式配置文件

TODO

### 加载 YAML 格式配置文件

TODO
