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
package puma.sp.mgmt.model.user;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "MGMT_GROUP")
public class Group {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	
	@OneToMany
	private List<Group> children;
	
	
	@ManyToMany
	private List<User> assignedUsers;
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getId() {
		return this.id;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setChildren(List<Group> children) {
		this.children = children;
	}
	
	public List<Group> getChildren() {
		return this.children;
	}
	
	public void setAssignedUsers(List<User> users) {
		this.assignedUsers = users;
	}
	
	public List<User> getAssignedUsers() {
		return this.assignedUsers;
	}
	
	public Boolean isDescendant(Group other) {
		if (this.getChildren() == null)
			return false;
		return this.descendants().contains(other);
	}
	
	public void assign(User user) {
		for (Group next: this.descendants())
			if (next.hasUserAssigned(user))
				next.resign(user);
	}
	
	public void resign(User user) {
		if (this.hasUserAssigned(user))
			this.assignedUsers.remove(user);
	}
	
	public Boolean hasUserAssigned(User user) {
		if (this.getAssignedUsers() == null)
			return false;
		return this.getAssignedUsers().contains(user);
	}
	
	public List<Group> descendants() {
		List<Group> result = new ArrayList<Group>();
		if (this.getChildren() == null)
			return result;
		Iterator<Group> it = this.getChildren().iterator();
		while (it.hasNext()) {
			Group next = it.next();
			result.addAll(next.descendants());
			result.add(next);
		}
		return result;
	}
	
	@Override
	public boolean equals(Object other) {
		try {
			Group otherObject = (Group) other;
			return otherObject.getId().equals(this.getId());
		} catch (ClassCastException ex) {
			return false;
		}
	}
}
