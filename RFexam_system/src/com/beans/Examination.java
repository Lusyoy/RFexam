package com.beans;
/**
 * 试题表
 * @author liuyang
 *
 */
public class Examination {
	private int id;
	private int courseid;
	private String question;
	private String ansA;
	private String ansB;
	private String ansC;
	private String ansD;
	private String ans;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getCourseid() {
		return courseid;
	}
	public void setCourseid(int courseid) {
		this.courseid = courseid;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public String getAnsA() {
		return ansA;
	}
	public void setAnsA(String ansA) {
		this.ansA = ansA;
	}
	public String getAnsB() {
		return ansB;
	}
	public void setAnsB(String ansB) {
		this.ansB = ansB;
	}
	public String getAnsC() {
		return ansC;
	}
	public void setAnsC(String ansC) {
		this.ansC = ansC;
	}
	public String getAnsD() {
		return ansD;
	}
	public void setAnsD(String ansD) {
		this.ansD = ansD;
	}
	public String getAns() {
		return ans;
	}
	public void setAns(String ans) {
		this.ans = ans;
	}
	public Examination(int id, int courseid, String question, String ansA,
			String ansB, String ansC, String ansD, String ans) {
		super();
		this.id = id;
		this.courseid = courseid;
		this.question = question;
		this.ansA = ansA;
		this.ansB = ansB;
		this.ansC = ansC;
		this.ansD = ansD;
		this.ans = ans;
	}
	public Examination() {
		super();
	}
	
}
