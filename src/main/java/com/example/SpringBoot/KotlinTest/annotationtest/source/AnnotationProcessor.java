package com.example.springboot.kotlintest.annotationtest.source;


import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;
import java.util.LinkedHashSet;
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

    //创建文件
    private Filer filer=null;
    //用来处理TypeMirror. 也就是一个类的父类。   TypeMirror superClassType = currentClass.getSuperclass();
    private Types typeUtils=null;
    //将要产生类中的所有元素抽象为Element元素
    private Elements elementUtils=null;
    // 来将一些错误信息打印到控制台上
    private Messager messager=null;

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

    /**
     * ProcessingEnvironment是包含了注解处理器相关的工具类和编译器配置的参数
     * RoundEnvironment则是指在每一轮的扫描和处理源代码中获取被注解的Element
     */

    /**
     * 核心注解处理器
     * 这相当于每个处理器的主函数main()，你在这里写你的扫描、评估和处理注解的代码，以及生成Java文件。
     * 输入参数RoundEnviroment，可以让你查询出包含特定注解的被注解元素
     * @param annotations  请求处理的注解类型
     * @param roundEnv  当前的信息环境
     * @return       如果返回 true，则这些注解已声明并且不要求后续 Processor 处理它们；
     *      *        如果返回 false，则这些注解未声明并且可能要求后续 Processor 处理它们
     */
    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        return false;
    }

    @Override
    public Set<String> getSupportedOptions() {
        return super.getSupportedOptions();
    }

    /**
     * 这里必须指定，这个注解处理器是注册给哪个注解的。注意，它的返回值是一个字符串的集合，包含本处理器想要处理的注解类型的合法全称
     * @return  注解器所支持的注解类型集合，如果没有这样的类型，则返回一个空集合
     */
    @Override
    public Set<String> getSupportedAnnotationTypes() {
        Set<String> annotations=new LinkedHashSet();
        /**
         * 1、getCanonicalName() 是获取所传类从java语言规范定义的格式输出。
         * 2、getName() 是返回实体类型名称
         * 3、getSimpleName() 返回从源代码中返回实例的名称。
         */
        annotations.add(Myannotations.class.getCanonicalName());
        return annotations;
    }

    /**
     * 指定使用的Java版本，通常这里返回SourceVersion.latestSupported()，默认返回SourceVersion.RELEASE_6
     * @return 使用的Java版本
     */
    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }


}
