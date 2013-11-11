package puma.sp.mgmt.repositories.organization;

import org.springframework.data.jpa.repository.JpaRepository;
import puma.sp.mgmt.model.organization.Tenant;

public interface TenantRepository extends JpaRepository<Tenant, Long> {
	// Not sure if this resolves to a NamedQuery, reading points:
	//		http://docs.spring.io/spring-data/data-jpa/docs/1.0.0.RELEASE/reference/html/#jpa.query-methods.named-queries
	//		http://forum.spring.io/forum/spring-projects/data/105012-jparepository-call-a-named-query-spring-data-jpa
}
