package cn.wuliSecondHand.domain;

import java.io.Serializable;

public class Product implements Serializable{
	/**
	 * 商品编号
	 */
	private String id;
	/**
	 * 标题
	 */
	private String title;
	/**
	 * 描述
	 */
	private String description;	
	/**
	 * 价格
	 */
	private double price;
	/**
	 * 分类
	 */
	private String category; 
	/**
	 * 是否接受砍价
	 */
	private String isbargain;
	/**
	 * 是否以物换物
	 */
	private String ischange;
	/**
	 * 校区
	 */
	private String schoolarea;
	/**
	 * 图片路径
	 */
	private String imgurl;
	/**
	 * 压缩图片路径
	 */
	private String imgurlcompress;
	/**
	 * 发布人
	 */
	private String user;
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
	 * 发布时间
	 */
	private String pushtime;
	
	
	
	public String getImgurlcompress() {
		return imgurlcompress;
	}
	public void setImgurlcompress(String imgurlcompress) {
		this.imgurlcompress = imgurlcompress;
	}
	public String getPushtime() {
		return pushtime;
	}
	public void setPushtime(String pushtime) {
		this.pushtime = pushtime;
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
	public String getSchoolarea() {
		return schoolarea;
	}
	public void setSchoolarea(String schoolarea) {
		this.schoolarea = schoolarea;
	}
	public String getIsbargain() {
		return isbargain;
	}
	public void setIsbargain(String isbargain) {
		this.isbargain = isbargain;
	}
	public String getIschange() {
		return ischange;
	}
	public void setIschange(String ischange) {
		this.ischange = ischange;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getImgurl() {
		return imgurl;
	}
	public void setImgurl(String imgurl) {
		this.imgurl = imgurl;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	
}
