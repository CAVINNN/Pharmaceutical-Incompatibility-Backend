package com.pi.service;

import java.util.List;

public interface IncompatibilityService {
	
	public boolean hasIncompatibility(String chineseMedicine, String westernMedicine);
	
	public List<String> checkIncompatibility(List<String> chineseMedicines, List<String> westernMedicines);
	
}
