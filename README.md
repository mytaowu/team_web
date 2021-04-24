# WEB应用专业团队官网项目。
在线地址：http://www.pzhulsx.online:8088/index
- 更新项目上线，部署上线，在线访问网站
![](README_md_files/image.png?v=1&type=image)

项目采用dubbo + springboot的分布式开发，各位看官需要自己配置dubbo + zk，集成了druid，swagger2相关配置，读者自己根据代码配置吧。
修改team-index-web的zk配置：

修改team-index-service的dubbo + zk + mysql的配置，需要自己进行一个配置。

配置fastdfs：

项目目录：
├─team-api 			# 一些公用的API
├─team-bean 			#公用的JavaBean对象
├─team-common-util			#公用的工具类
├─team-index-service			#操作数据库的服务类springboot微服务
├─team-index-web				#负责页面展示的微服务
├─team-reverse-engineering			#mybatis的逆向工程文件，可以忽略
├─team-service-util							#操作数据库需要的工具类
└─team-web-util								#操作前端需要的工具类。


首页链接了团队的宣传视频：

中间的人物介绍：

底部存在轮播图：


团队动态：

动态详情：

热点新闻：

新闻详情：

团队成果：

学习资源:

登录界面：

注册界面：

手机找回密码：

后端界面展示（个人主页）：展示阅读数，新闻数量，成果数量，分享的资源。

不同的卡片显示不同的模块：有我的动态，我的新闻，我的成果，我的资源，以及相应的管理，用户管理，新用户审核等等。


添加动态，可以自定义上传图片（需要自行配置fastdfs）。

添加成果，添加资源。

并可以进行编辑。



## 读者自行开发吧，开发不易，有问题可以联系我

qq：1579587634 邮箱：[1579587634@qq.com](mailto:1579587634@qq.com)
