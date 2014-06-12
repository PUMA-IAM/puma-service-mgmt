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
package puma.sp.mgmt.repositories.organization;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import puma.sp.mgmt.model.organization.Organization;

@Repository
public class OrganizationDAOImpl implements OrganizationDAO {
	
	@PersistenceContext
	private EntityManager em;
	
	@Override
	public Organization byName(String name) {
		TypedQuery<Organization> query = em.createNamedQuery("Organization.byName", Organization.class);
		query.setParameter("name", name);
		List<Organization> results = query.getResultList();
		if (results.isEmpty()) {
			return null;
		}
		if (results.size() > 1) {
			throw new RuntimeException("Multiple organizations with name " + name + " found!");
		}
		return results.get(0);
	}
}
