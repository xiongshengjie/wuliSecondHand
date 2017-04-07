package cn.wuliSecondHand.domain;

import java.io.Serializable;

public class Collection implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 收藏编号
	 */
	private String id;
	/**
	 * 收藏人
	 */
	private String user;
	/**
	 * 收藏物品
	 */
	private String productid;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getProductid() {
		return productid;
	}
	public void setProductid(String productid) {
		this.productid = productid;
	}
	
}
