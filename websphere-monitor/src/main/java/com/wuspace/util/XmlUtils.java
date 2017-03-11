package com.wuspace.util;

import com.wuspace.domain.PerformanceMonitor;
import com.wuspace.domain.Server;
import com.wuspace.domain.Servers;
import com.wuspace.domain.WebsphereNode;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.List;

/**
 * Created by WUCHAO on 2017/3/10.
 */
public class XmlUtils {

    public PerformanceMonitor parsePerformanceMonitor() {
        PerformanceMonitor performanceMonitor = new PerformanceMonitor();
        SAXReader saxReader = new SAXReader();
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("xxxx.xml");
        Document document = null;
        try {
            document = saxReader.read(inputStream);
            //PerformanceMonitor
            Element root = document.getRootElement();
            //Node
            List<Element> elementsOfRoot = root.elements();
            for (Element elementOfRoot : elementsOfRoot) {
                WebsphereNode websphereNode = new WebsphereNode();
                //Server-server1
                List<Element> noteE = elementOfRoot.elements();
                for (Element elementOfNode : noteE) {
                    Servers servers = new Servers();
                    List<Element> fff = elementOfNode.elements();
                    for (Element fswe : fff) {
                        servers.setServer((Server) getStat(fswe));
                    }
                    websphereNode.getServers().add(servers);
                }
                performanceMonitor.getWebsphereNodes().add(websphereNode);
            }
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return performanceMonitor;
    }

    public Object getStat(Element element) {
        Object o = null;
        try {
            String classNameTemp = element.attributeValue("name").replaceAll("\\s|\\(|\\)|\\/","");
            String className = Character.toUpperCase(classNameTemp.charAt(0)) + classNameTemp.substring(1);
            Class clazz = Class.forName("com.wuspace.domain." + className);
            o = clazz.newInstance();
            List<Element> elements = element.elements();
            for (Element childElement : elements) {
                String eNameTemp = childElement.attributeValue("name").replaceAll("\\s|\\(|\\)|\\/","");
                String eName = Character.toLowerCase(eNameTemp.charAt(0)) + eNameTemp.substring(1);
                try {
                    Field field = clazz.getDeclaredField(eName);
                    field.setAccessible(true);
                    if (childElement.getName().equals("Stat")) {
                        field.set(o, getStat(childElement));
                    } else {
                        String val = "value";
                        if (childElement.getName().contains("CountStatistic")) {
                            val = "count";
                        }
                        if (childElement.getName().contains("BoundedRangeStatistic")) {
                            val = "value";
                        }
                        if (childElement.getName().contains("RangeStatistic")) {
                            val = "value";
                        }
                        if (childElement.getName().contains("TimeStatistic")) {
                            val = "totalTime";
                        }
                        field.set(o, Integer.parseInt(childElement.attributeValue(val)));
                    }
                } catch (NoSuchFieldException e1) {
                    e1.printStackTrace();
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return o;
    }

}
