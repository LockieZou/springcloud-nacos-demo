springcloud nacos demo 项目
-

#### 项目版本
- nacos 0.2.2.RELEASE
- spring cloud Greenwich.SR1
- spring boot 2.2.2
- maven 3.9
- jdk 1.8


#### 服务说明
- boot-user-service springboot nacos服务提供者，端口号 8801
- boot-order-service springboot nacos消费者,端口号 8802
- cloud-order-service springcloud nacos消费者，端口号 8803
- cloud-user-service springcloud nacos服务提供者，端口号 8804  
- cloud-address-service springcloud nacos消费者，端口号 8805  
- cloud-gateway-service springcloud gateway网关,nacos消费者，端口号8806  
- id-generate-spring-boot-start 自定义spring boot starter  





##### 简要说明
boot-order-service和boot-user-service集成了rocketMq所以启动的时候需要先启动本地的rocketMq，启动方式在下面。<br/>
本地搭建nacos服务并启动然后访问http://127.0.0.1:8848/nacos 即可进入nacos控制台。<br/> 
nacos配置数据持久化到mysql了，执行nacos_config.sql脚本即可  
各个项目都集成了代码生成器  
项目脚本 spring_cloud_demo.sql，nacos_config.sql  
  


  
配置中心：<br/>
  http://localhost:8801/bootUser/getProperties  
  http://localhost:8802/bootOrder/getProperties  
  http://localhost:8803/cloudOrder/getProperties  
  http://localhost:8804/cloudUser/getProperties  
  http://localhost:8805/cloudAddress/getProperties  

通过上面接口获取每个服务的配置中心值  

boot-order-service和boot-user-service没有使用nacos的通用mysql配置  
cloud-order-service和cloud-user-service使用了通用的mysql配置  
文件可以通过 http://localhost:8803/cloudOrder/getMysqlProperties 获取数据库用户账户名   

注册中心：<br/>
 http://localhost:8802/bootOrder/getOrder
 http://localhost:8803/cloudOrder/getOrder  
 order-service接口调用了user-service服务的获取用户名接口  
   
 cloud-order-service采用了RestTemplate和Feign两种方式调用  
 cloud-user-service采用了RestTemplate和Feign两种方式调用    
 cloud-address-service采用了RestTemplate和Feign两种方式调用  
 
 cloud-order-service调用了cloud-user-service、cloud-address-service  
   
 common-framework增加雪花算法获取分布式ID工具IdGenerateUtil，可以在各个服务中使用直接使用@Autowired就可以了  
 
 
   
  
##### RocketMQ 测试步骤  
 启动windows本地nacos  
 启动windows本地RocketMq的namesrv 进入window本地rocketmq安装目录的bin目录下，双击mqnamesrv.cmd或者执行 start mqnamesrv.cmd    
 启动windows本地RocketMq的Broker 进入windows本地rocketmq安装目录的bin目录下，双击mqbroker.cmd或者执行 start mqbroker.cmd -n 127.0.0.1:9876 autoCreateTopicEnable=true  
 执行 boot-order-service 项目的MQProducerController的send方法，发送消息 http://127.0.0.1:8802/mqProducer/send?msg=hello  
 

##### 分布式事务Seata 测试步骤  
1.执行seata脚本  
2.启动本地nacos  
3.启动seata，windows进入seata文件夹/bin目录，双击seata-server.bat即可，启动后就可以在nacos服务列表看到  
![avatar](./nacos-seata.png)  
 
 