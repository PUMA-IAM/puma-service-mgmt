package puma.sp.mgmt.repositories.user.group;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import puma.sp.mgmt.model.user.Group;
import puma.sp.mgmt.model.user.User;

@Service
public class GroupServiceImpl implements GroupService {
	@Autowired
	private GroupRepository repo;
	
	@Override
	public void addGroup(Group group) {
		this.repo.saveAndFlush(group);
	}

	@Override
	public void removeGroup(Long id) {
		this.repo.delete(id);
	}

	@Override
	public void assignUser(Group group, User user) {
		group.assign(user);
		this.repo.saveAndFlush(group);
	}

	@Override
	public void resignUser(Group group, User user) {
		group.resign(user);
		this.repo.saveAndFlush(group);
	}

	@Override
	public List<Group> all() {
		return this.repo.findAll();
	}

	@Override
	public Group findOne(Long id) {
		return this.repo.findOne(id);
	}

}
