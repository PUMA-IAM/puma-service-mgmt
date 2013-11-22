package puma.sp.mgmt.repositories.attribute;

import puma.sp.mgmt.model.attribute.AttributeFamily;

public interface AttributeFamilyService {
	
	public AttributeFamily findOne(Long id);
	
	public void add(AttributeFamily af);
	
	public void delete(AttributeFamily af);

}
