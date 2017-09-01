
package model;

import org.apache.solr.client.solrj.beans.Field;

public class Jobs {
	
	private String id;
	private String jobtitle;
	private String education;
	private int experience;
	private int salary;
	//Private int bonus;
	
	
	
	public String getId() {
		return id;
	}
	@Field
	public void setId(String id) {
		this.id = id;
	}
	public String getJobtitle() {
		return jobtitle;
	}
	@Field
	public void setJobtitle(String jobtitle) {
		this.jobtitle = jobtitle;
	}
	public String getEducation() {
		return education;
	}
	@Field
	public void setEducation(String education) {
		this.education = education;
	}
	public int getExperience() {
		return experience;
	}
	@Field
	public void setExperience(int experience) {
		this.experience = experience;
	}
	public int getSalary() {
		return salary;
	}
	@Field
	public void setSalary(int salary) {
		this.salary = salary;
	}
	
	

}
