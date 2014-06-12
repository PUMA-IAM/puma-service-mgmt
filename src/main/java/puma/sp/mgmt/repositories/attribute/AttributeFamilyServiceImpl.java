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
package puma.sp.mgmt.repositories.attribute;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import puma.sp.mgmt.model.attribute.AttributeFamily;
import puma.sp.mgmt.model.organization.Organization;
import puma.sp.mgmt.repositories.organization.OrganizationService;

@Service
public class AttributeFamilyServiceImpl implements AttributeFamilyService {

	private static final String PROVIDER_NAME = "provider";
	
	@Autowired
	private AttributeFamilyRepository attributeFamilyRepository;
	
	@Autowired
	private AttributeFamilyDAO attributeFamilyDAO;
	
	@Autowired
	private OrganizationService organizationService;
	

	@Override
	public void add(AttributeFamily af) {
		attributeFamilyRepository.saveAndFlush(af);
	}

	@Override
	public void delete(Long af) {
		this.attributeFamilyRepository.delete(af);
	}

	@Override
	public AttributeFamily findOne(Long id) {
		return attributeFamilyRepository.findOne(id);
	}

	@Override
	public List<AttributeFamily> findAll() {
		return this.attributeFamilyRepository.findAll();
	}

	@Override
	public List<AttributeFamily> findAllOrganization(Organization org) {
		return this.attributeFamilyDAO.findAllOrganization(org);
	}

	@Override
	public List<AttributeFamily> findAllOrganizationProvider(Organization org) {
		List<AttributeFamily> result = this.attributeFamilyDAO.findAllOrganization(this.organizationService.byName(PROVIDER_NAME));
		result.addAll(this.attributeFamilyDAO.findAllOrganization(org));
		return result;
	}

	@Override
	public List<AttributeFamily> byName(String xacmlIdentifier) {
		return this.attributeFamilyDAO.byName(xacmlIdentifier);
	}
}
