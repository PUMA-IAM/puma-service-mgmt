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

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import puma.sp.mgmt.model.attribute.Attribute;
import puma.sp.mgmt.model.organization.Tenant;
import puma.util.PasswordHasher;

/**
 * A user in 
 * @author jasper
 */
@Entity
@NamedQueries({
	@NamedQuery(name = "User.all", query = "SELECT u FROM User u"),
	@NamedQuery(name = "User.byId", query = "SELECT u FROM User u WHERE u.id = :id"),
	@NamedQuery(name = "User.byTenant", query = "SELECT u FROM User u WHERE u.tenant = :tenant"),	
	@NamedQuery(name = "User.byNameTenant", query = "SELECT u FROM User u WHERE u.loginName = :name AND u.tenant = :tenant"),	
	@NamedQuery(name = "User.byNameTenantNULL", query = "SELECT u FROM User u WHERE u.loginName = :name AND u.tenant IS NULL")
	})
@Table(name = "APPLICATION_USER")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;	
	private String loginName;
	@Column(length = 100)
	private String password;
	
    @OneToMany(cascade = CascadeType.REMOVE, mappedBy="user", fetch=FetchType.EAGER)
    private Set<Attribute> attributes;
    
	@ManyToOne
    private Tenant tenant;
        
	public User() {}
	public Long getId() {
		return this.id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getLoginName() {
		return this.loginName;
	}
	
	public void setLoginName(String name) {
		this.loginName = name;
	}
	
	public void setPassword(String password) throws NoSuchAlgorithmException, InvalidKeySpecException {
    	this.password = password;
	}
	
	public String getPassword() {
		return this.password;
	}
	
    public Set<Attribute> getAttributes() {
        return this.attributes;
    }
    
    public void setAttributes(Set<Attribute> attributes) {
        this.attributes = attributes;
    }
    
    public List<Attribute> getAttribute(String name) {
    	// Probably not most efficient way to do so, but will do for demo
    	List<Attribute> result = new ArrayList<Attribute>();
    	for (Attribute next: this.getAttributes())
    		if (next.getFamily().getName().equals(name))
    			result.add(next);
    	return result;
    }
    
    public void setTenant(Tenant t) {
        this.tenant = t;
        if (t != null && !t.getUsers().contains(this)) {
            t.getUsers().add(this);
        }
    }
    
    public Tenant getTenant() {
        return this.tenant;
    }
}
