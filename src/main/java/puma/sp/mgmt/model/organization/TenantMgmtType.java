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
	
	Locally,
	FederatedAuthentication,
	FederatedAuthorization();

}
