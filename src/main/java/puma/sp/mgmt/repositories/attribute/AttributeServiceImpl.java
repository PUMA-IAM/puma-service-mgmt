package puma.sp.mgmt.repositories.attribute;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import puma.sp.mgmt.model.attribute.Attribute;

@Service
public class AttributeServiceImpl implements AttributeService {
	
	@Autowired
	private AttributeRepository attributeRepository;
	
	@Autowired
	private AttributeDAO attributeDAO;

	@Override
	public Attribute findOne(Long id) {
		return this.attributeRepository.findOne(id);
	}

	@Override
	public Boolean exists(Long id) {
		return this.attributeRepository.exists(id);
	}

	@Override
	public void addAttribute(Attribute attribute) {
		this.attributeRepository.saveAndFlush(attribute);
	}

	@Override
	public void deleteAttribute(Attribute attribute) {
		this.attributeRepository.delete(attribute);
	}

}
