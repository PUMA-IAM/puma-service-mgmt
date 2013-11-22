package puma.sp.mgmt.repositories.organization;

import org.springframework.data.jpa.repository.JpaRepository;

import puma.sp.mgmt.model.organization.Organization;


public interface OrganizationRepository extends JpaRepository<Organization, Long> {
}