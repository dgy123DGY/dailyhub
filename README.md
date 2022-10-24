# dailyhub - 阅读收藏项目


首发公众号：**MarkerHub**

作者：吕一明

* 项目开发文档链接：https://www.zhuawaba.com/post/124
* 基于canal同步mysql与elasticsearch文档：https://www.zhuawaba.com/post/123


线上演示地址：https://www.zhuawaba.com/dailyhub

视频讲解：https://www.bilibili.com/video/BV1Jq4y1w7Bc/

### 1、前言

我们经常浏览很多网页，看到一些觉得有用、或者有意思的网页时候，我们通常会收藏到书签。然而当书签的收藏越来越多，分类越来越多，想找到之前的那条收藏就比较麻烦，虽然也有搜索功能，但还需要另外点击很多操作。

最重要的是，收藏网页的时候我往往需要记录一些浏览心得，作为我浏览的足迹和记忆。其实对我来说，收藏的分类不是特别重要，这是一个费脑的过程，因为很多网页可以放到多个文件夹，这时候又出现了选择困难症了，网页各式各样，总不能给每种网页都起个分类收藏。对我来说有点冗余。

于是我打算开发一个系统，以时间为记录线，在未打开网站的时候就可以快速记录我当前浏览网页的网址和标题，然后我还可以记录心得。另外还需要一个很强大的搜索引擎，快速搜索记录。这样我可以查看我每天浏览了那些网页，然后还可以分享到收藏广场上给更多的网友。

那么，接下来，跟着我，一起去完成这个项目的开发吧

#### 项目功能

* 公众号扫码登录注册
* 快速收藏网页
* 收藏夹列表
* 收藏检索

#### 技术栈

后端：springboot、spring data jpa、mysql、redis、elasticsearch、canal、mapstruct

前端：bootstrap 5

其实之前我在eblog项目中做个搜索功能，那时候使用的是rabbitmq同步数据到es，这次我为了减少代码开发的量，使用了canal基于binlog同步数据到es，这涉及到服务搭建的过程，后续我都会一一讲解。

### 2、线上演示

[https://www.zhuawaba.com/dailyhub](https://www.zhuawaba.com/dailyhub)

![图片](https://image-1300566513.cos.ap-guangzhou.myqcloud.com/upload/images/20220124/ed9e17d022704416ad8e8a134b3c0478.png)

------

>关注公众号 **Java问答社** 回复：**678** 获取项目更多信息
>
>![图片](https://image-1300566513.cos.ap-guangzhou.myqcloud.com/upload/images/20220124/14a720d7f23546db9b56c42cd225d792.png)
>
>关注公众号 **Java问答社** 回复：**678** 获取项目更多信息
