package com.blog.LHT.action.login;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.mail.MessagingException;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.blog.LHT.entity.User;
import com.blog.LHT.service.UserService;
import com.blog.LHT.utils.MD5Util;
import com.blog.LHT.utils.MailUtil;
import com.blog.LHT.utils.ResourceRead;
import com.blog.LHT.utils.VerifyCode;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import net.sf.json.JSONObject;

public class LoginAction extends ActionSupport implements ModelDriven<User>{

	private static final long serialVersionUID = 1L;
	private User user = new User();
	private String inputcode;
	private UserService userService;
	private int id;
	private String activationCode;
	
	
	public void setId(int id) {
		this.id = id;
	}

	public void setActivationCode(String activationCode) {
		this.activationCode = activationCode;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public void setInputcode(String inputcode) {
		this.inputcode = inputcode;
	}

	public String registerPage(){
		return "registerPage";
	}
	
	//验证验证码是否正确的方法
	public String checkCode(){
		String code = (String) ServletActionContext.getRequest().getSession().getAttribute("verifyCode");
		try {
			if(inputcode.equalsIgnoreCase(code)){
				ServletActionContext.getResponse().getWriter().println("1");
			}else{
				ServletActionContext.getResponse().getWriter().println("0");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return NONE;
	}
	
	public String register(){
		/*************放弃了这种后台校验的方法***********/
		//验证验证码是否正确
//		ServletActionContext.getRequest().removeAttribute("wrongCode");
//		ServletActionContext.getRequest().setAttribute("user", user);
//		String code = (String) ServletActionContext.getRequest().getSession().getAttribute("verifyCode");
//		if(!inputcode.equalsIgnoreCase(code)){
//			if(!"".equals(inputcode.trim())&&inputcode!=null){
//				ServletActionContext.getRequest().setAttribute("wrongCode", "验证码错误");
//			}
//			return "registerPage";
//		}else{
			user.setRegiTime(new Timestamp(new Date().getTime()));//添加注册时间
			user.setPermiss(0);//设置权限
			user.setHeadImgPath(ResourceRead.getImgPath()+"/user/head/init.png");
			user.setBgImgPath(ResourceRead.getImgPath()+"/user/bg/init.jpg");
			try {
				user.setPassword(MD5Util.md5(user.getPassword()));
			} catch (NoSuchAlgorithmException e1) {
				e1.printStackTrace();
			}
			userService.add(user);
			String activationCode = null;
			try {
				activationCode = MD5Util.md5(user.getUserName());
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			}
			String content = "欢迎注册本论坛，<a href='http://yuedu.51vip.biz/blog_parent/login"
					+ "/login_active.action?activationCode="+activationCode+"&id="+user.getUserId()+"'>点击我</a>"
							+ "进行激活用户。此链接4小时内有效，请尽快点击";
			try {
				MailUtil.sendMessage(user.getEmail(), "欢迎新成员", content);
			} catch (MessagingException e) {
				e.printStackTrace();
			}
			return "mailInfo";
//		}
		
	}
	
	//激活方法
	public String active(){
//		System.out.println(ServletActionContext.getRequest().getAttribute("id"));
//		int id = (int) ServletActionContext.getRequest().getAttribute("id");
//		String activationCode = (String) ServletActionContext.getRequest().getAttribute("activationCode");
//		System.out.println(id);
		User user = userService.load(id);
		if(user == null){
			return "failure";  //failure为失效或者错误的返回页面
		}else{
			try {
				if(!activationCode.equals(MD5Util.md5(user.getUserName()))){  //验证用户名的MD5和激活code是否一致
					return "failure";
				}else{
					Long timeDiffer = new Date().getTime() - user.getRegiTime().getTime();
					if(timeDiffer>14400000){ //验证链接的时效是否超过4小时
						return "failure";
					}else{
						user.setPermiss(1);
						userService.update(user);
						ServletActionContext.getRequest().getSession().setAttribute("loginUser", user);//设置一个用户的Session
						return "choose";
					}
				}
			} catch (NoSuchAlgorithmException e) {  //抛出异常，转到失败页面
				return "failure";
			}
		}
	}
	
	//验证名称是否重复
	public String checkName(){
		String queryString = "from User where userName=?";
		String[]params = {user.getUserName()};
		List<User>users = userService.findByHQL(queryString, params);
		try {
			if(user.getUserName()!=null&&!"".equals(user.getUserName())&&users.size()>0){
				ServletActionContext.getResponse().getWriter().println("1");//用户名重复
			}else if(user.getUserName()!=null&&!"".equals(user.getUserName())){
				ServletActionContext.getResponse().getWriter().println("0");//用户名可以使用
			}else{
				ServletActionContext.getResponse().getWriter().println("-1");//用户名为空的情况
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return NONE;
	}
	
	//生成验证码
	public String verifyImg() throws IOException{
		/**
		 * 设置响应头信息
		 */
		ServletActionContext.getResponse().setContentType("image/jpg");
		ServletActionContext.getResponse().setHeader("Pragma", "No-cache");
		ServletActionContext.getResponse().setHeader("Cache-Control", "no-cache");
		ServletActionContext.getResponse().setDateHeader("Expires", 0);
		HttpSession session = ServletActionContext.getRequest().getSession();
		//清除verifyCode
		session.removeAttribute("verifyCode");
		VerifyCode verifyCode = VerifyCode.getInstance();
		verifyCode.setSize(100, 50);
		String checkCode = verifyCode.getCheckCode();
		BufferedImage img = verifyCode.getCheckImg(checkCode);
		session.setAttribute("verifyCode", checkCode);
		ImageIO.write(img, "jpg", ServletActionContext.getResponse().getOutputStream());
//		System.out.println(session.getAttribute("verifyCode"));
//		System.out.println(session.getAttribute("verifyCode").getClass());
		return NONE;
	}

	public String loginPage(){
		return "loginPage";
	}
//status userName email headImgPath
	public String login(){
//		System.out.println(this.user.getUserName());
		String queryString = "from User where userName=? and password=?";
		String password = "";
		try {
			password = MD5Util.md5(this.user.getPassword());
		} catch (NoSuchAlgorithmException e1) {
			e1.printStackTrace();
		}
		String[]params = {user.getUserName().trim(),password};
		List<User>users = userService.findByHQL(queryString, params);
		try {
			if(users.size()>0){//用户名和账号匹配
				if(users.get(0).getPermiss() == 0||users.get(0).getPermiss() == null){//用户已经存在，但是尚未激活
					//TODO 暂时改为json传输
//					ServletActionContext.getResponse().getWriter().println("0");
//					String name = users.get(0).getUserName();
//					String wrong = "{\"status\":\"0\",\"userName\":"+users.get(0).getUserName()+",\"email\":"+users.get(0).getEmail()+"}";
					Map<String,String>wrongMap = new HashMap<>();
					wrongMap.put("userName", users.get(0).getUserName());
					wrongMap.put("password", users.get(0).getPassword());
					wrongMap.put("status", "0");
					wrongMap.put("email", users.get(0).getEmail());
					JSONObject responseJson = JSONObject.fromObject(wrongMap);
					ServletActionContext.getResponse().getWriter().println(responseJson);
				}else{//用户处于正常状态
					//TODO 暂时改为json
//					String right = "{\"status\":\"1\",\"userName\":"+users.get(0).getUserName()+",\"email\":"+users.get(0).getEmail()+"}";
//					ServletActionContext.getResponse().getWriter().println("1");
					Map<String,String> right = new HashMap<>();
					right.put("status","1" );
					right.put("userName", users.get(0).getUserName());
					right.put("password", users.get(0).getPassword());
					right.put("email", users.get(0).getEmail());
					right.put("userId", users.get(0).getUserId().toString());
					right.put("headImgPath", ResourceRead.getImgPath()+users.get(0).getHeadImgPath());
					JSONObject responseJson = JSONObject.fromObject(right);
					HttpServletResponse response = ServletActionContext.getResponse();
					//解决中文乱码
					response.setContentType("text/json");
					response.setCharacterEncoding("UTF-8");
					response.getWriter().println(responseJson);
					String imgP = ResourceRead.getImgPath();
					users.get(0).setHeadImgPath(imgP+users.get(0).getHeadImgPath());   //设置头像的相对路径
					users.get(0).setBgImgPath(imgP+users.get(0).getBgImgPath());     //设置背景路径
					ServletActionContext.getRequest().getSession().setAttribute("loginUser", users.get(0));//将登陆用户存入Session
				}
			}else{//用户名和账号不匹配
//				String wrong = "{\"status\":\"-1\"}";
				Map<String,String>wrong = new HashMap<>();
				wrong.put("status", "-1");
				JSONObject responseJson = JSONObject.fromObject(wrong);
				ServletActionContext.getResponse().getWriter().println(responseJson);
//				ServletActionContext.getResponse().getWriter().println("-1");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return NONE;
	}
	
	public String logout(){
		ServletActionContext.getRequest().getSession().removeAttribute("loginUser");
		return "loginPage";
	}
	
	@Override
	public User getModel() {
		return user;
	}
}
