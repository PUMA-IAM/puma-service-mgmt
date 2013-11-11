/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package puma.sp.mgmt.model.attribute;

import java.io.Serializable;

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
	@NamedQuery(name = "Attribute.byKey", query = "SELECT a FROM Attribute a WHERE a.attributeKey = :key"),
	@NamedQuery(name = "Attribute.byUser", query = "SELECT a FROM Attribute a WHERE a.user = :user"),
	@NamedQuery(name = "Attribute.byKeyUser", query = "SELECT a FROM Attribute a WHERE a.attributeKey = :key AND a.user = :user")
	})
@Table(name = "SP_ATTR")
public class Attribute {
	// TODO Implement class hierarchy with specification to entity field.
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne
	private AttributeType attributeKey;
	private Serializable attributeValue;
	@ManyToOne
	private User user;
	
	public Attribute() {}
	
	public Long getId() {
		return this.id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
    public void setAttributeKey(AttributeType key) {
        this.attributeKey = key;
    }
        
	public AttributeType getAttributeKey() {
		return this.attributeKey;
	}
        
    public void setAttributeValue(Serializable value) {
        this.attributeValue = value;
    }
	
	public Serializable getAttributeValue() {
		return this.attributeValue;
	}
	
	public User getUser() {
		return this.user;
	}
	
	public void setUser(User u) {
		this.user = u;
	}
}