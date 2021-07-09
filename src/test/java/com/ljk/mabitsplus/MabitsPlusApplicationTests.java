package com.ljk.mabitsplus;

import com.baomidou.mybatisplus.core.conditions.query.Query;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.ljk.mapper.UserMapper;
import com.ljk.pojo.User;
import netscape.security.UserTarget;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.Serializable;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
class MabitsPlusApplicationTests {

    @Test
    void contextLoads() {

    }

    @Autowired
    private UserMapper userMapper;

    @Test
    public void test1() {
        System.out.println("========================");
        List<User> list = userMapper.selectList(null);
        System.out.println(list);
    }

    @Test
    public void test2() {
        System.out.println("==========================");
        User user = userMapper.selectById(1L);
        System.out.println(user);
    }

    @Test
    public void test3() {
        System.out.println("===========================");
        List<User> users = userMapper.selectBatchIds(Arrays.asList(1L, 2L));
        users.forEach(System.out::println);
    }

    @Test
    public void test4() {
        System.out.println("==========================");
        Map<String, Object> map = new HashMap<>();
//      key为表中的字段名
        map.put("username", "xiaohu");
        map.put("password", "123456");

        List<User> users = userMapper.selectByMap(map);

        users.forEach(System.out::println);
    }

    @Test
    public void test5() {
        System.out.println("=================================");
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();

//        queryWrapper.like("username", "xiao").lt("age", "30");
//        queryWrapper.like("username", "xiao").gt("age", "10").isNull("email");
        queryWrapper.likeRight("username", "xiao").or().le("age", "23`").orderByAsc("age");
        List<User> users = userMapper.selectList(queryWrapper);
        users.forEach(System.out::println);
    }

    @Test
    public void test6() {

        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();

        updateWrapper.eq("username", "xiaohu").eq("age", 22).set("age", 21);

        userMapper.update(null, updateWrapper);

        System.out.println(userMapper.selectList(null));


    }
}
