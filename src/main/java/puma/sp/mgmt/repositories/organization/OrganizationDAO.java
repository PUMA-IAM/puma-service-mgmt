package puma.sp.mgmt.repositories.organization;

import puma.sp.mgmt.model.organization.Organization;


public interface OrganizationDAO {
	
	public Organization byName(String name);
	
}
