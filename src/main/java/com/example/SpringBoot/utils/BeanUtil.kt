package com.waytogalaxy.display.utils

import org.apache.commons.beanutils.BeanUtils
import org.apache.commons.lang3.StringUtils
import org.springframework.util.Assert

import java.beans.Introspector
import java.lang.reflect.Field
import java.lang.reflect.InvocationTargetException
import java.lang.reflect.Method
import java.lang.reflect.Modifier
import java.util.HashMap
import java.util.ArrayList
import kotlin.reflect.KClass


/**
 * 利用反射进行操作的一个工具类
 *
 * @author licho
 */
object BeanUtil {
    /**
     * 利用反射获取指定对象的指定属性
     *
     * @param obj       目标对象
     * @param fieldName 目标属性
     * @return 目标属性的值
     */
    fun getFieldValue(obj: Any, fieldName: String): Any? {
        var result: Any? = null
        val field = BeanUtil.getField(obj, fieldName)
        if (field != null) {
            field.isAccessible = true
            try {
                result = field.get(obj)
            } catch (e: IllegalArgumentException) {
                e.printStackTrace()
            } catch (e: IllegalAccessException) {
                e.printStackTrace()
            }

        }
        return result
    }

    /**
     * 利用反射获取指定对象里面的指定属性
     *
     * @param obj       目标对象
     * @param fieldName 目标属性
     * @return 目标字段
     */
    private fun getField(obj: Any, fieldName: String): Field? {
        var field: Field? = null
        var clazz: Class<*> = obj.javaClass
        while (clazz != Any::class.java) {
            try {
                field = clazz.getDeclaredField(fieldName)
                break
            } catch (e: NoSuchFieldException) {
                // 这里不用做处理，子类没有该字段可能对应的父类有，都没有就返回null。
            }

            clazz = clazz
                    .superclass
        }
        return field
    }

    /**
     * 利用反射设置指定对象的指定属性为指定的值
     *
     * @param obj        目标对象
     * @param fieldName  目标属性
     * @param fieldValue 目标值
     */
    fun setFieldValue(obj: Any, fieldName: String,
                      fieldValue: String) {
        val field = BeanUtil.getField(obj, fieldName)
        if (field != null) {
            try {
                field.isAccessible = true
                field.set(obj, fieldValue)
            } catch (e: IllegalArgumentException) {
                e.printStackTrace()
            } catch (e: IllegalAccessException) {
                e.printStackTrace()
            }

        }
    }

    /**
     * 两者属性名一致时，拷贝source里的属性到dest里
     * @param dest   目标对象
     * @param source 源对象
     * @throws IllegalAccessException    非法进入异常
     * @throws IllegalArgumentException  非法参数异常
     * @throws InvocationTargetException 非法调用异常
     */
    @Throws(IllegalAccessException::class, IllegalArgumentException::class, InvocationTargetException::class)
    fun copyPorperties(dest: Any, source: Any) {
        val srcCla = source.javaClass
        val fsF = srcCla.declaredFields

        for (s in fsF) {
            val name = s.name
            val srcObj = invokeGetterMethod(source, name)
            try {
                BeanUtils.setProperty(dest, name, srcObj)
            } catch (e: Exception) {
                e.printStackTrace()
            }

        }
    }

    /**
     * 调用Getter方法.
     *
     * @param target       目标对象
     * @param propertyName 属性名称
     * @return 属性get方法调用结果
     * @throws IllegalAccessException    非法进入异常
     * @throws IllegalArgumentException  非法参数异常
     * @throws InvocationTargetException 非法调用异常
     */
    @Throws(IllegalAccessException::class, IllegalArgumentException::class, InvocationTargetException::class)
    fun invokeGetterMethod(target: Any, propertyName: String): Any {
        val getterMethodName = "get" + StringUtils.capitalize(propertyName)
        return invokeMethod(target, getterMethodName, arrayOf(),
                arrayOf())
    }

