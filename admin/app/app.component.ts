/*
 * 组件 (Components)
 组件是一个模板的控制类用于处理应用和逻辑页面的视图部分。
 组件是构成 Angular 应用的基础和核心，可用于整个应用程序中。
 组件知道如何渲染自己及配置依赖注入。
 组件通过一些由属性和方法组成的 API 与视图交互。
 创建 Angular 组件的方法有三步：
 从 @angular/core 中引入 Component 修饰器
 建立一个普通的类，并用 @Component 修饰它

 @Component 装饰器能接受一个配置对象，并把紧随其后的类标记成了组件类。
 Angular 会基于这些信息创建和展示组件及其视图。
 @Component 中的配置项说明：
 selector - 一个 css 选择器，它告诉 Angular 在 父级 HTML 中寻找一个 <mylist> 标签，然后创建该组件，并插入此标签中。
 templateUrl - 组件 HTML 模板的地址。
 directives - 一个数组，包含 此 模板需要依赖的组件或指令。
 providers - 一个数组，包含组件所依赖的服务所需要的依赖注入提供者。
 * */

import {Component} from '@angular/core';
@Component({
    selector: 'my-app',
    template: '<h1> 我的第一个 Angular 应用 </h1>'
})
export class AppComponent {
}