以下是一个适用于你的项目的 [README.md](file://C:\programme\aProjects\digital_farm\DigitalFarmBackend\README.md) 文档示例：

# DigitalFarmBackend

DigitalFarmBackend 是一个用于农业基地管理的后端服务项目，基于 Spring Boot 和 MyBatis-Plus 实现，提供基础的农业基地信息与监测点数据管理功能。

## 技术栈

- **Java 17**
- **Spring Boot**
- **MyBatis-Plus**
- **MySQL**

## 模块说明

| 模块 | 描述 |
|------|------|
| `controller` | 提供 RESTful API 接口，例如 `/base` 接口可查询农业基地列表。 |
| `service` | 包含业务逻辑，如 [FarmBaseService](file://C:\programme\aProjects\digital_farm\DigitalFarmBackend\src\main\java\com\whitebear\digitalfarmbackend\service\FarmBaseService.java#L5-L7) 接口和其实现类 [FarmBaseServiceImpl](file://C:\programme\aProjects\digital_farm\DigitalFarmBackend\src\main\java\com\whitebear\digitalfarmbackend\service\impl\FarmBaseServiceImpl.java#L8-L11)。 |
| `mapper` | 数据访问层接口，使用 MyBatis-Plus 提供基本的 CRUD 操作。 |
| `model.entity` | 数据模型定义，包括 [FarmBase](file://C:\programme\aProjects\digital_farm\DigitalFarmBackend\src\main\java\com\whitebear\digitalfarmbackend\model\entity\FarmBase.java#L11-L39) 和 [MonitoringPoint](file://C:\programme\aProjects\digital_farm\DigitalFarmBackend\src\main\java\com\whitebear\digitalfarmbackend\model\entity\MonitoringPoint.java#L11-L39) 等实体类。 |
| `config` | 配置类，包含系统配置、数据库连接等。 |

## 核心功能

### FarmBaseController
```java
@RestController
public class FarmBaseController {
    @Autowired
    private FarmBaseService farmBaseService;

    @GetMapping("/base")
    public List<FarmBase> query(){
        return farmBaseService.getBaseMapper().selectList(null);
    }
}
```

- **GET /base**：获取所有农业基地的信息列表。

### FarmBase
- **字段描述**：
    - [baseId](file://C:\programme\aProjects\digital_farm\DigitalFarmBackend\src\main\java\com\whitebear\digitalfarmbackend\model\entity\FarmBase.java#L14-L15): 基地ID（主键）
    - [baseName](file://C:\programme\aProjects\digital_farm\DigitalFarmBackend\src\main\java\com\whitebear\digitalfarmbackend\model\entity\FarmBase.java#L17-L17): 基地名称
    - [location](file://C:\programme\aProjects\digital_farm\DigitalFarmBackend\src\main\java\com\whitebear\digitalfarmbackend\model\entity\FarmBase.java#L19-L19): 地理位置
    - [area](file://C:\programme\aProjects\digital_farm\DigitalFarmBackend\src\main\java\com\whitebear\digitalfarmbackend\model\entity\FarmBase.java#L21-L21): 占地面积
    - [createTime](file://C:\programme\aProjects\digital_farm\DigitalFarmBackend\src\main\java\com\whitebear\digitalfarmbackend\model\entity\FarmBase.java#L23-L23): 创建时间
    - [updateTime](file://C:\programme\aProjects\digital_farm\DigitalFarmBackend\src\main\java\com\whitebear\digitalfarmbackend\model\entity\FarmBase.java#L25-L25): 更新时间

### MonitoringPoint
- **字段描述**：
    - [pointId](file://C:\programme\aProjects\digital_farm\DigitalFarmBackend\src\main\java\com\whitebear\digitalfarmbackend\model\entity\MonitoringPoint.java#L14-L15): 监测点ID（主键）
    - [baseId](file://C:\programme\aProjects\digital_farm\DigitalFarmBackend\src\main\java\com\whitebear\digitalfarmbackend\model\entity\FarmBase.java#L14-L15): 所属基地ID
    - [pointName](file://C:\programme\aProjects\digital_farm\DigitalFarmBackend\src\main\java\com\whitebear\digitalfarmbackend\model\entity\MonitoringPoint.java#L17-L17): 监测点名称
    - [location](file://C:\programme\aProjects\digital_farm\DigitalFarmBackend\src\main\java\com\whitebear\digitalfarmbackend\model\entity\FarmBase.java#L19-L19): 具体位置
    - [imgUrl](file://C:\programme\aProjects\digital_farm\DigitalFarmBackend\src\main\java\com\whitebear\digitalfarmbackend\model\entity\MonitoringPoint.java#L19-L19): 图片URL
    - [longitude](file://C:\programme\aProjects\digital_farm\DigitalFarmBackend\src\main\java\com\whitebear\digitalfarmbackend\model\entity\MonitoringPoint.java#L20-L20): 经度
    - [latitude](file://C:\programme\aProjects\digital_farm\DigitalFarmBackend\src\main\java\com\whitebear\digitalfarmbackend\model\entity\MonitoringPoint.java#L21-L21): 纬度
    - [createTime](file://C:\programme\aProjects\digital_farm\DigitalFarmBackend\src\main\java\com\whitebear\digitalfarmbackend\model\entity\FarmBase.java#L23-L23): 创建时间
    - [updateTime](file://C:\programme\aProjects\digital_farm\DigitalFarmBackend\src\main\java\com\whitebear\digitalfarmbackend\model\entity\FarmBase.java#L25-L25): 更新时间

## 快速启动

### 数据库配置
在 [application.properties](file://C:\programme\aProjects\digital_farm\DigitalFarmBackend\target\classes\application.properties) 中配置 MySQL 数据库连接：
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/digital_farm?useUnicode=true&characterEncoding=utf-8&useSSL=false&serverTimezone=UTC
spring.datasource.username=root
spring.datasource.password=A1woshilzt1
```


### 启动方式
1. 运行 [DigitalFarmBackendApplication.java](file://C:\programme\aProjects\digital_farm\DigitalFarmBackend\src\main\java\com\whitebear\digitalfarmbackend\DigitalFarmBackendApplication.java) 文件中的 [main](file://C:\programme\aProjects\digital_farm\DigitalFarmBackend\src\main\java\com\whitebear\digitalfarmbackend\DigitalFarmBackendApplication.java#L10-L12) 方法。
2. 访问 `http://localhost:8080/base` 获取农业基地列表。

## 测试
- 使用 [DigitalFarmBackendApplicationTests.java](file://C:\programme\aProjects\digital_farm\DigitalFarmBackend\src\test\java\com\whitebear\digitalfarmbackend\DigitalFarmBackendApplicationTests.java) 来验证 Spring 上下文是否成功加载。

## 注意事项
- 确保 MySQL 服务已启动，并且数据库 `digital_farm` 已创建。
- 可以根据需求扩展更多的接口和业务逻辑，例如添加新增基地、更新基地信息等功能。

## 贡献者
欢迎贡献代码！请遵循以下步骤：
1. Fork 本仓库。
2. 创建新分支 (`git checkout -b feature/new-feature`)。
3. 提交更改 (`git commit -m 'Add new feature'`)。
4. Push 到远程分支 (`git push origin feature/new-feature`)。
5. 发起 Pull Request。
