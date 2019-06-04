package com.pi.entities;

import java.util.List;

public class Medicine {
	
	private List<String> chineseMedicines;
	private List<String> westernMedicines;
	
	@Override
	public String toString() {
		return "Medicine [chineseMedicines=" + chineseMedicines + ", westernMedicines=" + westernMedicines + "]";
	}
	public List<String> getChineseMedicines() {
		return chineseMedicines;
	}
	public void setChineseMedicines(List<String> chineseMedicines) {
		this.chineseMedicines = chineseMedicines;
	}
	public List<String> getWesternMedicines() {
		return westernMedicines;
	}
	public void setWesternMedicines(List<String> westernMedicines) {
		this.westernMedicines = westernMedicines;
	}
	
}
