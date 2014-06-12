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

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

import puma.sp.mgmt.model.application.Service;
import puma.sp.mgmt.model.user.User;

/**
 * 
 * @author jasper
 */
@Entity
@NamedQueries({
		@NamedQuery(name = "Tenant.all", query = "SELECT t FROM Tenant t"),
		@NamedQuery(name = "Tenant.byId", query = "SELECT t FROM Tenant t WHERE t.id = :id"),
		@NamedQuery(name = "Tenant.byName", query = "SELECT t FROM Tenant t WHERE t.name = :name"),
		@NamedQuery(name = "Tenant.subtenants", query = "SELECT t FROM Tenant t WHERE t.superTenant = :tenant") })
public class Tenant extends Organization {
	
	private TenantMgmtType managementType; // For Federated, hierarchical
											// authentication use IdP Proxying
											// (https://spaces.internet2.edu/display/GS/SAMLIdPProxy)

	private String authnRequestEndpoint;

	private String attrRequestEndpoint;

	private String authzRequestEndpoint;

	private String identityProviderPublicKey;

	@ManyToMany(targetEntity = Service.class)
	private List<Service> subscribedTo;

	@ManyToOne(cascade={})
	private Tenant superTenant;
	
	@OneToMany(cascade=CascadeType.ALL, mappedBy="superTenant", fetch=FetchType.EAGER)
	private Set<Tenant> subtenants;

	@OneToMany(cascade = CascadeType.REMOVE, mappedBy="tenant", fetch=FetchType.EAGER)
	private Set<User> users;

	public Tenant() {
	}

	/**
	 * Easier-to-use constructor for controllers. Users, subtenants, super
	 * tenant etc are kept empty.
	 * 
	 * @return
	 */
	public Tenant(String name, TenantMgmtType mgmtType, String authnEndpoint,
			String idpPublicKey, String attrEndpoint, String authzEndpoint) {
		super(name);
		this.managementType = mgmtType;
		this.authnRequestEndpoint = authnEndpoint;
		this.identityProviderPublicKey = idpPublicKey;
		this.attrRequestEndpoint = attrEndpoint;
		this.authzRequestEndpoint = authzEndpoint;
		
		this.superTenant = null;
		this.subtenants = new HashSet<Tenant>();
		this.subscribedTo = new LinkedList<Service>();
		this.users = new HashSet<User>();
	}

	public Set<User> getUsers() {
		return this.users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

	public String getAuthnRequestEndpoint() {
		return this.authnRequestEndpoint;
	}

	public void setAuthnRequestEndpoint(String redirect) {
		this.authnRequestEndpoint = redirect;
	}

	public String getAttrRequestEndpoint() {
		return this.attrRequestEndpoint;
	}

	public void setAttrRequestEndpoint(String endpoint) {
		this.attrRequestEndpoint = endpoint;
	}

	public String getAuthzRequestEndpoint() {
		return this.authzRequestEndpoint;
	}

	public void setAuthzRequestEndpoint(String endpoint) {
		this.authzRequestEndpoint = endpoint;
	}

	public TenantMgmtType getManagementType() {
		return managementType;
	}

	public void setManagementType(TenantMgmtType managementType) {
		this.managementType = managementType;
	}

	/**
	 * Returns whether authentication is locally managed for this tenant, or in
	 * a federated way.
	 * 
	 * @return
	 */
	public Boolean isAuthenticationLocallyManaged() {
		return this.getManagementType() == TenantMgmtType.Locally;
	}

	/**
	 * Returns whether authorization is locally managed for this tenant, or in a
	 * federated way.
	 * 
	 * @return
	 */
	public Boolean isAuthorizationLocallyManaged() {
		return this.getManagementType() != TenantMgmtType.FederatedAuthorization;
	}

	public Boolean hasFederatedAuthorization() {
		return this.getAuthzRequestEndpoint() != null;
	}

	public String getIdentityProviderPublicKey() {
		return this.identityProviderPublicKey;
	}

	public void setIdentityProviderPublicKey(String key) {
		this.identityProviderPublicKey = key;
	}

	public List<Service> getSubscribedTo() {
		return this.subscribedTo;
	}

	public void setSubscribedTo(List<Service> services) {
		this.subscribedTo = services;
	}

	/*
	 * public Boolean isSubscribedToApplication(Application app) { for (Service
	 * service: this.getSubscribedTo()) if (app.getServices().contains(service))
	 * return true; return false; }
	 */

	public Tenant getSuperTenant() {
		return this.superTenant;
	}

	public Set<Tenant> getSubtenants() {
		return subtenants;
	}

	public void setSubtenants(Set<Tenant> subtenants) {
		this.subtenants = subtenants;
	}

	public void setSuperTenant(Tenant tenant) {
		if (tenant != null && tenant.hasAncestor(this))
			throw new RuntimeException(
					"Could not set new supertenant, already amongst ancestors");
		this.superTenant = tenant;
	}

	public Boolean hasAncestor(Tenant other) {
		Tenant check = this.getSuperTenant();
		while (check != null) {
			if (check.equals(other))
				return true;
			check = check.getSuperTenant();
		}
		return false;
	}

	public String toHierarchy() {
		String result = this.getName();
		Tenant current = this.getSuperTenant();
		while (current != null) {
			result = result + "." + current.getName();
			current = current.getSuperTenant();
		}
		return result;
	}

	@Override
	public boolean equals(Object o) {
		Tenant other;
		if (o instanceof Tenant) {
			other = (Tenant) o;
			return this.getId() == other.getId();
		}
		return false;
	}

	@Override
	public int hashCode() {
		return this.getId().intValue();
	}
}
