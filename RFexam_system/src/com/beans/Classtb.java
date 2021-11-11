package com.beans;
/**
 * 班级表
 * @author liuyang
 *
 */
public class Classtb {
	private int id;
	private String cname;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public Classtb(int id, String cname) {
		super();
		this.id = id;
		this.cname = cname;
	}
	public Classtb() {
		super();
	}
	
}
