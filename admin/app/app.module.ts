/*
 *模块
 模块由一块代码组成，可用于执行一个简单的任务。
 Angular 应用是由模块化的，它有自己的模块系统：NgModules。
 每个 Angular 应该至少要有一个模块 (根模块)，一般可以命名为：AppModule。
 Angular 模块是一个带有 @NgModule 装饰器的类，它接收一个用来描述模块属性的元数据对象。
 几个重要的属性如下：
 declarations （声明） - 视图类属于这个模块。 Angular 有三种类型的视图类： 组件 、 指令 和 管道 。
 exports - 声明（ declaration ）的子集，可用于其它模块中的组件模板 。
 imports - 本模块组件模板中需要由其它导出类的模块。
 providers - 服务的创建者。本模块把它们加入全局的服务表中，让它们在应用中的任何部分都可被访问到。
 bootstrap - 应用的主视图，称为根组件，它是所有其它应用视图的宿主。只有根模块需要设置 bootstrap 属性中。
 * */


import {NgModule}      from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';
import {AppComponent} from "./app.component";
@NgModule({
    imports: [BrowserModule],
    declarations: [AppComponent],
    exports: [AppComponent],
    bootstrap: [AppComponent]
})
export class AppModule {
}