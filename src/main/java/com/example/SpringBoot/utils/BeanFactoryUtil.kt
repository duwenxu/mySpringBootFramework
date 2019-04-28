package com.waytogalaxy.display.utils

import org.springframework.beans.BeansException
import org.springframework.beans.factory.NoSuchBeanDefinitionException
import org.springframework.beans.factory.config.BeanFactoryPostProcessor
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory
import org.springframework.stereotype.Component

@Component
object BeanFactoryUtil : BeanFactoryPostProcessor {

    lateinit var beanFactory: ConfigurableListableBeanFactory

    override fun postProcessBeanFactory(beanFactory: ConfigurableListableBeanFactory) {
        this.beanFactory = beanFactory
    }

    /**
     * 获取bean实例对象
     *
     * @param name bean名称
     * @param <T>  泛型类
     * @return Object 一个以所给名字注册的bean的实例
     * @throws BeansException 获取bean失败异常
    </T> */
    @Throws(BeansException::class)
    fun <T> getBean(name: String): Any {
        return beanFactory.getBean(name)
    }

    /**
     * 获取类型为requiredType的对象
     *
     * @param classZ 类类型
     * @param <T>    泛型类
     * @return classZ类型bean实例
     * @throws BeansException 获取bean失败异常
    </T> */
    @Throws(BeansException::class)
    fun <T> getBean(classZ: Class<T>): T {
        return beanFactory.getBean(classZ)
    }

    /**
     * 获取类型为classZ的对象
     *
     * @param classZ 类型
     * @param args 参数
     * @param <T> 类型
     * @return 对象
    </T> */
    fun <T> getBean(classZ: Class<T>, vararg args: Any): T {
        return beanFactory.getBean(classZ, args)
    }

    /**
     * 如果BeanFactory包含一个与所给名称匹配的bean定义，则返回true
     *
     * @param name bean实体名称
     * @return true存在bean，false不存在bean
     */
    fun containsBean(name: String): Boolean {
        return beanFactory.containsBean(name)
    }

    /**
     * 判断以给定名字注册的bean定义是一个singleton还是一个prototype。 如果与给定名字相应的bean定义没有被找到，将会抛出一个异常（NoSuchBeanDefinitionException）
     *
     * @param name bena名称
     * @return true是单例，false不是单例
     * @throws NoSuchBeanDefinitionException bean未定义异常
     */
    @Throws(NoSuchBeanDefinitionException::class)
    fun isSingleton(name: String): Boolean {
        return beanFactory.isSingleton(name)
    }

    /**
     * @param name bean名称
     * @return Class 注册对象的类型
     * @throws NoSuchBeanDefinitionException bean未定义异常
     */
    @Throws(NoSuchBeanDefinitionException::class)
    fun getType(name: String): Class<*>? {
        return beanFactory.getType(name)
    }

    /**
     * 如果给定的bean名字在bean定义中有别名，则返回这些别名
     *
     * @param name bean名称
     * @return bean别名列表
     * @throws NoSuchBeanDefinitionException bean未定义异常
     */
    @Throws(NoSuchBeanDefinitionException::class)
    fun getAliases(name: String): Array<String> {
        return beanFactory.getAliases(name)
    }

    /**
     * 根据注解获得Bean
     *
     * @return bean别名列表
     * @throws NoSuchBeanDefinitionException bean未定义异常
     */
    @Throws(NoSuchBeanDefinitionException::class)
    fun getBeansWithAnnotation(annotationType: Class<out Annotation>): Map<String, Any> {
        return beanFactory.getBeansWithAnnotation(annotationType)
    }

    /**
     * 根据父类获得Bean
     *
     * @return bean别名列表
     * @throws NoSuchBeanDefinitionException bean未定义异常
     */
    @Throws(NoSuchBeanDefinitionException::class)
    fun <T> getBeansOfType(bean: Class<T>): Map<String, T> {
        return beanFactory.getBeansOfType(bean)
    }

}