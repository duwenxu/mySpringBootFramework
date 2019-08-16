package com.example.SpringBoot.DataBase_CRUD.web;

import com.example.SpringBoot.DataBase_CRUD.Entity.User;
import com.example.SpringBoot.DataBase_CRUD.service.impl.UserServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class UserController {
    @Resource
    UserServiceImpl service;
//return "user/userEdit"; 代表会直接去resources目录下找相关的文件。
//return "redirect:/list"; 代表转发到对应的controller，这个示例就相当于删除内容之后自动调整到list请求，然后再输出到页面。

    /**
     * 首页
     *
     * @return
     */
    @RequestMapping("/")
    public String index() {
        return "redirect:/list";
    }

    /**
     * 展示所有
     *
     * @param model
     * @return
     */
    @RequestMapping("/list")
    public String list(Model model) {
        List<User> users = service.findList();
        model.addAttribute("users", users);
        return "user/list";
    }

    @RequestMapping("/toAdd")
    public String toAdd() {
        return "user/userAdd";
    }

    /**
     * 新增
     *
     * @param user
     * @return
     */
    @RequestMapping("/add")
    public String add(User user) {
        service.insert(user);
        return "redirect:/list";
    }

    @RequestMapping("/toEdit")
    public String update(Model model, int id) {
        User user = service.findUserById(id);
        model.addAttribute("user", user);
        return "user/userEdit";
    }

    /**
     * 更新
     *
     * @param user
     * @return
     */
    @RequestMapping("/edit")
    public String edit(User user) {
        service.updateByPrimaryKey(user);
        return "redirect:/list";
    }

    /**
     * 删除
     *
     * @param id
     * @return
     */
    @RequestMapping("/delete")
    public String delete(int id) {
        service.deleteByPrimaryKey(id);
        return "redirect:/list";
    }

}
