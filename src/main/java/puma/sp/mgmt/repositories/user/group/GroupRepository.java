package puma.sp.mgmt.repositories.user.group;

import org.springframework.data.jpa.repository.JpaRepository;

import puma.sp.mgmt.model.user.Group;

public interface GroupRepository extends JpaRepository<Group, Long> {

}
