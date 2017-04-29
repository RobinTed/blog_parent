package com.blog.LHT.service;

import java.util.List;

import com.blog.LHT.entity.User;
import com.blog.LHT.utils.Pager;


public interface UserService {

	//基本的增删改查
		public void add(User user);
		public void delete(User user);
		public void update(User user);
		public User load(int id);
		public List<User>loadAll();
		public User loadByUsername(String username);
		public Pager<User> findByCondition(String query);
		public <T> List<T> findByHQL(String queryString,Object[]params);//根据hql查询
	
}
