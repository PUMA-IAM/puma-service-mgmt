package puma.sp.mgmt.repositories.user.group;

import java.util.List;

import puma.sp.mgmt.model.user.Group;
import puma.sp.mgmt.model.user.User;

public interface GroupService {
	
	public void addGroup(Group group);
	public void removeGroup(Long id);
	
	public void assignUser(Group group, User user);
	public void resignUser(Group group, User user);
	
	public List<Group> all();
	public Group findOne(Long id);
}
