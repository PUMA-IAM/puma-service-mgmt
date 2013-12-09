package puma.sp.mgmt.repositories.policy;

import java.util.List;

import puma.sp.mgmt.model.organization.Organization;
import puma.sp.mgmt.model.policy.Policy;


public interface PolicyDAO {
	public List<Policy> getPolicies(Organization organization);
}
