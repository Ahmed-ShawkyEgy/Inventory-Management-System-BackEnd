package com.orange.inventory.helper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class QueryHelper {

	public static List<HashMap<String, Object>> queryToMapList(List<Object[]> rows,String[] columns)
	{
		List<HashMap<String, Object>> map = new ArrayList<HashMap<String, Object>>();
		for(Object i: rows)
		{
			map.add(queryToMap((Object[])i, columns));
		}
		return map;		
	
	}
	
	public static HashMap<String, Object> queryToMap(Object[] row,String[] columns)
	{
		if(columns.length!=row.length)
			throw new RuntimeException("Cannot map columns to given row");
		HashMap<String, Object> result = new HashMap<>();
		for(int i = 0; i < row.length;i++)
		{
			result.put(columns[i], row[i]);
		}
		return result;
	}
	
}
