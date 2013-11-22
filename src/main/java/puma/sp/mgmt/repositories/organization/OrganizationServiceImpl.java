package puma.sp.mgmt.repositories.organization;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import puma.sp.mgmt.model.organization.Organization;

@Service
public class OrganizationServiceImpl implements OrganizationService {
	
	public static final String PROVIDER_ORGANIZATION_NAME = "provider";
	
	@Autowired
	private OrganizationRepository repository;
	
	@Autowired
	private OrganizationDAO dao;
	
	@Override
	public void addOrganization(Organization organization) {
		this.repository.saveAndFlush(organization);
	}

	@Override
	public void deleteOrganization(Long organizationId) {
		this.repository.delete(organizationId);
	}

	@Override
	public Organization byName(String name) {
		return this.dao.byName(name);
	}

	@Override
	public List<Organization> findAll() {
		return this.repository.findAll();
	}

	@Override
	public Organization findOne(Long id) {
		return this.repository.findOne(id);
	}

	@Override
	public Boolean exists(Long id) {
		return this.repository.exists(id);
	}

	@Override
	public Organization getProviderOrganization() {
		return this.byName(PROVIDER_ORGANIZATION_NAME);
	}

}
