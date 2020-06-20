/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author nos
 */
@Entity
@Table(name = "produit")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Produit.findAll", query = "SELECT p FROM Produit p")
    , @NamedQuery(name = "Produit.findByIdProduit", query = "SELECT p FROM Produit p WHERE p.idProduit = :idProduit")
    , @NamedQuery(name = "Produit.findByLibelle", query = "SELECT p FROM Produit p WHERE p.libelle = :libelle")
    , @NamedQuery(name = "Produit.findByLogo", query = "SELECT p FROM Produit p WHERE p.logo = :logo")
    , @NamedQuery(name = "Produit.findByPrixVent", query = "SELECT p FROM Produit p WHERE p.prixVent = :prixVent")
    , @NamedQuery(name = "Produit.findByPrixAchat", query = "SELECT p FROM Produit p WHERE p.prixAchat = :prixAchat")
    , @NamedQuery(name = "Produit.findByDateEnrg", query = "SELECT p FROM Produit p WHERE p.dateEnrg = :dateEnrg")})
public class Produit implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "libelle")
    private String libelle;
    @Size(max = 100)
    @Column(name = "logo")
    private String logo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "prixVent")
    private int prixVent;
    @Basic(optional = false)
    @NotNull
    @Column(name = "prixAchat")
    private int prixAchat;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idProduit")
    private Integer idProduit;
    @Column(name = "dateEnrg")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateEnrg;
    @JoinColumn(name = "idGenre", referencedColumnName = "idGenre")
    @ManyToOne(optional = false)
    private Genre idGenre;
    @JoinColumn(name = "idMarque", referencedColumnName = "idMarque")
    @ManyToOne(optional = false)
    private Marque idMarque;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "produit")
    private List<Commande> commandeList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idProduit")
    private List<Commentaire> commentaireList;

    public Produit() {
    }

    public Produit(Integer idProduit) {
        this.idProduit = idProduit;
    }

    public Produit(Integer idProduit, String libelle, int prixVent, int prixAchat) {
        this.idProduit = idProduit;
        this.libelle = libelle;
        this.prixVent = prixVent;
        this.prixAchat = prixAchat;
    }

    public Integer getIdProduit() {
        return idProduit;
    }

    public void setIdProduit(Integer idProduit) {
        this.idProduit = idProduit;
    }


    public Date getDateEnrg() {
        return dateEnrg;
    }

    public void setDateEnrg(Date dateEnrg) {
        this.dateEnrg = dateEnrg;
    }

    public Genre getIdGenre() {
        return idGenre;
    }

    public void setIdGenre(Genre idGenre) {
        this.idGenre = idGenre;
    }

    public Marque getIdMarque() {
        return idMarque;
    }

    public void setIdMarque(Marque idMarque) {
        this.idMarque = idMarque;
    }

    @XmlTransient
    public List<Commande> getCommandeList() {
        return commandeList;
    }

    public void setCommandeList(List<Commande> commandeList) {
        this.commandeList = commandeList;
    }

    @XmlTransient
    public List<Commentaire> getCommentaireList() {
        return commentaireList;
    }

    public void setCommentaireList(List<Commentaire> commentaireList) {
        this.commentaireList = commentaireList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idProduit != null ? idProduit.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Produit)) {
            return false;
        }
        Produit other = (Produit) object;
        if ((this.idProduit == null && other.idProduit != null) || (this.idProduit != null && !this.idProduit.equals(other.idProduit))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jpa.Produit[ idProduit=" + idProduit + " ]";
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public int getPrixVent() {
        return prixVent;
    }

    public void setPrixVent(int prixVent) {
        this.prixVent = prixVent;
    }

    public int getPrixAchat() {
        return prixAchat;
    }

    public void setPrixAchat(int prixAchat) {
        this.prixAchat = prixAchat;
    }
    
}
