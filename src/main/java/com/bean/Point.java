package com.bean;

/**方案内的点
 * @author hzz
 *
 */
public class Point {
	
	private int pointId;//自增的id
	
	private String planId;//该点属于哪一个方案
	
	private int Id;//属于某一个仓库内点集的id
	
	private String lng;//经度
	
	private String lat;//纬度
	
	private String flag;//属于哪一个仓库

	public Point(String planId, int id, String lng, String lat, String flag) {
		super();
		this.planId = planId;
		Id = id;
		this.lng = lng;
		this.lat = lat;
		this.flag = flag;
	}

	public Point() {
		super();
	}

	public int getPointId() {
		return pointId;
	}

	public void setPointId(int pointId) {
		this.pointId = pointId;
	}

	public String getPlanId() {
		return planId;
	}

	public void setPlanId(String planId) {
		this.planId = planId;
	}

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getLng() {
		return lng;
	}

	public void setLng(String lng) {
		this.lng = lng;
	}

	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	@Override
	public String toString() {
		return "Point [pointId=" + pointId + ", planId=" + planId + ", Id=" + Id + ", lng=" + lng + ", lat=" + lat
				+ ", flag=" + flag + "]";
	}
	
	

}
