package puma.sp.mgmt.repositories.attribute;

import java.util.List;

import puma.sp.mgmt.model.attribute.AttributeFamily;
import puma.sp.mgmt.model.organization.Organization;

public interface AttributeFamilyDAO {
	public List<AttributeFamily> findAllOrganization(Organization org);

	public List<AttributeFamily> byName(String xacmlIdentifier);
}
