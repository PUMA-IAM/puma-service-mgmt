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
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import puma.sp.mgmt.model.attribute.AttributeFamily;
import puma.sp.mgmt.model.organization.Organization;

@Repository
public class AttributeFamilyDAOImpl implements AttributeFamilyDAO {

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public List<AttributeFamily> findAllOrganization(Organization org) {
		if (org == null) {
			throw new RuntimeException("Could not execute query: null pointer organization");
		}
		TypedQuery<AttributeFamily> query = null;
		query = em.createNamedQuery("AttributeFamily.byOrganization", AttributeFamily.class);
		query.setParameter("organization", org);
		return query.getResultList();
	}

	@Override
	public List<AttributeFamily> byName(String xacmlIdentifier) {
		if (xacmlIdentifier == null) {
			throw new RuntimeException("Could not execute query: null pointer xacml id");
		}
		TypedQuery<AttributeFamily> query = null;
		query = em.createNamedQuery("AttributeFamily.byOrganization", AttributeFamily.class);
		query.setParameter("name", xacmlIdentifier);
		return query.getResultList();
	}	
}
