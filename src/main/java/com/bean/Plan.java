package com.bean;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

/** 用户保存的路径方案
 * @author hzz
 *
 */
@Document(collection="plan")
public class Plan {
	@Id
	private String planId;
	
	private String userLoginname;
	
	private String planName;
	
	private String distance;
	
	private Timestamp createTime;
	
	private List<Point> points;

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
	

	public List<Point> getPoints() {
		return points;
	}

	public void setPoints(List<Point> points) {
		this.points = points;
	}

	public Plan() {
		super();
	}



	@Override
	public String toString() {
		return "Plan [planId=" + planId + ", userLoginname=" + userLoginname + ", planName=" + planName + ", distance="
				+ distance + ", createTime=" + createTime + ", points=" + points + "]";
	}

	@PersistenceConstructor
	public Plan(String planId, String userLoginname, String planName, String distance, Timestamp createTime,
			List<Point> points) {
		super();
		this.planId = planId;
		this.userLoginname = userLoginname;
		this.planName = planName;
		this.distance = distance;
		this.createTime = createTime;
		this.points = points;
	}
	

}
