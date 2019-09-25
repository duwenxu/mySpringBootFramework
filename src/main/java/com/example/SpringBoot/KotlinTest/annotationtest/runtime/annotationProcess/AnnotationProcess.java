package com.example.springboot.kotlintest.annotationtest.runtime.annotationProcess;


import com.example.springboot.kotlintest.annotationtest.runtime.annotations.Contraints;
import com.example.springboot.kotlintest.annotationtest.runtime.annotations.DBtable;
import com.example.springboot.kotlintest.annotationtest.runtime.annotations.SqlInteger;
import com.example.springboot.kotlintest.annotationtest.runtime.annotations.SqlString;

import javax.annotation.processing.SupportedAnnotationTypes;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * 自定义注解解析器  （运行时注解）
 *  原理：使用反射获取运行时注解并执行相应操作
 *  此处：构建表创建语句
 * @// FIXME: 2019/4/29 如何关联解析器与注解  @SupportedAnnotationTypes注解
 * @author duwenxu
 * @create 2019-04-29 8:33
 */

@SupportedAnnotationTypes("com.example.SpringBoot.KotlinTest.annotationtest.runtime.annotations.DBtable")
class AnnotationProcess {

    public static String createSQL(String className) throws ClassNotFoundException {
        Class<?> clazz = Class.forName(className);
        DBtable dBtable = clazz.getAnnotation(DBtable.class); //获取该注解名的class
        if (dBtable==null){
            System.out.println("The method descripted with DBtable not exist");
            return null;
        }
        String tableName = dBtable.name();
        if (tableName.length()<1){
           tableName=clazz.getName().toUpperCase();
        }
        List<String> SQLlist = new ArrayList<>();
        //遍历类型反射获取的所有字段
        for (Field field:clazz.getDeclaredFields()) {
            String fieldName=null;
            Annotation[] annos = field.getDeclaredAnnotations();
            if (annos.length<1){  //该字段上没有注解
               continue;
           }
           if (annos[0] instanceof SqlInteger){
               SqlInteger intField = (SqlInteger) annos[0];
                if (intField.name().length()<1){
                    fieldName=field.getName().toUpperCase();
                }else {
                    fieldName=intField.name();
                }
               SQLlist.add(fieldName+" int"+getConstraints(intField.contraints()));
           }
            if (annos[0] instanceof SqlString){
                SqlString strField = (SqlString) annos[0];
                if (strField.name().length()<1){
                    fieldName=field.getName().toUpperCase();
                }else {
                    fieldName=strField.name();
                }
                SQLlist.add(fieldName+" varchar("+strField.length()+")"+getConstraints(strField.contraints()));
            }
        }
       StringBuilder newCreateSQL=new StringBuilder("create table "+tableName+"(");
        for (String fieldSQL:SQLlist) {
            newCreateSQL.append("\n "+fieldSQL+",");
        }
        return newCreateSQL.append(");").toString();
    }


    /**
     * 拼接SQL
     * @param con
     * @return
     */
    private static String getConstraints(Contraints con){
        String constraints="";
        if (!con.allowNull())
            constraints+=" NOT NULL";
        if (con.isPrimaryKey())
            constraints+="PRIMARY KEY";
        if (con.isUnique())
            constraints+=" UNIQUE";
        return constraints;
    }
}
