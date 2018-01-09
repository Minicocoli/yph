# 项目基本框架
Create By Hokok 2017-12-22

1: 开发工具: JDK1.7、Maven、idea 2017、mysql、redis(下个版本加入).

2: 模块结构-->  以后拓展也是按照模块形式拓展。

    1) yph-admin-web : 系统后台管理系统。
    
    2) yph-common    : 系统共有模块。内有系统的拦截器，日志系统。
    
    3) yph-generator : mybatis 代码生成器。
    
    4) yph-system    : 系统框架模块。内有菜单管理、基础数据等。
    
    ..........
    
 3：api 接口文档地址
 
    http://localhost:8080/admin/swagger-ui.html
 
 4：前端框架使用 layUi
    
    开发约定:
    
    
        layui 文档地址：http://www.layui.com/demo/
     
        1) 遵循页面和js的分离。
        
        2) 遵循js渲染逻辑 和 数据操作分离。
        
        3) 遵循方法代码上写注释。
        
        4) 遵循命名规范。(驼峰法)
      
 5：后端开发约定:
    
    1) 项目基本结构：
    
           yph--|               : 项目根路径
                |--controller   : Controller层:  命名规则：xxxxController    ---->   Controller 控制器
                |
                |--entity       : javaBean 实体
                |    |  
                |    |--vo      : 用于视图层的复杂数据渲染(比如渲染订单：OrderVo -->(Order + Goods))。不是必须要的。
                |    |
                |    |--dto     : 用于业务层的传输对象。不是必定要的实体。改实体是在 service层的调用流转所需要的。
                |    |
                |--mapper       : ORM 层接口.  
                |
                |--service      : service 层
                    |
                    |--impl     : service 层实现。 命名约定--> xxxxServiceImpl.java .类似:  OrderServiceImpl.java
                    |
                    |           : service 层接口。 命名约定--> IxxxService.java .类似: IOrderService.java
                    
                    
    2) 
           
    
     
      








    


