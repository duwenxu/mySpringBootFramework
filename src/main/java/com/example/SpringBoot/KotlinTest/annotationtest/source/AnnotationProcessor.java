package com.example.SpringBoot.KotlinTest.annotationtest.source;

import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;
import java.util.Set;

/**
 * 编译期注解处理器
 * 参照：https://blog.csdn.net/github_35180164/article/details/52121038
 * @author duwenxu
 * @create 2019-04-30 16:53
 */
@SupportedSourceVersion(SourceVersion.RELEASE_8)
@SupportedAnnotationTypes("com.example.SpringBoot.KotlinTest.annotationtest.source.Myannotations")
//@SupportedOptions()
class AnnotationProcessor extends AbstractProcessor {

    private Filer filer=null;        //创建文件
    private Types typeUtils=null;       //用来处理TypeMirror. 也就是一个类的父类。   TypeMirror superClassType = currentClass.getSuperclass();
    private Elements elementUtils=null;    //将要产生类中的所有元素抽象为Element元素
    private Messager messager=null;    // 来将一些错误信息打印到控制台上

    /**
     * 在整个编译期间仅仅被调用一次，作用就是初始化参数ProcessingEnvironment。
     * @param processingEnv
     */
    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        filer=processingEnv.getFiler();
        typeUtils=processingEnv.getTypeUtils();
        elementUtils=processingEnv.getElementUtils();
        messager=processingEnv.getMessager();
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        return false;
    }

    @Override
    public Set<String> getSupportedOptions() {
        return super.getSupportedOptions();
    }

//    /**
//     * 这里必须指定，这个注解处理器是注册给哪个注解的。注意，它的返回值是一个字符串的集合，包含本处理器想要处理的注解类型的合法全称
//     * @return  注解器所支持的注解类型集合，如果没有这样的类型，则返回一个空集合
//     */
//    @Override
//    public Set<String> getSupportedAnnotationTypes() {
//        Set<String> annotations=new LinkedHashSet();
//        /**
//         * 1、getCanonicalName() 是获取所传类从java语言规范定义的格式输出。
//         * 2、getName() 是返回实体类型名称
//         * 3、getSimpleName() 返回从源代码中返回实例的名称。
//         */
//        annotations.add(Myannotations.class.getCanonicalName());
//        return annotations;
//    }
//
//    /**
//     * 指定使用的Java版本，通常这里返回SourceVersion.latestSupported()，默认返回SourceVersion.RELEASE_6
//     * @return 使用的Java版本
//     */
//    @Override
//    public SourceVersion getSupportedSourceVersion() {
//        return SourceVersion.latestSupported();
//    }


}
