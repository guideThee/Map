package com.matlabInterface;

import java.util.List;

import com.mathworks.toolbox.javabuilder.MWNumericArray;

import Bean.pointArray;

public interface GeneratePath<E> {
	
	/**
	 * @param pointSetInfo 三行二维矩阵，第一行：点的id，第二行：点的纬度，第三行：点的经度
	 * @return 以正确的顺序排列出的id数组
	 */
	int[] generateResult(String jsonString);
	
	/**
	 * @param <E> 如何将从服务器传过来的jsonString转化为对象的List
	 * @param jsonString
	 * @return
	 */
	List<E> parsePointArrayString(String jsonString);
	
	/**将list转化为矩阵
	 * @param list
	 * @return
	 */
	MWNumericArray changePointListIntoMatrix(List<E> list);


}
