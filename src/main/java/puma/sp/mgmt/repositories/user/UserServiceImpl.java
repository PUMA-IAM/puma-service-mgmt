package puma.sp.mgmt.repositories.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import puma.sp.mgmt.model.organization.Tenant;
import puma.sp.mgmt.model.user.User;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserDAO userDAO;
	
	@Override
	public void addUser(User user) {
		if (this.userDAO.byNameTenant(user.getLoginName(), user.getTenant()) == null)
			this.userRepository.saveAndFlush(user);
		else
			throw new RuntimeException("Could not add two users with the same login name and affiliated organization");
	}

	@Override
	public void deleteUser(Long userId) {
		this.userRepository.delete(userId);
	}

	@Override
	public User getUserById(Long id) {
		return this.userRepository.findOne(id);
	}

	@Override
	public List<User> getAllUsers() {
		return this.userRepository.findAll();
	}

	@Override
	public User byNameTenantNULL(String name) {
		return this.userDAO.byNameTenant(name, null);
	}

	@Override
	public User byNameTenant(String name, Tenant tenant) {
		return this.userDAO.byNameTenant(name, tenant);
	}

	@Override
	public List<User> byTenant(Tenant tenant) {
		return this.userDAO.byTenant(tenant);
	}

	@Override
	public User byId(Long id) {
		return this.userRepository.findOne(id);
	}
}
