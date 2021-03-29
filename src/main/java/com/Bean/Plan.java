package com.Bean;

import java.util.Date;

public class Plan {
    private String planId;

    private String userLoginname;

    private String planName;

    private String distance;

    private String point;

    private String warehouse;

    private String flage;

    public Plan() {}
    public Plan(String userLoginname, String planName, String distance, String point, String warehouse, String flage,
			Date creattime) {
		this.userLoginname = userLoginname;
		this.planName = planName;
		this.distance = distance;
		this.point = point;
		this.warehouse = warehouse;
		this.flage = flage;
		this.creattime = creattime;
	}

	private Date creattime;

    public String getPlanId() {
        return planId;
    }

    public void setPlanId(String planId) {
        this.planId = planId == null ? null : planId.trim();
    }

    public String getUserLoginname() {
        return userLoginname;
    }

    public void setUserLoginname(String userLoginname) {
        this.userLoginname = userLoginname == null ? null : userLoginname.trim();
    }

    public String getPlanName() {
        return planName;
    }

    public void setPlanName(String planName) {
        this.planName = planName == null ? null : planName.trim();
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance == null ? null : distance.trim();
    }

    public String getPoint() {
        return point;
    }

    public void setPoint(String point) {
        this.point = point == null ? null : point.trim();
    }

    public String getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(String warehouse) {
        this.warehouse = warehouse == null ? null : warehouse.trim();
    }

    public String getFlage() {
        return flage;
    }

    public void setFlage(String flage) {
        this.flage = flage == null ? null : flage.trim();
    }

    public Date getCreattime() {
        return creattime;
    }

    public void setCreattime(Date creattime) {
        this.creattime = creattime;
    }
}