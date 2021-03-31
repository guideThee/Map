package com.matlabImpl;

import java.util.ArrayList;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mathworks.toolbox.javabuilder.MWClassID;
import com.mathworks.toolbox.javabuilder.MWNumericArray;
import com.matlabInterface.GeneratePath;

import Bean.pointArray;
import net.sf.json.JSONArray;

@Service
public class GeneratePathImpl implements GeneratePath<pointArray> {

	@Override
	public int[] generateResult(String pointArrayString) {
		List<pointArray> pointSet = parsePointArrayString(pointArrayString);
		MWNumericArray input = changePointListIntoMatrix(pointSet);
		return null;
	}
	
	/** 解析服务器传过来的json字符串，该字符串包含了每个点的id.经纬度信息。
	 * @param pointArrayString
	 * @return
	 */
	public List<pointArray> parsePointArrayString(String pointArrayString){
		JSONArray jsonArray = JSONArray.fromObject(pointArrayString);
		ArrayList<pointArray> point = new ArrayList<>();
		for (int i = 0; i < jsonArray.size(); i++) {
			pointArray p = new pointArray();
			p.setId(jsonArray.getJSONObject(i).getInt("id"));
			p.setLng(Double.parseDouble(jsonArray.getJSONObject(i).getString("lng")));
			p.setLat(Double.parseDouble(jsonArray.getJSONObject(i).getString("lat")));
			point.add(p);
		}
		return point;
	}
	
	/**将点的List转化为matlab矩阵
	 * @param pointList
	 * @return 矩阵第一行为id，第二行为纬度，第三行为经度
	 */
	public MWNumericArray changePointListIntoMatrix(List<pointArray> pointList){
		int length = pointList.size();
		double[][] data = new double[3][length];
			for(int j = 0; j < length; j++) {
				data[0][j] = (double)pointList.get(j).getId();
				data[1][j] = pointList.get(j).getLat();
				data[2][j] = pointList.get(j).getLng();
			}
		return new MWNumericArray(data,MWClassID.DOUBLE);
	}

}
