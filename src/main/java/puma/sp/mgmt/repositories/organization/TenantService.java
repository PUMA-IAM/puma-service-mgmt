package puma.sp.mgmt.repositories.organization;

import java.util.List;
import puma.sp.mgmt.model.organization.Tenant;

public interface TenantService {
	public void addTenant(Tenant tenant);
	public void deleteTenant(Long tenantId);
	public List<Tenant> findAll();
	public Tenant findOne(Long id);
	public Boolean exists(Long id);
	public Tenant byName(String name);
}
