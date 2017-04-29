package com.blog.LHT.action.interceptor;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

public class LoginInterceptor extends MethodFilterInterceptor{

	private static final long serialVersionUID = 1L;

	@Override
	protected String doIntercept(ActionInvocation invocation) throws Exception {
		Object loginUser = ServletActionContext.getRequest().getSession().getAttribute("loginUser");
		if(loginUser != null){
			return invocation.invoke();    //放行
		}else{
			return "loginPage";        //返回配置标签登录页面
		}
	}

}
