/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author nos
 */
@Entity
@Table(name = "marque")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Marque.findAll", query = "SELECT m FROM Marque m")
    , @NamedQuery(name = "Marque.findByIdMarque", query = "SELECT m FROM Marque m WHERE m.idMarque = :idMarque")
    , @NamedQuery(name = "Marque.findByMarque", query = "SELECT m FROM Marque m WHERE m.marque = :marque")
    , @NamedQuery(name = "Marque.findByDiscription", query = "SELECT m FROM Marque m WHERE m.discription = :discription")
    , @NamedQuery(name = "Marque.findByLogo", query = "SELECT m FROM Marque m WHERE m.logo = :logo")})
public class Marque implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "marque")
    private String marque;
    @Size(max = 255)
    @Column(name = "discription")
    private String discription;
    @Size(max = 100)
    @Column(name = "logo")
    private String logo;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idMarque")
    private Integer idMarque;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idMarque")
    private List<Produit> produitList;

    public Marque() {
    }

    public Marque(Integer idMarque) {
        this.idMarque = idMarque;
    }

    public Marque(Integer idMarque, String marque) {
        this.idMarque = idMarque;
        this.marque = marque;
    }

    public Integer getIdMarque() {
        return idMarque;
    }

    public void setIdMarque(Integer idMarque) {
        this.idMarque = idMarque;
    }


    @XmlTransient
    public List<Produit> getProduitList() {
        return produitList;
    }

    public void setProduitList(List<Produit> produitList) {
        this.produitList = produitList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idMarque != null ? idMarque.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Marque)) {
            return false;
        }
        Marque other = (Marque) object;
        if ((this.idMarque == null && other.idMarque != null) || (this.idMarque != null && !this.idMarque.equals(other.idMarque))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jpa.Marque[ idMarque=" + idMarque + " ]";
    }

    public String getMarque() {
        return marque;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public String getDiscription() {
        return discription;
    }

    public void setDiscription(String discription) {
        this.discription = discription;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }
    
}
