package puma.sp.mgmt.repositories.user;

import java.util.List;
import puma.sp.mgmt.model.organization.Tenant;
import puma.sp.mgmt.model.user.User;

public interface UserDAO {
	public User byNameTenant(String name, Tenant object);
	public List<User> byTenant(Tenant tenant);
}
