# library-system
------
这是一个简易的图书管理系统，实现了用户和管理员的注册、登录、对图书的增删改查等功能。
## 1、环境要求
- `Java 17`及以上
- `Spring Boot 3.2.5`
- `Maven 3.8.x`及以上
## 2、安装和运行
将项目clone到本地，运行`mvn spring-boot:run`，即可启动图书管理系统
## 3、使用方法
启动系统后，浏览器访问Swagger UI界面(http://localhost:8080/swagger-ui/index.html)
可以看到如下界面：
![image](https://github.com/user-attachments/assets/636027ae-3aaa-4cab-bc71-64509fb7cdf0)
系统提供了RESTFul的接口，包括对图书的增删改查以及普通用户和管理员的注册。其中，对图书的查询支持根据id(通过`GET /api/books/{id}`)、书名、作者和ISBN码查询(通过`GET /api/books/search`)，亦支持列出所有图书(通过`GET /api/books`)；对图书的修改和注册管理员需要先以管理员身份登录。
初始状态下，系统提供了一个默认的普通用户账号密码(`user@userpass`)和一个管理员账号密码(`admin@adminpass`)，并提供了三本图书供操作示例。
