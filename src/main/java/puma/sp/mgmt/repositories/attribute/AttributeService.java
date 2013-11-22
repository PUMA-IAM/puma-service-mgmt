package puma.sp.mgmt.repositories.attribute;

import puma.sp.mgmt.model.attribute.Attribute;

public interface AttributeService {
	
	public void addAttribute(Attribute attribute);
	
	public void deleteAttribute(Attribute attribute);
	
	public Attribute findOne(Long id);
	
	public Boolean exists(Long id);
	
}
