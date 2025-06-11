# ServletDemo

## Servlet快速入门

1.创建web项目，导入servlet依赖

```xml
<dependency></dependency>
    <groupId>javax.servlet</groupId>
    <artifactId>javax.servlet-api</artifactId>
    <version>4.0.1</version>
</dependency>
```

2.创建servlet类，继承HttpServlet类，重写service方法，在service方法中编写业务逻辑

```java
public class HelloServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("HelloServlet");
    }
}
```

3.在web.xml文件中配置servlet

```xml
<servlet>
    <servlet-name>HelloServlet</servlet-name>
    <servlet-class>com.example.servlet.HelloServlet</servlet-class>
</servlet>
<servlet-mapping>
    <servlet-name>HelloServlet</servlet-name>
    <url-pattern>/hello</url-pattern>
</servlet-mapping>
```

4.在类上添加@WebServlet注解，配置servlet

```java
@WebServlet("/hello")
public class HelloServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("HelloServlet");
    }
}
```

5.访问servlet
<http://localhost:8080/hello>

## Servlet生命周期

1.创建servlet对象，调用init方法，初始化servlet
2.调用service方法，处理请求
3.调用destroy方法，销毁servlet

## urlPattern配置规则
1.精确匹配：/hello
2.目录匹配：/hello/*
3.后缀匹配：*.do
4.默认匹配：/
## 重定向和转发
1.重定向：response.sendRedirect("/hello")
2.转发：request.getRequestDispatcher("/hello").forward(request, response)
