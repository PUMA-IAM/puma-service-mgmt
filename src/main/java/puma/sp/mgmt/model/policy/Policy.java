package puma.sp.mgmt.model.policy;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

import puma.sp.mgmt.model.organization.Tenant;

@Entity
public class Policy {
	
	@Id
	private String id;
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getId() {
		return this.id;
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
	
	public Policy(String id, PolicyType policyType, Tenant definingOrganization, String content) {
		this.id = id;
		this.policyType = policyType;
		this.definingOrganization = definingOrganization;
		this.content = content;
	}
	
}
