package com.faymax.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.faymax.dao.PropertyValueDao;
import com.faymax.pojo.Product;
import com.faymax.pojo.Property;
import com.faymax.pojo.PropertyValue;
import com.faymax.pojo.PropertyValueExample;

@Service
public class PropertyValueServiceImpl implements PropertyValueService {
	
	@Autowired
	private PropertyValueDao pvDao;
	@Autowired
	private PropertyService pService;
	
	@Override
	public void init(Product p) {
		List<Property> pts = pService.list(p.getCid());
		for(Property pt:pts) {
			PropertyValue pv = get(pt.getId(), p.getId());
			if(null==pv) {
				pv = new PropertyValue();
				pv.setPid(p.getId());
				pv.setPtid(pt.getId());
				pvDao.insert(pv);
			}
		}
		
	}
	
	@Override
	public void update(PropertyValue pv) {
		pvDao.updateByPrimaryKeySelective(pv);
		
	}
	@Override
	public PropertyValue get(int ptid, int pid) {
		PropertyValueExample example = new PropertyValueExample();
        example.createCriteria().andPtidEqualTo(ptid).andPidEqualTo(pid);
        List<PropertyValue> pvs= pvDao.selectByExample(example);
        if (pvs.isEmpty())
            return null;
        return pvs.get(0);
	}
	@Override
	public List<PropertyValue> list(int pid) {
		PropertyValueExample example = new PropertyValueExample();
        example.createCriteria().andPidEqualTo(pid);
        List<PropertyValue> result = pvDao.selectByExample(example);
        for (PropertyValue pv : result) {
            Property property = pService.get(pv.getPtid());
            pv.setProperty(property);
        }
        return result;
	}
	
	public void setProperty(List<PropertyValue> list) {
		for (PropertyValue pv : list) { 
            setProperty(pv);
        }
	}
	
	public void setProperty(PropertyValue pv) {
		Property property = pService.get(pv.getPtid());
		pv.setProperty(property);
	}

}
