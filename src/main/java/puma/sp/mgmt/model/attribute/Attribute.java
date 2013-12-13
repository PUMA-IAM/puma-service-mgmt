/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package puma.sp.mgmt.model.attribute;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import puma.sp.mgmt.model.user.User;

/**
 *
 * @author jasper
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "Attribute.all", query = "SELECT a FROM Attribute a"),
	@NamedQuery(name = "Attribute.byFamily", query = "SELECT a FROM Attribute a WHERE a.family = :family"),
	@NamedQuery(name = "Attribute.byUser", query = "SELECT a FROM Attribute a WHERE a.user = :user"),
	@NamedQuery(name = "Attribute.byFamilyUser", query = "SELECT a FROM Attribute a WHERE a.family = :family AND a.user = :user")
	})
@Table(name = "SP_ATTR")
public class Attribute {
	// LATER Implement class hierarchy with specification to entity field.
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	private AttributeFamily family;
	
	private String value;
	
	@ManyToOne
	private User user;
	
	public Attribute() {}
	
	public Long getId() {
		return this.id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
    public void setFamily(AttributeFamily family) {
        this.family = family;
    }
        
	public AttributeFamily getFamily() {
		return this.family;
	}
        
    public void setValue(String value) {
        this.value = value;
    }
	
	public String getValue() {
		return this.value;
	}
	
	public User getUser() {
		return this.user;
	}
	
	public void setUser(User u) {
		this.user = u;
	}
}