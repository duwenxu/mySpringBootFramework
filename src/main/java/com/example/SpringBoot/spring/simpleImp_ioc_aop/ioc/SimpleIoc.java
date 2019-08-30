package com.example.springboot.spring.simpleImp_ioc_aop.ioc;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.FileInputStream;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * Ioc的简单实现
 *
 * @author duwenxu
 * @create 2019-08-29 17:03
 */
public class SimpleIoc {

    private Map<String,Object> beanMap=new HashMap<>();

    public SimpleIoc(String location) throws Exception {
        loadBeans(location);
    }

    private void registryBean(String id,Object bean){
        beanMap.put(id, bean);
    }

    public Object getBean(String name){
        Object bean = beanMap.get(name);
        if (bean ==null){
            throw new IllegalArgumentException("there is no bean with name"+name);
        }
        return bean;
    }

    /**
     * 加载Bean:
     * 1. 加载xml配置文件
     * 2. 获取到所有<bean>标签并遍历
     * 3. 利用反射 加载对应的class,并创建bean
     * 4. 遍历 所有的<property>标签，将属性值填充到相关字段中
     * 5. 将Bean注册到bean容器中
     */
    private void loadBeans(String localPath) throws Exception {
        FileInputStream inputStream = new FileInputStream(localPath);
        DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        Document document = documentBuilder.parse(inputStream);
        Element root = document.getDocumentElement();  //获取到文件根节点
        NodeList childNodes = root.getChildNodes();

        for (int i = 0; i < childNodes.getLength(); i++) {
            Node node = childNodes.item(i);
            if (node instanceof Element) {
                Element element = (Element) node;
                String id = element.getAttribute("id");
                String className = element.getAttribute("class");

                Class beanClass = null;
                try {
                    beanClass = Class.forName(className);   //反射获取对应的class对象，用于创建实例
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }

                Object bean = beanClass.newInstance();

                NodeList propertyList = element.getElementsByTagName("property");
                for (int j = 0; j < propertyList.getLength(); j++) {
                    Node propertyNode = propertyList.item(i);
                    if (propertyNode instanceof Element) {
                        Element property = (Element) propertyNode;
                        String name = property.getAttribute("name");
                        String value = property.getAttribute("value");

                        Field declaredField = bean.getClass().getDeclaredField(name);
                        declaredField.setAccessible(true);

                        if (value != null && value.length() > 0) {
                            //填充value到相关字段中
                            declaredField.set(name, value);     //不应该是填充相关字段吗
                        } else {
                            String ref = property.getAttribute("ref");
                            if (ref != null && ref.length() > 0) {
                                throw new IllegalArgumentException("ref config error");
                            }
                            //将引用填充到相关字段中
                            declaredField.set(ref, getBean(ref));
                        }
                        //注册Bean
                        registryBean(id, bean);
                    }
                }
            }
        }
    }
}
