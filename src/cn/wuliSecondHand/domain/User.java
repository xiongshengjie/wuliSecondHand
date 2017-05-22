package cn.wuliSecondHand.domain;

import java.io.Serializable;

public class User implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 用户编号
	 */
	private String id;
	/**
	 * 用户名
	 */
	private String name;
	/**
	 * 用户密码
	 */
	private String password;
	/**
	 * 昵称
	 */
	private String nickname;
	/**
	 * 微信
	 */
	private String wechat;
	/**
	 * qq
	 */
	private String qq;
	/**
	 * 电话号码
	 */
	private String telnum;
	/**
	 * 学院
	 */
	private String institute;
	/**
	 * 班级
	 */
	private String classes;
	/**
	 * 年级
	 */
	private String grade;
	/**
	 * 意向
	 */
	private String job_intention;
	/**
	 * 意向
	 */
	private String ex_jobinfo;
	/**
	 * 简历
	 */
	private String resume;
	
	
	public String getResume() {
		return resume;
	}
	public void setResume(String resume) {
		this.resume = resume;
	}
	public String getJob_intention() {
		return job_intention;
	}
	public void setJob_intention(String job_intention) {
		this.job_intention = job_intention;
	}
	public String getEx_jobinfo() {
		return ex_jobinfo;
	}
	public void setEx_jobinfo(String ex_jobinfo) {
		this.ex_jobinfo = ex_jobinfo;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getInstitute() {
		return institute;
	}
	public void setInstitute(String institute) {
		this.institute = institute;
	}
	public String getClasses() {
		return classes;
	}
	public void setClasses(String classes) {
		this.classes = classes;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public String getWechat() {
		return wechat;
	}
	public void setWechat(String wechat) {
		this.wechat = wechat;
	}
	public String getQq() {
		return qq;
	}
	public void setQq(String qq) {
		this.qq = qq;
	}
	public String getTelnum() {
		return telnum;
	}
	public void setTelnum(String telnum) {
		this.telnum = telnum;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
