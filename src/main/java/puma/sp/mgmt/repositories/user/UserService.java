package puma.sp.mgmt.repositories.user;

import java.util.List;
import puma.sp.mgmt.model.organization.Tenant;
import puma.sp.mgmt.model.user.User;

public interface UserService {
	public void addUser(User user);
	public void deleteUser(Long userId);
	public User getUserById(Long id);
	public List<User> getAllUsers();
	public User byNameTenantNULL(String name);
	public User byNameTenant(String name, Tenant tenant);
	public List<User> byTenant(Tenant tenant);
	public User byId(Long id);
}
