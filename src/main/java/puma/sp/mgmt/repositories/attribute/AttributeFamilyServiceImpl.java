package puma.sp.mgmt.repositories.attribute;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import puma.sp.mgmt.model.attribute.AttributeFamily;
import puma.sp.mgmt.model.organization.Organization;
import puma.sp.mgmt.repositories.organization.OrganizationService;

@Service
public class AttributeFamilyServiceImpl implements AttributeFamilyService {

	private static final String PROVIDER_NAME = "provider";
	
	@Autowired
	private AttributeFamilyRepository attributeFamilyRepository;
	
	@Autowired
	private AttributeFamilyDAO attributeFamilyDAO;
	
	@Autowired
	private OrganizationService organizationService;
	

	@Override
	public void add(AttributeFamily af) {
		attributeFamilyRepository.saveAndFlush(af);
	}

	@Override
	public void delete(Long af) {
		this.attributeFamilyRepository.delete(af);
	}

	@Override
	public AttributeFamily findOne(Long id) {
		return attributeFamilyRepository.findOne(id);
	}

	@Override
	public List<AttributeFamily> findAll() {
		return this.attributeFamilyRepository.findAll();
	}

	@Override
	public List<AttributeFamily> findAllOrganization(Organization org) {
		return this.attributeFamilyDAO.findAllOrganization(org);
	}

	@Override
	public List<AttributeFamily> findAllOrganizationProvider(Organization org) {
		List<AttributeFamily> result = this.attributeFamilyDAO.findAllOrganization(this.organizationService.byName(PROVIDER_NAME));
		result.addAll(this.attributeFamilyDAO.findAllOrganization(org));
		return result;
	}

	@Override
	public List<AttributeFamily> byName(String xacmlIdentifier) {
		return this.attributeFamilyDAO.byName(xacmlIdentifier);
	}
}
