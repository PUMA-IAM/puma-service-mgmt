package puma.sp.mgmt.repositories.attribute;

import java.util.List;

import puma.sp.mgmt.model.attribute.AttributeFamily;

public interface AttributeFamilyService {
	
	public AttributeFamily findOne(Long id);
	
	public List<AttributeFamily> findAll();
	
	public void add(AttributeFamily af);
	
	public void delete(AttributeFamily af);

}
