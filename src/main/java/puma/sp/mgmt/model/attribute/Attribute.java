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
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package puma.sp.mgmt.model.attribute;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import puma.sp.mgmt.model.user.User;

/**
 *
 * @author jasper
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "Attribute.all", query = "SELECT a FROM Attribute a"),
	@NamedQuery(name = "Attribute.byFamily", query = "SELECT a FROM Attribute a WHERE a.family = :family"),
	@NamedQuery(name = "Attribute.byUser", query = "SELECT a FROM Attribute a WHERE a.user = :user"),
	@NamedQuery(name = "Attribute.byFamilyUser", query = "SELECT a FROM Attribute a WHERE a.family = :family AND a.user = :user")
	})
@Table(name = "SP_ATTR")
public class Attribute {
	// LATER Implement class hierarchy with specification to entity field.
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	private AttributeFamily family;
	
	private String value;
	
	@ManyToOne
	private User user;
	
	public Attribute() {}
	
	public Long getId() {
		return this.id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
    public void setFamily(AttributeFamily family) {
        this.family = family;
    }
        
	public AttributeFamily getFamily() {
		return this.family;
	}
        
    public void setValue(String value) {
        this.value = value;
    }
	
	public String getValue() {
		return this.value;
	}
	
	public User getUser() {
		return this.user;
	}
	
	public void setUser(User u) {
		this.user = u;
	}
}
