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
package puma.sp.mgmt.model.policy;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import puma.sp.mgmt.model.organization.PolicyLangType;
import puma.sp.mgmt.model.organization.Tenant;

@Entity
@IdClass(Policy.Key.class)
@NamedQueries({
	@NamedQuery(name = "Policy.all", query = "SELECT p FROM Policy p"),
	@NamedQuery(name = "Policy.byId", query = "SELECT p FROM Policy p WHERE p.id = :id"),
	@NamedQuery(name = "Policy.byOrganization", query = "SELECT p FROM Policy p WHERE p.definingOrganization = :organization")	
})
public class Policy {
	
	@Id
	private String id;
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getId() {
		return this.id;
	}
	
	@Id
	@Enumerated(EnumType.STRING)
	private PolicyLangType langType;
	
	public void setLangType(PolicyLangType langType) {
		this.langType = langType;
	}
	
	public PolicyLangType getLangType() {
		return this.langType;
	}

	@Lob
	private String content;
	
	public void setContent(String content) {
		this.content = content;
	}
	
	public String getContent() {
		return this.content;
	}
	
	@Enumerated(EnumType.STRING)
	private PolicyType policyType;
	
	public void setPolicyType(PolicyType policyType) {
		this.policyType = policyType;
	}
	
	public PolicyType getPolicyType() {
		return this.policyType;
	}
	
	@ManyToOne
	private Tenant definingOrganization;		// Should be of type Organization, which has subclasses [Provider?] Application Provider, Middleware Provider and Tenant.
	
	public void setDefiningOrganization(Tenant organization) {
		this.definingOrganization = organization;
	}
	
	public Tenant getDefiningOrganization() {
		return this.definingOrganization;
	}
	
	public String toXACML() {
		// Currently, we suppose the policy is stored as a XACML representation
		return this.getContent();
	}
	
	public Policy() {
		// default constructor for the JPA
	}
	
	public Policy(String id, PolicyLangType langType, PolicyType policyType, Tenant definingOrganization, String content) {
		this.id = id;
		this.langType = langType;
		this.policyType = policyType;
		this.definingOrganization = definingOrganization;
		this.content = content;
	}
	
	public static class Key implements Serializable {
		private static final long serialVersionUID = 1L;
		
		private String id;
		private PolicyLangType langType;
		
		public Key() {
			
		}
		
		public Key(String id, PolicyLangType langType) {
			this.id = id;
			this.langType = langType;
		}
		
		public void setId(String id) {
			this.id = id;
		}
		
		public String getId() {
			return id;
		}
		
		public void setLangType(PolicyLangType langType) {
			this.langType = langType;
		}
		
		public PolicyLangType getLangType() {
			return langType;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((id == null) ? 0 : id.hashCode());
			result = prime * result + ((langType == null) ? 0 : langType.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Key other = (Key) obj;
			if (id == null) {
				if (other.id != null)
					return false;
			} else if (!id.equals(other.id))
				return false;
			if (langType != other.langType)
				return false;
			return true;
		}
	}
	
}
