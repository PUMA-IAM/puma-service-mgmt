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
package puma.sp.mgmt.repositories.user;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import puma.sp.mgmt.model.organization.Tenant;
import puma.sp.mgmt.model.user.User;

@Repository
public class UserDAOImpl implements UserDAO {

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public User byNameTenant(String name, Tenant tenant) {
		TypedQuery<User> query = null;
		if (tenant != null) {
			query = em.createNamedQuery("User.byNameTenant", User.class);
			query.setParameter("tenant", tenant);
		} else {
			query = em.createNamedQuery("User.byNameTenantNULL", User.class);
		}		
		query.setParameter("name", name);
		List<User> results = query.getResultList();
		if (results.size() > 1)
			throw new RuntimeException("Multiple users found with name " + name + " with tenant " + tenant==null?"null":tenant.getName() + ".");
		if (results.isEmpty())
			return null;
		return results.get(0);
	}

	@Override
	public List<User> byTenant(Tenant tenant) {
		TypedQuery<User> query = em.createNamedQuery("User.byTenant", User.class);
		query.setParameter("tenant", tenant);
		return query.getResultList();
	}

}
