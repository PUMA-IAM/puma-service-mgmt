package puma.sp.mgmt.repositories.attribute;

import java.util.List;

import puma.sp.mgmt.model.attribute.AttributeFamily;
import puma.sp.mgmt.model.organization.Organization;

public interface AttributeFamilyService {
	
	public AttributeFamily findOne(Long id);
	
	public List<AttributeFamily> findAll();
	
	public List<AttributeFamily> findAllOrganization(Organization org);
	
	public List<AttributeFamily> findAllOrganizationProvider(Organization org);
	
	public void add(AttributeFamily af);
	
	public void delete(AttributeFamily af);

}
