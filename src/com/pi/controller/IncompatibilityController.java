package com.pi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pi.dao.InstanceDao;
import com.pi.entities.Medicine;
import com.pi.service.IncompatibilityService;

@Controller
public class IncompatibilityController {
	
	@Autowired
	private InstanceDao instanceDao;
	
	@Autowired
	private IncompatibilityService incompatiblityService;
	
	@RequestMapping("/getChineseMedicines")
	@ResponseBody
	public List<String> getChineseMedicines() {
		List<String> list = instanceDao.getChineseMedicines();
		return list;
	}
	
	@RequestMapping("/getWesternMedicines")
	@ResponseBody
	public List<String> getWesternMedicines() {
		List<String> list = instanceDao.getWesternMedicines();
		return list;
	}
	
	@RequestMapping("/doMix")
	@ResponseBody
	public List<String> doMix(@RequestBody Medicine medicine) {
		List<String> cmList = medicine.getChineseMedicines();
		List<String> wmList = medicine.getWesternMedicines();
		
		List<String> list = incompatiblityService.checkIncompatibility(cmList, wmList);
		
		if( !list.isEmpty() ) {
			return list;
		}
		else {
			return list;
		}
	}
	
}
