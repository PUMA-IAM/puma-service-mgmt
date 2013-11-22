package puma.sp.mgmt.repositories.attribute;

import org.springframework.data.jpa.repository.JpaRepository;

import puma.sp.mgmt.model.attribute.AttributeFamily;

public interface AttributeFamilyRepository extends JpaRepository<AttributeFamily, Long> {
}
