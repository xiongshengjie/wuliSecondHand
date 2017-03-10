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
}
