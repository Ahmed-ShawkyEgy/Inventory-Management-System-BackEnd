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
			HashMap<String, Object> row = new HashMap<>();
			for(int idx = 0 ; idx < ((Object[])i).length;idx++)		
			{
				row.put(columns[idx], ((Object[])i)[idx]);
			}
			map.add(row);
		}
		return map;		
	
	}
	
}
