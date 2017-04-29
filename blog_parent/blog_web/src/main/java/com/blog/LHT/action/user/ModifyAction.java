package com.blog.LHT.action.user;

import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import com.blog.LHT.entity.User;
import com.blog.LHT.service.UserService;
import com.blog.LHT.utils.MD5Util;
import com.blog.LHT.utils.NameUtil;
import com.blog.LHT.utils.ResourceRead;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class ModifyAction extends ActionSupport implements ModelDriven<User>{

	private static final long serialVersionUID = 1L;
//	private static final String HEAD_PATH = "/blog_parent/img/user/head/";
	private static final String HEAD_PATH = "user/head/";
//	private static final String REAL_HEAD_PATH = "F:/STSWorkspace/blog/blog_parent/blog_web/src/main/webapp/img/user/head/";
	private static final String REAL_HEAD_PATH = ResourceRead.getImgRealPath()+"user/head/";
//	private static final String BG_PATH = "/blog_parent/img/user/bg/";
	private static final String BG_PATH = "user/bg/";
//	private static final String REAL_BG_PATH = "F:/STSWorkspace/blog/blog_parent/blog_web/src/main/webapp/img/user/bg/";
	private static final String REAL_BG_PATH = ResourceRead.getImgRealPath()+"user/bg/";
//	private static List<String>fileType = Arrays.asList("bmp","gif","jpeg","png","jpg","tga","JPG","BMP","GIF","JPEG","PNG","TAG");
	private User user = new User();
	private UserService userService;
	
	private File headImg;
	private String headImgFileName;
//	private String headImgContentType;
	private File bgImg;
	private String bgImgFileName;
//	private String bgImgContentType;
	
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

//	public void setHeadImgContentType(String headImgContentType) {
//		this.headImgContentType = headImgContentType;
//	}
//
//	public void setBgImgContentType(String bgImgContentType) {
//		this.bgImgContentType = bgImgContentType;
//	}
	public void setHeadImgFileName(String headImgFileName) {
		this.headImgFileName = headImgFileName;
	}

	public void setBgImgFileName(String bgImgFileName) {
		this.bgImgFileName = bgImgFileName;
	}

	public void setHeadImg(File headImg) {
		this.headImg = headImg;
	}

	public void setBgImg(File bgImg) {
		this.bgImg = bgImg;
	}

	//用来更新用户的信息
	public String update(){
		User user = (User) ServletActionContext.getRequest().getSession().getAttribute("loginUser");
		if(this.user.getBirth() == null||this.user.getBirth().toString().trim() == ""){
			this.user.setBirth(user.getBirth());
		}
		this.user.setUserName(user.getUserName());
		this.user.setPermiss(user.getPermiss());
		this.user.setRegiTime(user.getRegiTime());
		this.user.setEmail(user.getEmail());
		try {
			if(user.getPassword().equals(this.user.getPassword())){  //判断用户是否修改密码
				this.user.setPassword(user.getPassword());
			}else{
				this.user.setPassword(MD5Util.md5(this.user.getPassword()));   //将密码加密
			}
		} catch (NoSuchAlgorithmException e2) {
			e2.printStackTrace();
		}
		String headName = "";
		String bgName = "";
//		this.user.setEmail(user.getEmail());
//		this.user.setUserId(user.getUserId());
		if(headImgFileName == null){
			String path =user.getHeadImgPath();
			if(!path.contains("_")){
				this.user.setHeadImgPath(path);
				headName = "init.png";
			}else{
				path = path.substring(path.indexOf("user/"));
				this.user.setHeadImgPath(path);
				headName = path.substring(path.indexOf(this.user.getUserId()+"_"));
			}
		}else{
			headName = NameUtil.getHeadName(user.getUserId(), getFileType(headImgFileName));
			String headPath = HEAD_PATH+headName;
			this.user.setHeadImgPath(headPath);
		}
		if(bgImgFileName == null){
			String path = user.getBgImgPath();
			if(!path.contains("_")){
				this.user.setBgImgPath(path);
				bgName = "init.jpg";
			}else{
				path = path.substring(path.indexOf("user/"));
				this.user.setBgImgPath(path);
				bgName = path.substring(path.indexOf(this.user.getUserId()+"_"));
			}
		}else{
			bgName = NameUtil.getBgName(user.getUserId(), getFileType(bgImgFileName));
			 String bgPath = BG_PATH+ bgName;
			this.user.setBgImgPath(bgPath);
		}
		this.user.setLastUpdateTime(new Timestamp(new Date().getTime()));
		try {
			userService.update(this.user);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		try {
			if(headImg!=null){
				FileUtils.copyFile(headImg, new File(REAL_HEAD_PATH+headName));
			}
			if(bgImg!=null){
				FileUtils.copyFile(bgImg, new File(REAL_BG_PATH+bgName));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		ServletActionContext.getRequest().getSession().removeAttribute("loginUser");
		//TODO 路径需要修改
		
		this.user.setHeadImgPath(ResourceRead.getImgPath()+HEAD_PATH+headName);
		this.user.setBgImgPath(ResourceRead.getImgPath()+BG_PATH+bgName);
		ServletActionContext.getRequest().getSession().setAttribute("loginUser", this.user);
		return "update";
	}
	
//	public String testJson(){
//		//{username:"Milla",password:"Hello"}
//		try {
//			ServletActionContext.getResponse().getWriter().println("{\"username\":\"Milla\",\"password\":\"Hello\"}");
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		return NONE;
//	}
	

	
	public String testCopy(){
//		String path = this.getClass().getClassLoader().getResource("").toString();
//		System.out.println(path.substring(6, path.indexOf("blog_web"))+"blog_web/src/main/webapp/img/user/head"+headImgFileName);
		try {
			FileUtils.copyFile(headImg, new File(HEAD_PATH+headImgFileName));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return NONE;
	}
	
	//获得文件的后缀
	private static String getFileType(String fileName){
	int position = fileName.lastIndexOf(".");
	if(position == 0){
		return null;
	}else{
		return fileName.substring(position);
	}
}
	
	@Override
	public User getModel() {
		return user;
	}

	
	
//	public String cacheHead(){
//		String format = getFileType(headImgFileName);
//		System.out.println(format);
//		User user = (User) ServletActionContext.getRequest().getSession().getAttribute("loginUser");
//		String copyFilePath = "";
//		try {
//			if(fileType.contains(format)){
//				copyFilePath = "F:/STSWorkspace/blog/blog_parent/blog_web/src/main/webapp/img/user/head/"+user.getUserId()+"(1)"+"."+format;
//				FileUtils.copyFile(headImg, new File(copyFilePath));
//				ServletActionContext.getRequest().setAttribute("copyFilePath", copyFilePath);
////				ServletActionContext.getResponse().getWriter().println("1"); //图片格式合法
//			}else{
//				ServletActionContext.getResponse().getWriter().println("0");  //图片格式不合法
//			}
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		return NONE;
//	}
	

	//用来校验图像的文件的格式
//	public String checkFormat(){
//		String imgFormat = user.getHeadImgPath();
//		System.out.println(imgFormat);
//		try {
//			if(fileType.contains(getFileType(imgFormat))){
//				ServletActionContext.getResponse().getWriter().println("1"); //图片格式合法
//			}else{
//				ServletActionContext.getResponse().getWriter().println("0");  //图片格式不合法
//			}
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		return NONE;
//	}
	
//	@Test
//	public void testPath(){
//		String path = this.getClass().getClassLoader().getResource("").toString();
//		System.out.println(path.indexOf("blog_web"));
//		System.out.println(path.charAt(path.indexOf("blog_web")));
//		System.out.println(path.substring(6, path.indexOf("blog_web")));
//	}
	
}
