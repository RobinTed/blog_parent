package com.blog.LHT.dao.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.blog.LHT.dao.BaseDao;

public class BaseDaoImpl extends HibernateDaoSupport implements BaseDao {

	@Override
	public <T> void add(T entity) {
		this.getHibernateTemplate().save(entity);
	}

	@Override
	public <T> void delete(T entity) {
		this.getHibernateTemplate().delete(entity);
	}

	@Override
	public <T> void update(T  entity) {
		this.getHibernateTemplate().saveOrUpdate(entity);;
	}

	@Override
	public <T> List<T> loadAll(Class<T>entityClass) {
		return this.getHibernateTemplate().loadAll(entityClass);
	}

	@Override
	public <T> T load(Class<T> entityClass, int id) {
		return this.getHibernateTemplate().get(entityClass, id);
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> findByHQL(String queryString, Object[] params) {
		return (List<T>) this.getHibernateTemplate().find(queryString, params);
	}

	/**
	 * 查找某个用户的分页
	 */
	@Override
	public <T> List<T> findPage(T entity,int beginSize, int pageSize) {
		List<T> list = this.getHibernateTemplate().findByExample(entity, beginSize, pageSize);
		return list;
	}

	/**
	 * 查找所有的分页
	 */
	@Override
	public <T> List<T> findPage(Class<T> entityClass, int beginSize, int pageSize) {
		DetachedCriteria criteria = DetachedCriteria.forClass(entityClass);
		@SuppressWarnings("unchecked")
		List<T>list = (List<T>) this.getHibernateTemplate().findByCriteria(criteria, beginSize, pageSize);
		return list;
	}

	@Override
	public <T> List<T> loadSpecAll(T entity) {
		List<T> list = this.getHibernateTemplate().findByExample(entity);
		return list;
	}

}