    /**
     * 直接调用对象方法, 无视private/protected修饰符.
     *
     * @param object         目标对象
     * @param methodName     方法名称
     * @param parameterTypes 参数类型
     * @param parameters     参数
     * @return 方法调用结果
     * @throws IllegalAccessException    非法进入异常
     * @throws IllegalArgumentException  非法参数异常
     * @throws InvocationTargetException 非法调用异常
     */
    @Throws(IllegalAccessException::class, IllegalArgumentException::class, InvocationTargetException::class)
    fun invokeMethod(`object`: Any,
                     methodName: String, parameterTypes: Array<Class<*>>,
                     parameters: Array<Any>): Any {
        val method = getDeclaredMethod(`object`, methodName, parameterTypes) ?: throw IllegalArgumentException("Could not find method ["
                + methodName + "] parameterType " + parameterTypes
                + " on target [" + `object` + "]")

        method.isAccessible = true
        return method.invoke(`object`, *parameters)
    }

    /**
     * 循环向上转型, 获取对象的DeclaredMethod.
     * 如向上转型到Object仍无法找到, 返回null.
     *
     * @param object         目标对象
     * @param methodName     方法名称
     * @param parameterTypes 参数类型
     * @return 目标对象的DeclaredMethod
     */
    internal fun getDeclaredMethod(`object`: Any, methodName: String,
                                   parameterTypes: Array<Class<*>>): Method? {
        Assert.notNull(`object`, "object不能为空")

        var superClass: Class<*> = `object`.javaClass
        while (superClass != Any::class.java) {
            try {
                return superClass.getDeclaredMethod(methodName, *parameterTypes)
            } catch (e: NoSuchMethodException) {    // NOSONAR
                // Method不在当前类定义,继续向上转型
            }

            superClass = superClass
                    .superclass
        }
        return null
    }

    /**
     * 根据指定注解获得属性值
     *
     * @param bean            目标bean
     * @param annotationClass 注解Class
     * @return 属性值
     */
    fun getFiledValueByAnnotation(bean: Any, annotationClass: Class<out Annotation>): String? {
        val beanClass = bean.javaClass
        val fields = beanClass.declaredFields
        for (field in fields) {
            if (field.isAnnotationPresent(annotationClass)) {
                try {
                    return BeanUtils.getProperty(bean, field.name)
                } catch (e: IllegalAccessException) {
                    e.printStackTrace()
                } catch (e: InvocationTargetException) {
                    e.printStackTrace()
                } catch (e: NoSuchMethodException) {
                    e.printStackTrace()
                }

            }
        }
        return null
    }

    /**
     * 根据指定注解设置属性值
     *
     * @param bean            目标bean
     * @param annotationClass 注解Class
     * @param value           值
     */
    fun setFiledValueByAnnotation(bean: Any, annotationClass: Class<out Annotation>, value: Any) {
        val beanClass = bean.javaClass
        val fields = beanClass.declaredFields
        for (field in fields) {
            if (field.isAnnotationPresent(annotationClass)) {
                try {
                    BeanUtils.setProperty(bean, field.name, value)
                } catch (e: IllegalAccessException) {
                    e.printStackTrace()
                } catch (e: InvocationTargetException) {
                    e.printStackTrace()
                }

            }
        }
    }

    /**
     * Bean转Map
     *
     * @param obj Bean
     * @return Map
     */
    fun transBean2Map(obj: Any?): Map<String, Any>? {
        if (obj == null) {
            return null
        }
        val map = HashMap<String, Any>()
        try {
            val beanInfo = Introspector.getBeanInfo(obj.javaClass)
            val propertyDescriptors = beanInfo.propertyDescriptors
            for (property in propertyDescriptors) {
                val key = property.name

                // 过滤class属性
                if (key != "class") {
                    // 得到property对应的getter方法
                    val getter = property.readMethod
                    val value = getter.invoke(obj)

                    map.put(key, value)
                }

            }
        } catch (e: Exception) {
            println("transBean2Map Error " + e)
        }

        return map

    }

