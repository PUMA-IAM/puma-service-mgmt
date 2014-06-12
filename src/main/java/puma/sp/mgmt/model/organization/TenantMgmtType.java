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

/**
 * Enum used to declare how a tenant is managed: 
 * - locally: completely managed @ provider
 * - federated authentication: federated authentication, but authorization @ provider
 * - federated authorization: federated authentication and federated authorization
 * @author Maarten Decat
 *
 */
public enum TenantMgmtType {
	
	Locally("Local authentication and authorization"),
	FederatedAuthentication("Federated authentication, local authorization"),
	FederatedAuthorization("Federated authentication, federated authorization");
	
	private String description;
	
	private TenantMgmtType(String desc) {
		this.description = desc;
	}
	
	public String getDescription() {
		return this.description;
	}
}
