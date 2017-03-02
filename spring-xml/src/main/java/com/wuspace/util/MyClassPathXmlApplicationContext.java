package com.wuspace.util;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.util.StringUtils;

import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.List;

/**
 * Created by WUCHAO on 2017/3/2.
 */
public class MyClassPathXmlApplicationContext {

    /**
     *
     1. 利用传入的参数获取 xml 文件的流, 并且利用 dom4j 解析成 Document 对象

 　　2. 对于 Document 对象获取根元素对象 <beans> 后对下面的 < bean > 标签进行遍历, 判断是否有符合的 id.

 　　3. 如果找到对应的 id, 相当于找到了一个 Element 元素, 开始创建对象, 先获取 class 属性, 根据属性值利用反射建立对象.

 　　4. 遍历 <bean> 标签下的 property 标签, 并对属性赋值. 注意, 需要单独处理 int,float 类型的属性. 因为在 xml 配置中这些属性都是以字符串的形式来配置的, 因此需要额外处理.

 　　5. 如果属性 property 标签有 ref 属性, 说明某个属性的值是一个对象, 那么根据 id(ref 属性的值) 去获取 ref 对应的对象, 再给属性赋值.

 　　6. 返回建立的对象, 如果没有对应的 id, 或者 <beans> 下没有子标签都会返回 null
     */

    private String xmlFileName;

    public Object getBean(String xmlFileName, String id) {
        //声明对象引用
        Object o = null;

        SAXReader saxReader = new SAXReader();
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(xmlFileName);
        try {
            Document document = saxReader.read(inputStream);
            Element root = document.getRootElement();
            if (root != null) {
                //获取跟节点的所有子节点
                List<Element> elements = root.elements();
                //循环遍历所有子节点
                for (Element e : elements) {
                    //如果传入的id等于子节点的id属性值，就利用发射初始化改bean为一个对象
                    if (e.attributeValue("id").equals(id)) {
                        String className = e.attributeValue("class");
                        Class clazz = Class.forName(className);
                        o = clazz.newInstance();
                        //获取该子节点所有子节点(property 标签)
                        List<Element> attributes = e.elements();
                        for (Element a : attributes) {
                            String fieldName = a.attributeValue("name");
                            Field field = clazz.getDeclaredField(fieldName);
                            field.setAccessible(true);
                            if (!StringUtils.isEmpty(a.attribute("ref"))) {
                                String refObj = a.attributeValue("ref");
                                field.set(o, getBean(refObj));
                            }
                            //此属性值是一个字符串.这里单独处理int,float类型变量.如果不处理,会将String类型直接赋值给int类型,发生ClassCastException
                            else {
                                String valObj = a.attributeValue("value");
                                if (valObj.matches("[0-9]+")) {
                                    Integer i = Integer.parseInt(valObj);
                                    field.set(o, i);
                                    continue;
                                }
                                if (valObj.matches("[0-9]*(\\.+)[0-9]*")) {
                                    Float f = Float.parseFloat(valObj);//double可以接受float类型
                                    field.set(o, f);
                                    continue;
                                }
                                field.set(o, valObj);
                            }
                        }
                    }
                }
            }
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        return o;
    }

    public Object getBean(String id) {
        return getBean(xmlFileName, id);
    }

    public MyClassPathXmlApplicationContext() {

    }

    public MyClassPathXmlApplicationContext(String xmlFileName) {
        this.xmlFileName = xmlFileName;
    }
}
