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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import puma.sp.mgmt.model.organization.Tenant;
import puma.sp.mgmt.model.user.User;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserDAO userDAO;
	
	@Override
	public void addUser(User user) {
		if (this.userDAO.byNameTenant(user.getLoginName(), user.getTenant()) == null)
			this.userRepository.saveAndFlush(user);
		else
			throw new RuntimeException("Could not add two users with the same login name and affiliated organization");
	}

	@Override
	public void deleteUser(Long userId) {
		this.userRepository.delete(userId);
	}

	@Override
	public User getUserById(Long id) {
		return this.userRepository.findOne(id);
	}

	@Override
	public List<User> getAllUsers() {
		return this.userRepository.findAll();
	}

	@Override
	public User byNameTenantNULL(String name) {
		return this.userDAO.byNameTenant(name, null);
	}

	@Override
	public User byNameTenant(String name, Tenant tenant) {
		return this.userDAO.byNameTenant(name, tenant);
	}

	@Override
	public List<User> byTenant(Tenant tenant) {
		return this.userDAO.byTenant(tenant);
	}

	@Override
	public User byId(Long id) {
		return this.userRepository.findOne(id);
	}
}
