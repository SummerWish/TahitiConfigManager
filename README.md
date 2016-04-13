# TahitiConfigManager

[![Build Status](https://travis-ci.org/SummerWish/TahitiConfigManager.svg?branch=master)](https://travis-ci.org/SummerWish/TahitiConfigManager)
[![Coverage Status](https://coveralls.io/repos/github/SummerWish/TahitiConfigManager/badge.svg?branch=master)](https://coveralls.io/github/SummerWish/TahitiConfigManager?branch=master)
[![Dependency Status](https://www.versioneye.com/user/projects/57091970fcd19a00415b1011/badge.svg)](https://www.versioneye.com/user/projects/57091970fcd19a00415b1011)

[Documentation](http://summerwish.github.io/TahitiConfigManager/)

一个简单的配置管理框架。

- 支持动态加载配置

- 支持从多个位置加载配置

- 支持序列化到 Java Bean

- 支持多种配置格式，如 YAML, JSON

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
            <id>tahiti-nexus-snapshots</id>
            <name>Tahiti NEXUS</name>
            <url>http://sse.tongji.edu.cn/tahiti/nexus/content/groups/public</url>
            <releases><enabled>false</enabled></releases>
            <snapshots><enabled>true</enabled></snapshots>
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

### 手工下载

- [tahiti-config](http://sse.tongji.edu.cn/tahiti/nexus/service/local/repositories/public/content/octoteam/tahiti/tahiti-config/1.0-SNAPSHOT/tahiti-config-1.0-20160413.122350-3.jar)

除了这个库本身以外，视您配置文件的格式，您还需要分别添加各个序列化库的依赖：

- 对于 JSON 格式的配置文件：[fastjson](http://central.maven.org/maven2/com/alibaba/fastjson/1.2.8/fastjson-1.2.8.jar)

- 对于 YAML 格式的配置文件：[snakeyaml](http://central.maven.org/maven2/org/yaml/snakeyaml/1.17/snakeyaml-1.17.jar)

## 示例

### 从多个位置依次尝试加载 JSON 格式配置文件

对于一个发行的应用程序来说，一般有一个程序自带的默认配置文件，和一个可选的用户覆盖配置文件。TahitiConfigManager 可以很轻松地处理这种情况。

该示例演示从两个位置加载配置文件：

- 当前目录下 config.json（用户配置文件）
- `resources` 目录下 config.json（应用程序自带配置文件）

当用户配置文件存在时，将从用户配置文件加载；当它不存在时，才会从应用程序配置文件加载。

配置文件样例 `resources/config.json`:

```json
{
    "host": "127.0.0.1",
    "port": 3399
}
```

首先定义配置文件对应的 Java Bean，这里取名为 `ConfigBean`。

`ConfigBean.java`:

```java
public class ConfigBean {

    public String host;
    
    public int port;
    
    public String getHost() {
        return host;
    }
    
    public void setHost(String host) {
        this.host = host;
    }
    
    public int getPort() {
        return port;
    }
    
    public void setPort(int port) {
        this.port = port;
    }

}
```

> 提示：在 Intellij IDEA 下可以利用 generator 自动生成 getter 和 setter。

接下来，使用 `JsonAdapter` 和 `ConfigManager` 加载配置：

```java
ConfigManager configManager = new ConfigManager(
    new JsonAdapter(),  // 指定配置文件加载器，这里是 JSON 格式因此使用 JsonAdapter
    "./config.json",   // 用户配置文件路径
    Paths.get(this.getClass().getResource("/config.json").toURI()).toString()  // 默认配置文件路径
);

ConfigBean config = configManager.loadToBean(ConfigBean.class);

// 接下来使用面向对象的方式直接访问配置

config.getHost();  // 127.0.0.1
config.getPort();  // 3399
```

当然，您也可以只传一个配置文件路径，或者传 n 个配置文件路径，`ConfigManager` 将总是从第一个路径开始尝试加载配置文件，直到某个加载成功后停止。若从所有路径都无法加载配置，将抛出 `IOException`。

### 加载 YAML 格式配置文件

YAML 是另一种常见的配置文件格式，由于书写比 JSON 方便简洁因而被大量使用。

`./config.yaml`:

```yaml
server: 127.0.0.1
port: 3399
```

加载配置：

```java
ConfigManager configManager = new ConfigManager(
    new YamlAdapter(),
    "./config.json"
);

ConfigBean config = configManager.loadToBean(ConfigBean.class);

config.getHost();  // 127.0.0.1
config.getPort();  // 3399
```

### 写入配置

TahitiConfigManager 支持写入配置，调用 `writeToFirstCandidate` 时将从给定的第一个路径开始依次尝试写入配置，直到写入成功。若没有一个路径可以成功写入配置，则抛出 `IOException`。

```java
configManager.writeToFirstCandidate(config);
```