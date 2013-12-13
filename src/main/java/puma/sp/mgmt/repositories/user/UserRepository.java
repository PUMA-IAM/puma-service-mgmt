package puma.sp.mgmt.repositories.user;


import org.springframework.data.jpa.repository.JpaRepository;
import puma.sp.mgmt.model.user.User;

public interface UserRepository extends JpaRepository<User, Long> {
	/*
	User byNameTenantNULL(String name);
	User byNameTenant(String name, Tenant tenant);
	List<User> byTenant(Tenant tenant);
	User byId(Long id);
	List<User> all();
	*/
}
