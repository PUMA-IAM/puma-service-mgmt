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
package puma.sp.mgmt.repositories.user.group;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import puma.sp.mgmt.model.user.Group;
import puma.sp.mgmt.model.user.User;

@Service
public class GroupServiceImpl implements GroupService {
	@Autowired
	private GroupRepository repo;
	
	@Override
	public void addGroup(Group group) {
		this.repo.saveAndFlush(group);
	}

	@Override
	public void removeGroup(Long id) {
		this.repo.delete(id);
	}

	@Override
	public void assignUser(Group group, User user) {
		group.assign(user);
		this.repo.saveAndFlush(group);
	}

	@Override
	public void resignUser(Group group, User user) {
		group.resign(user);
		this.repo.saveAndFlush(group);
	}

	@Override
	public List<Group> all() {
		return this.repo.findAll();
	}

	@Override
	public Group findOne(Long id) {
		return this.repo.findOne(id);
	}

}
