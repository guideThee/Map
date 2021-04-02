package com.bean;

/**方案内的仓库
 * @author hzz
 *
 */
public class Warehouse {

	private int warehouseId;
	
	private String userLoginname;
	
	private int id;//某一个仓库点集的id
	
	private String lng;//经度
	
	private String lat;//纬度
	
	private String flag;//属于哪一个仓库的点集

	public int getWarehouseId() {
		return warehouseId;
	}

	public void setWarehouseId(int warehouseId) {
		this.warehouseId = warehouseId;
	}

	public String getUserLoginname() {
		return userLoginname;
	}

	public void setUserLoginname(String userLoginname) {
		this.userLoginname = userLoginname;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public Warehouse(String userLoginname, int id, String lng, String lat, String flag) {
		super();
		this.userLoginname = userLoginname;
		this.id = id;
		this.lng = lng;
		this.lat = lat;
		this.flag = flag;
	}

	public Warehouse() {
		super();
	}

	@Override
	public String toString() {
		return "Warehouse [warehouseId=" + warehouseId + ", userLoginname=" + userLoginname + ", id=" + id + ", lng="
				+ lng + ", lat=" + lat + ", flag=" + flag + "]";
	}
	
	
}
