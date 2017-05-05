package cn.wuliSecondHand.domain;

import java.io.Serializable;

public class DetailBean implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 用户
	 */
	private User user;
	/**
	 * 产品
	 */
	private Product product;
	/**
	 * 当前用户是否收藏
	 */
	private String iscoll;
	
	public String getIscoll() {
		return iscoll;
	}
	public void setIscoll(String iscoll) {
		this.iscoll = iscoll;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	
}
