package com.pi.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pi.dao.IncompatibilityDao;

@Service("incompatibilityService")
public class IncompatibilityServiceImpl implements IncompatibilityService {
	
	@Autowired
	private IncompatibilityDao incompatibilityDao;
	
	@Override
	public boolean hasIncompatibility(String chineseMedicine, String westernMedicine) {
		boolean result = false;
		List<String> incList = incompatibilityDao.getIncompatibilityMedicines(chineseMedicine);
		Iterator<String> it = incList.iterator();
		while( it.hasNext() ) {
			if( it.next().equals(westernMedicine) ) {
				result = true;
			}
		}
		return result;
	}

	@Override
	public List<String> checkIncompatibility(List<String> chineseMedicines, List<String> westernMedicines) {
		List<String> list = new ArrayList<String>();
		Iterator<String> cmIt = chineseMedicines.iterator();
		while( cmIt.hasNext() ) {
			String nowCM = cmIt.next();
			Iterator<String> wmIt = westernMedicines.iterator();
			while( wmIt.hasNext() ) {
				String nowWM = wmIt.next();
				if( hasIncompatibility(nowCM, nowWM) ) {
					list.add(nowCM + "vs" + nowWM);
				}
			}
			
		}
		return list;
	}

}
