package com.example.SpringBoot;

import com.example.springboot.jpa.web.HelloWorldController;
import com.example.springboot.jpa.repository.UserDao;
import com.example.springboot.springsecurity.model.rbac_model.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class myTest {
    private MockMvc mvc;

    @Before
    public void setUp() throws Exception {
        mvc = MockMvcBuilders.standaloneSetup(new HelloWorldController()).build();
    }

    @Test
    public void getHello() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/hello").accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Autowired
    private UserDao myDao;

    @Test
    public void dataJpaTest() {
        myDao.save(new User("令狐冲", 13, "上海"));
        myDao.save(new User("岳不群", 22, "上海"));
        myDao.save(new User("狄云", 12, "青海"));
        myDao.save(new User("虚竹", 22, "灵鹫宫"));
        myDao.save(new User("天山童姥", 66, "灵鹫宫"));
        myDao.save(new User("无崖子", 70, "灵鹫宫"));

        final List<User> userlist = myDao.findAllByAddress("上海");
        System.out.println(userlist.toString());
    }

    //分页查询     Pageable 是spring封装的分页实现类，使用的时候需要传入页数、每页条数和排序规则
    @Test
    public void testPageQuery() throws Exception {
        int page = 1, size = 10;
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        Pageable pageable = PageRequest.of(0, 10, sort);
        myDao.findByName("虚竹", pageable);
        myDao.findAll(pageable);
        System.out.println(myDao.findAll(pageable).toString());
    }

    @Test
    public void updateTest() throws Exception{
        myDao.updateByPrimaryKey("周杰伦", 34, "台湾",28 );
        myDao.findById(23);
    }

//    @Autowired
//    KafkaProducerTest kafkaProducer;
//
//    /**
//     * kafka生产消费测试
//     */
//    @Test
//    public void kafkaTest(){
//        for (int i=0;i<100;i++){
//            System.out.println("send message="+i);
//            kafkaProducer.sendMessage(i+"");
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//    }
}
