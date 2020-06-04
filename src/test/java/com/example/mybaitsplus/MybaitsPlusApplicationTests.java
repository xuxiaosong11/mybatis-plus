package com.example.mybaitsplus;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.mybaitsplus.dao.UserDao;
import com.example.mybaitsplus.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@SpringBootTest
class MybaitsPlusApplicationTests {

	@Autowired
	private UserDao userDao;

	@Test
	void contextLoads() {
		List<User> users = userDao.selectList(null);
		for (User  user: users) {
			System.out.println(user);
		}
 	}

 	@Test
	void testUser() {
		User user=new User();
		user.setAge(15);
		user.setEmail("xxsdxmd@163.com");
		user.setName("jwt");
		int insert = userDao.insert(user);
		System.out.println(insert);
	}

	@Test
	void testUpdate() {
		User user=new User();
		user.setId(6);
		user.setName("xf");
		int i = userDao.updateById(user);
		System.out.println(i);
		System.out.println(user);
	}

	@Test
	void testOptimisiLock() {
		User user = userDao.selectById(1);
		user.setName("zhangsan");
		user.setAge(11);
		int i = userDao.updateById(user);
		System.out.println(i);
	}

	@Test
	void testOptimisiticLock() {
		User user=userDao.selectById(1);
		user.setAge(17);
		user.setName("xs");

		User user1=userDao.selectById(1);
		user1.setName("vae");
		user1.setAge(18);
        userDao.updateById(user1);
		int i = userDao.updateById(user);
		System.out.println(i);
	}
    @Test
	void testSelectById() {
		User user = userDao.selectById(1);
		System.out.println(user);
	}

	@Test
	void testSelectByBatchId() {
		List<User> users = userDao.selectBatchIds(Arrays.asList(1, 2, 3));
		users.forEach(System.out::println);
	}

	@Test
	void testSelectByBatchIds() {
		HashMap<String,Object> map=new HashMap<>();
		map.put("name","xf");
		map.put("age",18);
		List<User> users = userDao.selectByMap(map);
		users.forEach(System.out::println);
	}

	@Test
	void testPage() {
		Page<User> page=new Page<>(1,5);
		userDao.selectPage(page,null);
		page.getRecords().forEach(System.out::println);
		System.out.println(page.getTotal());

	}

	@Test
	void testWrapper() {
		/**
		 * 查询name 不为空 age 不为空 年龄大于 12 的所有用户
		 */
		QueryWrapper<User> queryWrapper=new QueryWrapper<>();
		queryWrapper
				.isNotNull("name")
				.isNotNull("age")
				.ge("age",12);
		List<User> users = userDao.selectList(queryWrapper);
		users.forEach(System.out::println);
	}

	@Test
	void testWrapper2() {
		/*
		 *查询name为vae 的用户
		 */
		QueryWrapper<User> queryWrapper=new QueryWrapper<>();
		queryWrapper.eq("name","vae");
		User user = userDao.selectOne(queryWrapper);
		System.out.println(user);
	}

	@Test
	void testWrapper3() {
		/*
		   查询年龄在 20 到 30 之间的用户
		 */
		QueryWrapper<User> queryWrapper=new QueryWrapper<>();
		queryWrapper.between("age",20,30);
		List<User> users = userDao.selectList(queryWrapper);
		for (User user:users) {
			System.out.println(user);
		}
	}

	@Test
	void test2() {
		QueryWrapper<User> queryWrapper=new QueryWrapper<>();
		queryWrapper.inSql("id","select id from user where id <3");
		List<Object> objects = userDao.selectObjs(queryWrapper);
		for (Object o:objects) {
			System.out.println(o);
		}
	}

	@Test
	void test3() {
		QueryWrapper<User> queryWrapper=new QueryWrapper<>();
		queryWrapper.orderByDesc("id");
		List<User> users = userDao.selectList(queryWrapper);
		users.forEach(System.out::println);
	}
}
