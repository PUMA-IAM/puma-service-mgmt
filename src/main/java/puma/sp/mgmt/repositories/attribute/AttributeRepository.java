package puma.sp.mgmt.repositories.attribute;

import org.springframework.data.jpa.repository.JpaRepository;
import puma.sp.mgmt.model.attribute.Attribute;

public interface AttributeRepository extends JpaRepository<Attribute, Long> {
}
