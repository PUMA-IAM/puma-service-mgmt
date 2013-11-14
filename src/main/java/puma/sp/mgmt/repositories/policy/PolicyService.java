package puma.sp.mgmt.repositories.policy;

import puma.sp.mgmt.model.policy.Policy;

public interface PolicyService {

	public Policy getApplicationPolicy();

	public void storeApplicationPolicy(String contents);
}