    /**
     * Map转Bean
     *
     * @param map map
     * @param obj Bean
     */
    fun <T : Any> transMap2Bean(map: Map<String, Any?>, clazz: KClass<T>): T? {
        val obj = clazz.objectInstance ?: return null
        //获取关联的所有类，本类以及所有父类
        val ret = true
        var oo: Class<*> = obj.javaClass
        val clazzes = ArrayList<Class<*>>()
        while (ret) {
            clazzes.add(oo)
            oo = oo.superclass
            if (oo == null || oo == Any::class.java) break
        }

        for (i in clazzes.indices) {
            val fields = clazzes[i].declaredFields
            for (field in fields) {
                val mod = field!!.modifiers
                if (Modifier.isStatic(mod) || Modifier.isFinal(mod)) {
                    continue
                }
                //由字符串转换回对象对应的类型
                field.isAccessible = true
                field.set(obj, map[field.name])
            }
        }
        return obj
    }

    /**
     * 检查对象所有属性是否为空
     *
     * @param obj          对象
     * @param excludeField 排除检查的字段
     * @return 全部为空返回 true 只要有不为空则为false
     */
    fun checkBeanAllFiledIsNull(obj: Any?, vararg excludeField: String): Boolean {
        if (obj == null) {
            return true
        }
        for (field in obj.javaClass.declaredFields) {
            field.isAccessible = true
            try {
                return if (field.get(obj) == null || isInFields(field, *excludeField)) { //判断字段是否为空，并且对象属性中的基本都会转为对象类型来判断
                    continue
                } else {
                    false
                }
            } catch (e: IllegalAccessException) {
                e.printStackTrace()
            }

        }
        return true
    }

    /**
     * 是否为需要排除的属性
     *
     * @param field      属性
     * @param FieldNames 排除的属性名
     * @return
     */
    fun isInFields(field: Field, vararg FieldNames: String): Boolean {
        if (FieldNames.size == 0) {
            return false
        }
        for (filedName in FieldNames) {
            return field.name == filedName
        }
        return false
    }

    /**
     * 对象复制转换
     *
     * @param obj   转换源对象
     * @param toObj 转换类型
     * @param <T>
     * @return
     * @throws Exception
    </T> */
    @Throws(Exception::class)
    fun <T> objSimpleConvert(obj: Any, toObj: Class<T>): T {
        val toObjIns = toObj.newInstance() //创建目标对象实例
        var sourCls: Class<*> = obj.javaClass
        //遍历源属性
        do {
            val sourFields = sourCls.declaredFields //源属性集
            for (i in sourFields.indices) { //遍历源所有属性
                val sf = sourFields[i]
                sf.isAccessible = true
                //遍历目标所有属性
                var toCls: Class<*> = toObj
                do {
                    val fields = toCls.declaredFields //源属性集
                    for (j in fields.indices) { //遍历源所有属性
                        val tof = fields[j]
                        tof.isAccessible = true
                        if (sf.name == tof.name) { //属性名字相同
                            val type = tof.type.toString()//得到此属性的类型
                            if (type.endsWith("String")) {
                                tof.set(toObjIns, sf.get(obj))
                            } else if (type.endsWith("int") || type.endsWith("Integer")) {
                                tof.set(toObjIns, sf.get(obj))
                            } else if (type.endsWith("Date")) {
                                tof.set(toObjIns, sf.get(obj))
                            } else if (type.endsWith("long") || type.endsWith("Long")) {
                                tof.set(toObjIns, sf.get(obj))
                            } else if (type.endsWith("short") || type.endsWith("Short")) {
                                tof.set(toObjIns, sf.get(obj))
                            } else {
                                throw Exception("类型转换失败！")
                            }
                        }
                    }
                    toCls = toCls.superclass

                } while (toCls != Any::class.java)
            }
            sourCls = sourCls.superclass
        } while (sourCls != Any::class.java)
        return toObjIns
    }


}

