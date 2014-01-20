package puma.sp.mgmt.repositories.organization;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import puma.sp.mgmt.model.organization.Tenant;

@Service
public class TenantServiceImpl implements TenantService {
	@Autowired
	private TenantRepository repository;
	
	@Autowired
	private OrganizationRepository orgRep;
	
	@Autowired
	private TenantDAO dao;
	
	@Override
	public void addTenant(Tenant tenant) {
		this.repository.saveAndFlush(tenant);
	}

	@Override
	public void deleteTenant(Long tenantId) {
		this.orgRep.delete(tenantId);
	}

	@Override
	public Tenant byName(String name) {
		return this.dao.byName(name);
	}

	@Override
	public List<Tenant> findAll() {
		return this.repository.findAll();
	}

	@Override
	public Tenant findOne(Long id) {
		return this.repository.findOne(id);
	}

	@Override
	public Boolean exists(Long id) {
		return this.repository.exists(id);
	}

}
