# WEB应用专业团队官网项目。
在线地址：http://www.pzhulsx.online:8088/index
- 更新项目上线，部署上线，在线访问网站
![](README_md_files/image.png?v=1&type=image)

项目采用dubbo + springboot的分布式开发，各位看官需要自己配置dubbo + zk，集成了druid，swagger2相关配置，读者自己根据代码配置吧。
修改team-index-web的zk配置：

修改team-index-service的dubbo + zk + mysql的配置，需要自己进行一个配置。

配置fastdfs：

项目目录：
```
├─team-api 			# 一些公用的API

├─team-bean 			#公用的JavaBean对象

├─team-common-util			#公用的工具类

├─team-index-service			#操作数据库的服务类springboot微服务

├─team-index-web				#负责页面展示的微服务

├─team-reverse-engineering			#mybatis的逆向工程文件，可以忽略

├─team-service-util							#操作数据库需要的工具类

└─team-web-util								#操作前端需要的工具类。
```


首页链接了团队的宣传视频：
![image](https://user-images.githubusercontent.com/83112491/115953913-d0781800-a520-11eb-91ac-e23dc53ef08f.png)


中间的人物介绍：
![image](https://user-images.githubusercontent.com/83112491/115953923-d66df900-a520-11eb-8379-be60b8b79a5a.png)

底部存在轮播图：
![image](https://user-images.githubusercontent.com/83112491/115953929-da9a1680-a520-11eb-98fd-19f9a3d614ba.png)


团队动态：
![image](https://user-images.githubusercontent.com/83112491/115953946-e2f25180-a520-11eb-9bc4-d0c818090f56.png)


动态详情：
![image](https://user-images.githubusercontent.com/83112491/115953956-e980c900-a520-11eb-8f7d-1f37c928d02e.png)


热点新闻：
![image](https://user-images.githubusercontent.com/83112491/115953967-f8677b80-a520-11eb-975e-cb8234ae1456.png)


团队成果：
![image](https://user-images.githubusercontent.com/83112491/115953989-0917f180-a521-11eb-9fd7-44e16a22197f.png)


学习资源:
![image](https://user-images.githubusercontent.com/83112491/115954001-192fd100-a521-11eb-8b51-4e74b42b9d5d.png)


登录界面：
![image](https://user-images.githubusercontent.com/83112491/115954007-21880c00-a521-11eb-8180-5e9161ffed80.png)


注册界面：
![image](https://user-images.githubusercontent.com/83112491/115954010-277ded00-a521-11eb-848a-58ffad394e55.png)


手机找回密码：
![image](https://user-images.githubusercontent.com/83112491/115954013-2f3d9180-a521-11eb-9c8e-3ada56606160.png)


后端界面展示（个人主页）：展示阅读数，新闻数量，成果数量，分享的资源。

不同的卡片显示不同的模块：有我的动态，我的新闻，我的成果，我的资源，以及相应的管理，用户管理，新用户审核等等。
![image](https://user-images.githubusercontent.com/83112491/115954024-3f557100-a521-11eb-8f0a-ee3876c4755e.png)


添加动态，可以自定义上传图片（需要自行配置fastdfs）。
![image](https://user-images.githubusercontent.com/83112491/115954026-47151580-a521-11eb-9edf-e522dc4bddd7.png)

![image](https://user-images.githubusercontent.com/83112491/115954029-4c726000-a521-11eb-8b26-bdd262b28f93.png)

添加成果，添加资源。

并可以进行编辑。
![image](https://user-images.githubusercontent.com/83112491/115954038-57c58b80-a521-11eb-881a-3eec88e2ce61.png)


## 读者自行开发吧，开发不易，有问题可以联系我

qq：1579587634 邮箱：[1579587634@qq.com](mailto:1579587634@qq.com)
