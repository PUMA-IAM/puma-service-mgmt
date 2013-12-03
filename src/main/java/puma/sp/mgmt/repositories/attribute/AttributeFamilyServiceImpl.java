package puma.sp.mgmt.repositories.attribute;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import puma.sp.mgmt.model.attribute.AttributeFamily;

@Service
public class AttributeFamilyServiceImpl implements AttributeFamilyService {
	
	@Autowired
	private AttributeFamilyRepository attributeFamilyRepository;

	@Override
	public void add(AttributeFamily af) {
		attributeFamilyRepository.saveAndFlush(af);
	}

	@Override
	public void delete(AttributeFamily af) {
		attributeFamilyRepository.delete(af);
	}

	@Override
	public AttributeFamily findOne(Long id) {
		return attributeFamilyRepository.findOne(id);
	}

	@Override
	public List<AttributeFamily> findAll() {
		return this.attributeFamilyRepository.findAll();
	}

}
