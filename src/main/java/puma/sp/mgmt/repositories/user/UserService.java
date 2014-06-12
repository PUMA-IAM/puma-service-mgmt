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
import puma.sp.mgmt.model.organization.Tenant;
import puma.sp.mgmt.model.user.User;

public interface UserService {
	public void addUser(User user);
	public void deleteUser(Long userId);
	public User getUserById(Long id);
	public List<User> getAllUsers();
	public User byNameTenantNULL(String name);
	public User byNameTenant(String name, Tenant tenant);
	public List<User> byTenant(Tenant tenant);
	public User byId(Long id);
}
