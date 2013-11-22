package puma.sp.mgmt.repositories.organization;

import java.util.List;

import puma.sp.mgmt.model.organization.Organization;

public interface OrganizationService {
	
	public void addOrganization(Organization organization);
	
	public void deleteOrganization(Long organizationId);
	
	public List<Organization> findAll();
	
	public Organization findOne(Long id);
	
	public Boolean exists(Long id);
	
	public Organization byName(String name);
	
	public Organization getProviderOrganization();

}
