//package com.blog.LHT.dao.abandon;
//
//import java.lang.reflect.ParameterizedType;
//import java.lang.reflect.Type;
//import java.util.List;
//
//import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
//import org.springframework.stereotype.Repository;
//
//import com.blog.LHT.utils.Pager;
//
//@Repository
//public class BaseDaoImpl<T> extends HibernateDaoSupport implements BaseDao<T>{
//
//	private Class<?> clazz;
//	public BaseDaoImpl() {
//		ParameterizedType type = (ParameterizedType) this.getClass().getGenericSuperclass();
//		Type[] types = type.getActualTypeArguments();
//		Class<?> clazz = (Class<?>) types[0];
//		this.clazz = clazz;
//	}
//	
//	@Override
//	public void add(T t) {
//		this.getHibernateTemplate().save(t);
//	}
//
//	@Override
//	public void delete(T t) {
//		this.getHibernateTemplate().delete(t);
//	}
//
//	@Override
//	public void update(T t) {
//		this.getHibernateTemplate().update(t);
//	}
//
//	@SuppressWarnings("unchecked")
//	@Override
//	public T load(int id) {
//		return (T) this.getHibernateTemplate().get(clazz, id);
//	}
//
//	@Override
//	public Pager<T> findByCondition(String query) {
//		//暂时不写此方法，后面需要再写
//		return null;
//	}
//
//	@SuppressWarnings("unchecked")
//	@Override
//	public List<T> loadAll() {
//		return (List<T>) this.getHibernateTemplate().loadAll(clazz);
//	}
//
//}
