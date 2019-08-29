package com.example.springboot.spring.simpleImp_ioc_aop.ioc;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.FileInputStream;

/**
 * Ioc的简单实现
 *
 * @author duwenxu
 * @create 2019-08-29 17:03
 */
public class SimpleIoc {

    /**
     * 加载Bean:
     *      1. 加载xml配置文件
     *      2. 获取到所有<bean>标签并遍历
     *      3. 利用反射 加载对应的class,并创建bean
     *      4. 遍历 所有的<property>标签，将属性值填充到相关字段中
     *      5. 将Bean注册到bean容器中
     */
    private void loadBeans(String localPath) throws Exception {
        FileInputStream inputStream = new FileInputStream(localPath);
        DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        Document document = documentBuilder.parse(inputStream);
        Element root = document.getDocumentElement();  //获取到文件根节点
        NodeList childNodes = root.getChildNodes();

        for (int i = 0; i < childNodes.getLength(); i++) {

        }
    }

}
