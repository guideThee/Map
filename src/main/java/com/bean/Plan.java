package com.bean;

import java.sql.Timestamp;

/** 用户保存的路径方案
 * @author hzz
 *
 */
public class Plan {
	
	private String planId;
	
	private String userLoginname;
	
	private String planName;
	
	private String distance;
	
	private Timestamp createTime;

	public String getPlanId() {
		return planId;
	}

	public void setPlanId(String planId) {
		this.planId = planId;
	}

	public String getUserLoginname() {
		return userLoginname;
	}

	public void setUserLoginname(String userLoginname) {
		this.userLoginname = userLoginname;
	}

	public String getPlanName() {
		return planName;
	}

	public void setPlanName(String planName) {
		this.planName = planName;
	}

	public String getDistance() {
		return distance;
	}

	public void setDistance(String distance) {
		this.distance = distance;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public Plan() {
		super();
	}

	public Plan(String planId, String userLoginname, String planName, String distance, Timestamp createTime) {
		super();
		this.planId = planId;
		this.userLoginname = userLoginname;
		this.planName = planName;
		this.distance = distance;
		this.createTime = createTime;
	}

	@Override
	public String toString() {
		return "Plan [planId=" + planId + ", userLoginname=" + userLoginname + ", planName=" + planName + ", distance="
				+ distance + ", createTime=" + createTime + "]";
	}
	

}
