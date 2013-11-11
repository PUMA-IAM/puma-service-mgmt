package puma.sp.mgmt.repositories.organization;

import java.util.List;

import puma.sp.mgmt.model.organization.Tenant;

public interface TenantDAO {
	public Tenant byName(String name);
	public List<Tenant> subtenants(Tenant tenant);
}
