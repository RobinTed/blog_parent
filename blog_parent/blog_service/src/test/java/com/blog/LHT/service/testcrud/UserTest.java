package com.blog.LHT.service.testcrud;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.blog.LHT.entity.User;
import com.blog.LHT.service.UserService;

public class UserTest {

	private UserService service;
	private User user;
	
	@Before
	public void loadContext(){
		@SuppressWarnings("resource")
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext-service.xml");
		service =  (UserService) context.getBean("userServiceImpl");
		user = new User();
	}
	
	@Test
	public void testAdd(){
//		user.setRegiTime(LocalDateTime.now());
//		System.out.println(user);
//		service.add(user);
//		System.out.println(user.ge);
//		Timestamp timestamp = new Timestamp(new Date().getTime());
//		System.out.println(timestamp);
		Timestamp timestamp = new Timestamp(new Date().getTime());
		System.out.println(timestamp.getHours());
//		user.setRegiTime(new Timestamp(new Date().getTime()));
//		service.add(user);
	}
	
	@Test
	public void testHQL(){
//		String[]params = {user.getUserName()};
//		String queryString = "from User where userName=?";
//		List<User>users = service.findByHQL(queryString, params);
		String queryString = "from User where userName=? and password=?";
		String[] params = {user.getUserName(),user.getPassword()};
		List<User>users = service.findByHQL(queryString, params);
		System.out.println(users);
		System.out.println(users.size());
		for(User user:users){
			System.out.println(user.toString());
		}
	}
	
	@Test
	public void testUpdate(){
		user.setUserId(12);
		user.setUserName("Milla");
		user.setRegiTime(new Timestamp(new Date().getTime()));
		user.setPermiss(1);
		user.setBirth(new Timestamp(new Date(102, 11, 12).getTime()));
		service.update(user);
	}
	
	@Test
	public void testTimestamp(){
		Timestamp timestamp = new Timestamp(2002, 12, 12, 12, 12, 12, 12);
		System.out.println(timestamp.compareTo(new Timestamp(new Date().getTime())));
	}
	
	@Test
	public void testTimeForm(){
		
		System.out.println(LocalDateTime.now().compareTo(LocalDateTime.of(1999, 12, 23, 12, 12)));
	}
	
}
