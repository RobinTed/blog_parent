package com.blog.LHT.service.impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.blog.LHT.dao.BaseDao;
import com.blog.LHT.entity.User;
import com.blog.LHT.service.UserService;
import com.blog.LHT.utils.Pager;

@Transactional
public class UserServiceImpl implements UserService {

	private BaseDao baseDao;
	
	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}

	@Override
	public void add(User user) {
		baseDao.add(user);
	}

	@Override
	public void delete(User user) {
		baseDao.delete(user);
	}

	@Override
	public void update(User user) {
		baseDao.update(user);
	}

	@Override
	public User load(int id) {
		return baseDao.load(User.class, id);
	}

	@Override
	public List<User> loadAll() {
		return baseDao.loadAll(User.class);
	}

	@Override
	public Pager<User> findByCondition(String query) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User loadByUsername(String username) {
		return null;
	}

	@Override
	public <T> List<T> findByHQL(String queryString, Object[] params) {
		return baseDao.findByHQL(queryString, params);
	}


}
