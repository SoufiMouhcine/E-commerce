/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author nos
 */
@Entity
@Table(name = "commande")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Commande.findAll", query = "SELECT c FROM Commande c")
    , @NamedQuery(name = "Commande.findByIdProduit", query = "SELECT c FROM Commande c WHERE c.commandePK.idProduit = :idProduit")
    , @NamedQuery(name = "Commande.findByLogin", query = "SELECT c FROM Commande c WHERE c.commandePK.login = :login")
    , @NamedQuery(name = "Commande.findByAdressLivraison", query = "SELECT c FROM Commande c WHERE c.adressLivraison = :adressLivraison")
    , @NamedQuery(name = "Commande.findByVilleLivraison", query = "SELECT c FROM Commande c WHERE c.villeLivraison = :villeLivraison")
    , @NamedQuery(name = "Commande.findByPayeLivraison", query = "SELECT c FROM Commande c WHERE c.payeLivraison = :payeLivraison")
    , @NamedQuery(name = "Commande.findByQte", query = "SELECT c FROM Commande c WHERE c.qte = :qte")
    , @NamedQuery(name = "Commande.findByEtatlivraison", query = "SELECT c FROM Commande c WHERE c.etatlivraison = :etatlivraison")
    , @NamedQuery(name = "Commande.findByEtatreglement", query = "SELECT c FROM Commande c WHERE c.etatreglement = :etatreglement")})
public class Commande implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected CommandePK commandePK;
    @Size(max = 255)
    @Column(name = "adressLivraison")
    private String adressLivraison;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "villeLivraison")
    private String villeLivraison;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "payeLivraison")
    private String payeLivraison;
    @Basic(optional = false)
    @NotNull
    @Column(name = "qte")
    private int qte;
    @Column(name = "etatlivraison")
    private Boolean etatlivraison;
    @Column(name = "etatreglement")
    private Boolean etatreglement;
    @JoinColumn(name = "idProduit", referencedColumnName = "idProduit", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Produit produit;
    @JoinColumn(name = "login", referencedColumnName = "login", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private User user;

    public Commande() {
    }

    public Commande(CommandePK commandePK) {
        this.commandePK = commandePK;
    }

    public Commande(CommandePK commandePK, String villeLivraison, String payeLivraison, int qte) {
        this.commandePK = commandePK;
        this.villeLivraison = villeLivraison;
        this.payeLivraison = payeLivraison;
        this.qte = qte;
    }

    public Commande(int idProduit, String login) {
        this.commandePK = new CommandePK(idProduit, login);
    }

    public CommandePK getCommandePK() {
        return commandePK;
    }

    public void setCommandePK(CommandePK commandePK) {
        this.commandePK = commandePK;
    }

    public String getAdressLivraison() {
        return adressLivraison;
    }

    public void setAdressLivraison(String adressLivraison) {
        this.adressLivraison = adressLivraison;
    }

    public String getVilleLivraison() {
        return villeLivraison;
    }

    public void setVilleLivraison(String villeLivraison) {
        this.villeLivraison = villeLivraison;
    }

    public String getPayeLivraison() {
        return payeLivraison;
    }

    public void setPayeLivraison(String payeLivraison) {
        this.payeLivraison = payeLivraison;
    }

    public int getQte() {
        return qte;
    }

    public void setQte(int qte) {
        this.qte = qte;
    }

    public Boolean getEtatlivraison() {
        return etatlivraison;
    }

    public void setEtatlivraison(Boolean etatlivraison) {
        this.etatlivraison = etatlivraison;
    }

    public Boolean getEtatreglement() {
        return etatreglement;
    }

    public void setEtatreglement(Boolean etatreglement) {
        this.etatreglement = etatreglement;
    }

    public Produit getProduit() {
        return produit;
    }

    public void setProduit(Produit produit) {
        this.produit = produit;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (commandePK != null ? commandePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Commande)) {
            return false;
        }
        Commande other = (Commande) object;
        if ((this.commandePK == null && other.commandePK != null) || (this.commandePK != null && !this.commandePK.equals(other.commandePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jpa.Commande[ commandePK=" + commandePK + " ]";
    }
    
}
