/*******************************************************************************
 * Copyright 2014 KU Leuven Research and Developement - iMinds - Distrinet 
 * 
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 * 
 *        http://www.apache.org/licenses/LICENSE-2.0
 * 
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 *    
 *    Administrative Contact: dnet-project-office@cs.kuleuven.be
 *    Technical Contact: maarten.decat@cs.kuleuven.be
 *    Author: maarten.decat@cs.kuleuven.be
 ******************************************************************************/
package puma.sp.mgmt.repositories.policy;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import puma.sp.mgmt.model.organization.Organization;
import puma.sp.mgmt.model.policy.Policy;
import puma.sp.mgmt.model.policy.PolicyType;

@Service
public class PolicyServiceImpl implements PolicyService {
	
	private static final String APPLICATION_POLICY_ID = "application-policy";
	
	private static final String CENTRAL_PUMA_PDP_POLICY_ID = "central-puma-policy";

	@Autowired
	private PolicyRepository policyRepository;
	
	@Autowired
	private PolicyDAO policyDAO;

	@Override
	public Policy getApplicationPolicy() {
		return policyRepository.findOne(APPLICATION_POLICY_ID);
	}

	@Override
	public void storeApplicationPolicy(String contents) {
		Policy ap = policyRepository.findOne(APPLICATION_POLICY_ID);
		if(ap == null) {
			ap = new Policy(APPLICATION_POLICY_ID, PolicyType.APPLICATION, null, contents);
		}
		ap.setContent(contents);
		policyRepository.save(ap);
	}

	@Override
	public Policy getCentralPUMAPDPPolicy() {
		return policyRepository.findOne(CENTRAL_PUMA_PDP_POLICY_ID);
	}

	@Override
	public void storeCentralPUMAPDPPolicy(String contents) {
		Policy cpp = policyRepository.findOne(CENTRAL_PUMA_PDP_POLICY_ID);
		if(cpp == null) {
			cpp = new Policy(CENTRAL_PUMA_PDP_POLICY_ID, PolicyType.MIDDLEWARE, null, contents);
		}
		cpp.setContent(contents);
		policyRepository.save(cpp);
	}

	@Override
	public List<Policy> getPolicies(Organization organization) {
		return this.policyDAO.getPolicies(organization);
	}

	@Override
	public void storePolicy(Policy policy) {
		this.policyRepository.save(policy);
	}

	@Override
	public void removePolicy(String policyId) {
		this.policyRepository.delete(policyId);
	}
	
	
}
