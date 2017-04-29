package com.blog.LHT.dao;

import java.util.List;

public interface BaseDao {

	public <T> void add(T entity);//增
	public <T> void delete(T entity);//删
	public <T> void update(T entity);//改
	public <T>List<T> loadAll(Class<T>entityClass);//查所有
	public <T>List<T>loadSpecAll(T entity);//查询某一个用户的所有
	public <T>T load(Class<T>entityClass,int id);//查一个
	public <T> List<T> findByHQL(String queryString,Object[]params);//根据hql查询
	public <T>List<T>findPage(Class<T> entityClass,int beginSize,int pageSize);       //分页查询
	public <T>List<T>findPage(T entity,int beginSize,int pageSize);  
}
