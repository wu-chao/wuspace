# AngularJS 1.X    

1. ng-app=" "  定义 angularJS 的使用范围；
2. ng-init="变量 = 值; 变量 ='值'"  初始化变量的值，有多个变量时，中间用分号隔开；
3. ng-model="变量"  定义变量名；
4. ng-bind="变量"  绑定变量名，获取该变量的数据。这里的变量就是第 3 条的变量名。但是一般都用双重花括号来获取变量的值，比如：{{变量}}。



1. ng-model 是用于表单元素 (input, select, textarea) 的，支持双向绑定。对普通元素无效；
2. ng-bind 用于普通元素，不能用于表单元素，应用程序单向地渲染数据到元素；
3. 当 ng-bind 和 {{}} 同时使用时，ng-bind 绑定的值覆盖该元素的内容。



1、uppercase，lowercase 大小写转换

```
{{ "lower cap string" | uppercase }}   // 结果：LOWER CAP STRING
{{ "TANK is GOOD" | lowercase }}      // 结果：tank is good
```

2、date 格式化

```
{{1490161945000 | date:"yyyy-MM-dd HH:mm:ss"}} // 2017-03-22 13:52:25
```

3、number 格式化（保留小数）

```
{{149016.1945000 | number:2}}
```

4、currency 货币格式化

```
{{ 250 | currency }}            // 结果：$250.00
{{ 250 | currency:"RMB ￥ " }}  // 结果：RMB ￥ 250.00
```

5、filter 查找

输入过滤器可以通过一个管道字符（|）和一个过滤器添加到指令中，该过滤器后跟一个冒号和一个模型名称。

filter 过滤器从数组中选择一个子集

```
 // 查找name为iphone的行
{{ [{"age": 20,"id": 10,"name": "iphone"},
{"age": 12,"id": 11,"name": "sunm xing"},
{"age": 44,"id": 12,"name": "test abc"}
] | filter:{'name':'iphone'} }}        
```

6、limitTo 截取

```
{{"1234567890" | limitTo :6}} // 从前面开始截取6位
{{"1234567890" | limitTo:-4}} // 从后面开始截取4位
```

7、orderBy 排序（默认正序asc,倒序添加-负号）

```
 // 根id降序排
{{ [{"age": 20,"id": 10,"name": "iphone"},
{"age": 12,"id": 11,"name": "sunm xing"},
{"age": 44,"id": 12,"name": "test abc"}
] | orderBy:'-id' }}

// 根据id升序排
{{ [{"age": 20,"id": 10,"name": "iphone"},
{"age": 12,"id": 11,"name": "sunm xing"},
{"age": 44,"id": 12,"name": "test abc"}
] | orderBy:'id' }}
```



**Angular 的很多服务，在 DOM 中有对应的对象，那为什么不使用这些对象，而是要用服务呢？**

因为这些服务可以获取到 Angular 应用声明周期的每一个阶段，并且和 $watch 整合，让 Angular 可以监控应用，处理事件变化。普通的 DOM 对象则不能在 Angular 应用声明周期中和应用整合。



## AngularJS 模块 

模块定义了一个应用程序。

模块是应用程序中不同部分的容器。

模块是应用控制器的容器。

控制器通常属于一个模块。

### 创建模块 

你可以通过 AngularJS 的 **angular.module** 函数来创建模块：

``` 
<div ng-app="myApp">...</div>

<script>

var app = angular.module("myApp", []); 

</script> 
```

> 在模块定义中 [] 参数用于定义模块的依赖关系。
> 中括号 [] 表示该模块没有依赖，如果有依赖的话会在中括号写上依赖的模块名字。 



### 添加控制器 

你可以使用 **ng-controller** 指令来添加应用的控制器:

``` 
<div ng-app="myApp" ng-controller="myCtrl">
{{firstName + " " + lastName}}
</div>

<script>

var app = angular.module("myApp", []);

app.controller("myCtrl", function($scope) {
    $scope.firstName = "John";
    $scope.lastName = "Doe";
});

</script> 
```



### 添加指令 

AngularJS 提供了很多内置的指令，你可以使用它们来为你的应用添加功能。

