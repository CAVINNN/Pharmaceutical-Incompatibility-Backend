package com.pi.dao;

import java.util.List;

public interface InstanceDao {
	
	// 获取所有实例
	public List<String> getAllMedicines();
		
	// 获取全部中成药实例
	public List<String> getChineseMedicines();
		
	// 获取全部西药实例
	public List<String> getWesternMedicines();
	
}
