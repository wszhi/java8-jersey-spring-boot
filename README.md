Gaia
==========

### 开发环境
- 确保开发环境下有jdk8，以及mysql 5.5+
- 使用下面的脚本,提前在mysql中建好数据库

```
DROP DATABASE IF EXISTS `gaia`; CREATE SCHEMA `gaia` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
```

- 使用`./gradlew cleanIdea idea`生成Intellij工程
- 使用`Intellij`打开生成的`gaia.ipr`文件
- 找到`GaiaApplication`这个类，运行`main()`方法启动服务器

### 测试服务器是否启动正常

- 检查启动中有无异常log
- 打开浏览器，访问<http://localhost:8080/gaia/rest/application.wadl>，看是否有API列表输出
- 打开浏览器，访问<http://localhost:8080/gaia/rest/product/1>，看是否返回包含`"errorCode":"RESOURCE_NOT_FOUND"`这样的出错信息
- 在数据库console中，使用后面的脚本在数据库中插入一条数据`insert into gaia.PRODUCT (`name`, `time_created`) values ('product_name', NOW());`
- 在数据库console中，使用`select * from gaia.PRODUCT`，查看是否已插入数据成功，并记下数据的`id`
- 加入上一步记下的`id`为`1`，打开浏览器，访问<http://localhost:8080/gaia/rest/product/1>
- 检查浏览器的返回是否是 `{"id":1,"name":"product_name","timeCreated":1474615151000}`，其中`timeCreated`对应的数字可能会略有不同

### 查看所有API 地址及请求所需方式、参数

- 中心服务器：<http://localhost:8080/gaia/rest/application.wadl>

*注意：更改localhost到你希望查看的服务器IP*


####1 show all A1N
GET请求,访问URL:
http://localhost:8080/gaia/rest/a1ns
得到返回的JSON数据

####2 select A1N by ID
GET请求,访问URL:
http://localhost:8080/gaia/rest/a1ns/1
得到返回的JSON数据

####3 add A1N with B1N list
POST请求,使用postman发送请求

访问的URL为
http://localhost:8080/gaia/rest/a1ns
请求类型是POST
Headers参数为Content-Type:application/json
Body的数据类型是row,JSON(application/json)
```
{
  "name": "today",
  "b1Ns": [
    {
      "name": "Btest1"
    },
    {
      "name": "Btest2"
    }
  ]
}
```
返回一个URIString
####4 update A1N with B1N list
PUT 请求,使用postman发送请求

访问的URL为
http://localhost:8080/gaia/rest/a1ns/1
请求类型是PUT
Body的数据类型是row,JSON(application/json)
```
{
  "name": "test1",
  "b1Ns": [
    {
      "id": 1,
      "name": "btest1"
    }
  ]
}
```
返回一个URIString

#### 5 Delete A1N By ID
DELETE请求,使用postman发送请求

访问的URL为
http://localhost:8080/gaia/rest/a1ns/1
请求类型是DELETE
返回一个URI String

#### 6 Show all ANN
GET请求,访问URL:
http://localhost:8080/gaia/rest/anns
得到返回的JSON数据

#### 7 Select ANN By Id
GET请求,访问URL:
http://localhost:8080/gaia/rest/anns/1
得到返回的JSON数据

#### 8 Add ANN With BNN
POST请求,使用postman发送请求

访问的URL为
http://localhost:8080/gaia/rest/anns
请求类型是POST
Headers参数为Content-Type:application/json
Body的数据类型是row,JSON(application/json)
```
{
    "name":"todayA1",
    "bnn":[{
        "id":1
        },{
        "name":"todayB2"
    }]
}
```
请求将会在数据库中的操作:
插入一个ANN,ANN的name为todayA1;
插入一个BNN,BNN的name为todayB2;
关联表ANN_BNN中插入一条记录:ann_id为刚刚插入的ANN的ID,bnn_id为1。
关联表ANN_BNN中插入一条记录:ann_id为刚刚插入的ANN的ID,bnn_id为刚刚插入的"todayB2"记录的ID。
注意:bnn的id必须已经存在于BNN表中。

返回一个URI String


#### 9 Update ANN With B1N List
PUT 请求,使用postman发送请求

访问的URL为
http://localhost:8080/gaia/rest/anns/1
请求类型是PUT
Body的数据类型是row,JSON(application/json)
```
{
    "name":"todayAChanged",
    "bnn":[{
        "id":2
        },{
        "id":3
        },{
            "name":"todayBNew"
        }]
    
}
```
请求将会在数据库中的操作:
更新ID为1的ANN,改name为todayAChanged;
插入一个BNN,BNN的name为todayBNew;
关联表ANN_BNN中ann_id为1的记录更新为当前的三条,bnn_id为2、3和刚插入的BNN的ID。
注意:bnn的id必须已经存在于BNN表中。

返回一个URI String

#### 10 Delete ANN By ID
DELETE请求,使用postman发送请求

访问的URL为
http://localhost:8080/gaia/rest/anns/1
请求类型是DELETE
返回一个URI String