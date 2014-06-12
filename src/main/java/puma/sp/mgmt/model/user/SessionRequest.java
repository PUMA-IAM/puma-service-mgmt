/*******************************************************************************
 * Copyright 2014 KU Leuven Research and Developement - iMinds - Distrinet 
 * 
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 * 
 *        http://www.apache.org/licenses/LICENSE-2.0
 * 
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 *    
 *    Administrative Contact: dnet-project-office@cs.kuleuven.be
 *    Technical Contact: maarten.decat@cs.kuleuven.be
 *    Author: maarten.decat@cs.kuleuven.be
 ******************************************************************************/
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package puma.sp.mgmt.model.user;

import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Class maintaining the unique session request for a user which is in the process of logging on remotely. 
 * @author Jasper Bogaerts
 */
@Entity
@NamedQueries({
        @NamedQuery(name = "SessionRequest.all", query = "SELECT s FROM SessionRequest s"),
        @NamedQuery(name = "SessionRequest.bySessionId", query = "SELECT s FROM SessionRequest s WHERE s.requestId = :id")
	})
public class SessionRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String requestId;
    private String relayState;
    @Basic(optional = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date generationTime;   
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public Date getGenerationTime() {
        return this.generationTime;
    }
    
    public void setGenerationTime(Date d) {
        this.generationTime = d;
    }
    
    public String getRequestId() {
        return this.requestId;
    }
    
    public void setRequestId(String id) {
        this.requestId = id;
    }
    
    public String getRelayState() {
        return this.relayState;
    }
    
    public void setRelayState(String relayState) {
        this.relayState = relayState;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof SessionRequest)) {
            return false;
        }
        SessionRequest other = (SessionRequest) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.SessionRequest[ id=" + id + " ]";
    }
    
}