完整的指令内容可以参阅 [AngularJS 参考手册](http://www.runoob.com/angularjs/angularjs-reference.html)。

此外，你可以使用模块来为你应用添加自己的指令：

``` 
<div ng-app="myApp" runoob-directive></div>

<script>

var app = angular.module("myApp", []);

app.directive("runoobDirective", function() {
    return {
        template : "我在指令构造器中创建!"
    };
});
</script> 
```



### 模块和控制器包含在 JS 文件中 

通常 AngularJS 应用程序将模块和控制器包含在 JavaScript 文件中。

在以下实例中， "myApp.js" 包含了应用模块的定义程序， "myCtrl.js" 文件包含了控制器：

``` 
<!DOCTYPE html>
<html>
<script src="http://apps.bdimg.com/libs/angular.js/1.4.6/angular.min.js"></script>
<body>

<div ng-app="myApp" ng-controller="myCtrl">
{{firstName + " " + lastName}}
</div>

<script src="myApp.js"></script>
<script src="myCtrl.js"></script>

</body>
</html> 
```

App.js 

``` 
var app = angular.module("myApp", []); 
```




> {{}} 解析括号中的表达式，并把值输出到模板中。
|管道操作符，用于将前面的结果通过管道输出成另一种格式。




# AngularJS 2.X 

### 新建应用  

> http://blog.csdn.net/xiaozhi_2016/article/details/68483692  

需要先安装 nodejs 和 npm。 

``` 
npm install cnpm (npm 设置淘宝源)
npm install -g angular-cli
ng -v 
set SASS_BINARY_SITE=https://npm.taobao.org/mirrors/node-sass/ （设置 sass 路径为 淘宝的源，不然会一直卡住）
ng new augualr-demo
cd angular-demo
ng serve 
```

访问 http://localhost:4200 / 即可运行项目。

### 创建 Service 
``` 
cd src/app 
sudo mkdir core
sudo chmod -R 777 core
cd core 
ng g s auth 
``` 
(ng generate service auth)，在 core 包下会生成 auth.service.ts 和 auth.service.spec.ts 这两个文件。 
 
 ``` 
 # auth.serivce.ts 
 
 import {Injectable} from '@angular/core';
 
 @Injectable() 
 export class AuthService {
    constructor() {} 
    loginWithCredentials(username: string, password: string): boolean { 
        if (username === 'admin') {
            return true;
        } else {
            return false; 
        }
    }
 }
 ```

#### 依赖注入 

任何类都可以通过依赖注入这种方式提供和注入，它提供一种解耦的方式，通过 Providers 提供，通过 constructor 注入。 

在 login.component.ts 文件中的 @Component 装配器的 providers 中配置： 
``` 
@Component({
 selector: 'app-login',
 template: '',
 providers: [AuthService]
})
``` 
在类的构造函数中注入。  
``` 
export class LoginComponent implements OnInit {
    // 不需要显示地声明成员变量 service
    constructor(private service: AuthService) {} 
    
    ngOnInit() {} 
}
``` 

注入进来的类可以不在 providers 中配置，而放到 app.module.ts 中去配置。 
在 app.module.ts 中配置的可以在模块中全局使用。 
``` 
providers: [
    {provide: 'auth', useClass: AuthService}
]
``` 
providers 是一个数组，这个数组是把你想要注入到其他组件中的服务配置在这里。注意这里的写法和上面的有点区别，没有直接写成： 
``` 
providers: [AuthService] 
``` 
而是给出了一个对象，里面有两个属性，provider 和 useClass。  
provider 定义了这个服务的名称，有需要注入这个服务的就引用这个名称就好。 
useClass 指明这个名称对应的服务是一个类，本例中就是 AuthService 了。 

现在改动一下刚才的 ts 文件，去掉头部的 import 和组件修饰器中的 providers， 更改其构造函数为： 
``` 
constructor(@Inject('auth') private service) {} 
``` 
去掉了 service 的类型声明，但加了一个修饰符 @Inject('auth')，这个修饰符的意思是请到系统配置中找到名称为 auth 的那个依赖注入到修饰的变量中。  


#### 双向绑定 
双向绑定机制是在组件中提供成员变量，然后在模板中引用这个数据变量，当在组件内对数据进行操作后再反馈到界面上。 

改造 login.component.ts,首先在 class 中声明两个数据变量 username 和 password： 
``` 
username = '';
password = '';
``` 
然后去掉 onClick 方法的参数，并将内部的语句改造成如下的样子： 
``` 
console.log('auth result is: ' 

    + this.service.loginWithCredentials(this.username, this.password)); 
``` 

去掉参数的原因是双向绑定后，我们通过数据成员变量就可以知道用户名和密码了，不需要再传递参数了。
成员变量的引用方式是 `this.成员变量` 。

修改模板： 
``` 
<div>
    <input type="text" [(ngModel)]="username"/>
    <input type="password" [(ngModel)]="password"/>
    <button (click)="onClick">Login</button>
</div>
``` 

## 模块化 
Angular Module 是一个用 @NgModule 修饰的类，@NgModule 设置一些元数据告诉 Angular 怎样编译和运行该模块。同时这些元数据声明哪些是 Module 拥有的组件/指令和管道，哪些是外部可访问的组件。 

@Input() 是输入型绑定的修饰符，用于把数据从父组件传到子组件。
```  
export class ToDoComponent implements OnInit {
    @Input() itemCount: number;
    
    constructor() {}
    
    onInit() {}
} 
``` 



