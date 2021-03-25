package Bean;

public class pointArray {
private int id;
private double lng;
private double lat;
public int flage = -1;
public double getLng() {
	return lng;
}
public void setLng(double lng) {
	this.lng = lng;
}
public double getLat() {
	return lat;
}
public void setLat(double lat) {
	this.lat = lat;
}
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
@Override
public String toString() {
	return "pointArray [id=" + id + ", lng=" + lng + ", lat=" + lat + ", flage=" + flage + "]";
}
public pointArray(int id, double lng, double lat, int flage) {
	super();
	this.id = id;
	this.lng = lng;
	this.lat = lat;
	this.flage = flage;
}
public pointArray() {
	super();
	// TODO Auto-generated constructor stub
}
}
