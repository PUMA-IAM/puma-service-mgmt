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
package puma.sp.mgmt.model.organization;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

import puma.sp.mgmt.model.attribute.AttributeFamily;


/**
 * Model class for an organization. Examples of organizations modeled by the PUMA service are the application providers, 
 * their tenants, or any other organization for which the PUMA service might want to reason on access control about.
 * 
 * Note: There should always be an organization called "provider".
 * 
 * @author Jasper Bogaerts
 *
 */
@Entity
@NamedQueries({
	@NamedQuery(name = "Organization.byName", query = "SELECT t FROM Organization t WHERE t.name = :name")
	})
public class Organization {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
    @Column(unique=true)
	private String name;
    
    @OneToMany(cascade=CascadeType.ALL, mappedBy="definedBy", fetch=FetchType.EAGER)
    private List<AttributeFamily> attributeFamilies;

	public Long getId() {
		return this.id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setName(String name) {
		this.name = name;
	}  
    
    public List<AttributeFamily> getAttributeFamilies() {
		return attributeFamilies;
	}

	public void setAttributeFamilies(List<AttributeFamily> attributeFamilies) {
		this.attributeFamilies = attributeFamilies;
	}
	
	public Organization() {
		
	}
	
	public Organization(String name) {
		this.name = name;
	}
}
