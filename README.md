springcloud nacos demo 项目
-

#### 项目版本
- nacos 0.2.2.RELEASE
- spring cloud Greenwich.SR1
- spring boot 2.2.2
- maven 3.9
- jdk 1.8


#### 服务说明
- boot-order-service springboot nacos消费者,端口号 8001
- boot-user-service springboot nacos服务提供者，端口号 8002
- cloud-order-service springcloud nacos消费者，端口号 8003
- cloud-user-service springcloud nacos服务提供者，端口号 8004




##### 简要说明
本地搭建nacos服务 http://127.0.0.1:8848/nacos访问nacos控制台。<br/> 
  
配置中心：<br/>
  http://localhost:8801/bootUser/getProperties  
  http://localhost:8802/bootOrder/getProperties  
  http://localhost:8803/cloudOrder/getProperties  
  http://localhost:8804/cloudUser/getProperties  

通过上面接口获取每个服务的配置中心值  

cloud-order-service和cloud-user-service使用了通用的mysql配置  
文件可以通过 http://localhost:8803/cloudOrder/getMysqlProperties 获取数据库用户账户名   

注册中心：<br/>
 http://localhost:8802/bootOrder/getOrder
 http://localhost:8803/cloudOrder/getOrder  
 order-service接口调用了user-service服务的获取用户名接口  
   
 cloud-order-service采用了RestTemplate和Feign两种方式调用

