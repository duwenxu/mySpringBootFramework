package com.example.SpringBoot.web;

import com.example.SpringBoot.Entity.ConfigIDEABean;
import com.example.SpringBoot.Entity.User;
import com.example.SpringBoot.repository.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * spring boot web开发非常的简单，其中包括常用的json输出、filters、property、log等
 */
//添加@RestController注释，默认类中的方法都会以json的格式返回
@RestController
public class HelloWorldController {
    /**
     *
     */
    @Autowired
    UserDao mydao;

    //application.properties的自定义属性
    @Value("${com.test.name}")
    private String name;
    @Value("${com.test.value}")
    private String value;
    @Value("${com.mytest.title}")
    private String logintitle;
    //引入自定义属性绑定的Bean对象
    @Autowired
    ConfigIDEABean configIDEABean;

    @RequestMapping("/getUser")
    public User getUser() {
        User user = new User();
        return user;
    }

    @RequestMapping("/index")   //表示直接在  http://localhost:8080/
    public String init() {
        return mydao.findAllByAddress("上海").toString();

    }

    @RequestMapping("/dataJpaTest")  //在IDEA中快速输出打印语句：sout
    public void dataJpaTest() {
        //System.out.println(mydao.findAllByAddress("上海"));
        System.out.println(mydao.findAll());
        //System.out.println(mydao.findByAddressIsLikeAndNameEqualsAndAgeBetween("%灵鹫%", "虚竹",18 , 25));
        //mydao.save(new User("张无忌", 24, "光明顶"));
        //mydao.deleteUserByName("虚竹");
        //mydao.updateUserByNameAndAddress("李秋水", "灵鹫宫");
      //  return mydao.findAll().toString();
    }

    //分页查询测试
    @RequestMapping("/pageQuery")
    public String pageQuery(){
        int pagheNo=1,pageSize=10;
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        Pageable pagable = new PageRequest(pagheNo, pageSize);
        return mydao.findAll(pagable).toString()+"/t"+mydao.findByName("张无忌", pagable);
    }

    @RequestMapping("/PropertiesBeanTest")
    public String test() {
        return configIDEABean.getName() + configIDEABean.getValue();
    }


    @RequestMapping("/hello")//http://localhost:8080/hello
    public String hello(Model model, @RequestParam(value = "name",required = false,defaultValue = "World")String name) {
        model.addAttribute("name",name );
        return "你好 idea";
    }
}
