/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.davefernandes.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author dave
 */
@Entity
@Table(name = "ator")
@NamedQueries({
    @NamedQuery(name = "Ator.findAll", query = "SELECT a FROM Ator a"),
    @NamedQuery(name = "Ator.findByAtorId", query = "SELECT a FROM Ator a WHERE a.atorId = :atorId"),
    @NamedQuery(name = "Ator.findByPrimeiroNome", query = "SELECT a FROM Ator a WHERE a.primeiroNome = :primeiroNome"),
    @NamedQuery(name = "Ator.findByUltimoNome", query = "SELECT a FROM Ator a WHERE a.ultimoNome = :ultimoNome"),
    @NamedQuery(name = "Ator.findByUltimaAtualizacao", query = "SELECT a FROM Ator a WHERE a.ultimaAtualizacao = :ultimaAtualizacao")})
public class Ator implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ator_id")
    private Short atorId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "primeiro_nome")
    private String primeiroNome;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "ultimo_nome")
    private String ultimoNome;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ultima_atualizacao")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ultimaAtualizacao;

    public Ator() {
    }

    public Ator(Short atorId) {
        this.atorId = atorId;
    }

    public Ator(Short atorId, String primeiroNome, String ultimoNome, Date ultimaAtualizacao) {
        this.atorId = atorId;
        this.primeiroNome = primeiroNome;
        this.ultimoNome = ultimoNome;
        this.ultimaAtualizacao = ultimaAtualizacao;
    }

    public Short getAtorId() {
        return atorId;
    }

    public void setAtorId(Short atorId) {
        this.atorId = atorId;
    }

    public String getPrimeiroNome() {
        return primeiroNome;
    }

    public void setPrimeiroNome(String primeiroNome) {
        this.primeiroNome = primeiroNome;
    }

    public String getUltimoNome() {
        return ultimoNome;
    }

    public void setUltimoNome(String ultimoNome) {
        this.ultimoNome = ultimoNome;
    }

    public Date getUltimaAtualizacao() {
        return ultimaAtualizacao;
    }

    public void setUltimaAtualizacao(Date ultimaAtualizacao) {
        this.ultimaAtualizacao = ultimaAtualizacao;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (atorId != null ? atorId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ator)) {
            return false;
        }
        Ator other = (Ator) object;
        if ((this.atorId == null && other.atorId != null) || (this.atorId != null && !this.atorId.equals(other.atorId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.davefernandes.model.Ator[ atorId=" + atorId + " ]";
    }
    
}
