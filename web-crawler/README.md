# Web Magic 

## 实现 PageProcessor
### 页面元素的抽取 
抽取技术: 
- XPath：XPath 本来是用于 XML 中获取元素的一种查询语言，但是用于 Html 也是比较方便的。
    ``` 
    page.getHtml().xpath("//h1[@class='entry-title public']/strong/a/text()")
    ```
    这段代码使用了 XPath，它的意思是“查找所有 class 属性为'entry-title public'的 h1 元素，并找到他的 strong 子节点的a子节点，并提取 a 节点的文本信息”。 对应的Html是这样子的：
    ``` 
    ... 
    <h1 class="entry-title public">
        <strong>
            <a href="">web magic</a>
        </strong>
    <h1>
    ...
    ```
- JsonPath：JsonPath 是与 XPath 很类似的一个语言，它用于从 Json 中快速定位一条内容。WebMagic 中使用的 JsonPath 格式可以参考这里：https://code.google.com/p/json-path/。

- CSS 选择器：CSS 选择器是与 XPath 类似的语言。如果大家做过前端开发，肯定知道 $('h1.entry-title')这种写法的含义。客观的说，它比 XPath 写起来要简单一些，但是如果写复杂一点的抽取规则，就相对要麻烦一点。

- 正则表达式：正则表达式则是一种通用的文本抽取语言。
    ``` 
    page.addTargetRequests(page.getHtml().links().regex("(https://github\\.com/\\w+/\\w+)").all());
    ```
    这段代码就用到了正则表达式，它表示匹配所有 "https://github.com/code4craft/webmagic" 这样的链接。


### 链接的发现 
有了处理页面的逻辑，我们的爬虫就接近完工了！

但是现在还有一个问题：一个站点的页面是很多的，一开始我们不可能全部列举出来，于是如何发现后续的链接，是一个爬虫不可缺少的一部分。

``` 
page.addTargetRequests(page.getHtml().links().regex("(https://github\\.com/\\w+/\\w+)").all());
```
这段代码的分为两部分，`page.getHtml().links().regex("(https://github\\.com/\\w+/\\w+)").all()` 用于获取所有满足 `(https:/ /github\.com/\w+/\w+)` 这个正则表达式的链接，`page.addTargetRequests()` 则将这些链接加入到待抓取的队列中去。


## Selectable 接口 
`Selectable` 相关的抽取元素链式 API 是 WebMagic 的一个核心功能。使用 Selectable 接口，你可以直接完成页面元素的链式抽取，也无需去关心抽取的细节。

在刚才的例子中可以看到，`page.getHtml()` 返回的是一个 Html 对象，它实现了 Selectable 接口。这个接口包含一些重要的方法，我将它分为两类：抽取部分和获取结果部分。

## 使用 Pipeline 保存结果 


## 爬虫的配置、启动和终止 
### Spider  
`Spider` 是爬虫启动的入口。在启动爬虫之前，我们需要使用一个 `PageProcessor` 创建一个 `Spider` 对象，然后使用 `run()` 进行启动。同时Spider的其他组件（Downloader、Scheduler、Pipeline）都可以通过 set 方法来进行设置。

### Site 
对站点本身的一些配置信息，例如编码、HTTP 头、超时时间、重试策略、代理等，都可以通过设置 `Site` 对象来进行配置。


## 爬虫的监控 


## 配置代理


# Web Magic 组件的使用和定制

## 使用和定制 Pipeline
`Pipeline` 是抽取结束后，进行处理的部分，它主要用于抽取结果的保存，也可以定制 `Pipeline` 可以实现一些通用的功能。
 
在 WebMagic 里，一个 Spider 可以有多个 Pipeline，使用 `Spider.addPipeline()` 即可增加一个 Pipeline。这些 Pipeline 都会得到处理，例如你可以使用
``` 
spider.addPipeline(new ConsolePipeline()).addPipeline(new FilePipeline())
```
实现输出结果到控制台，并且保存到文件的目标。


## 使用和定制 Scheduler 


## 使用和定制 Downloader 
