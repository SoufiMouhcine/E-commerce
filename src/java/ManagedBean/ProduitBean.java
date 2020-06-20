/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagedBean;

import ejb.ProduitFacade;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.ApplicationScoped;
import jpa.Produit;

/**
 *
 * @author nos
 */
@Named(value = "produitBean")
@ApplicationScoped
public class ProduitBean {
@EJB
private ProduitFacade ProduitEJB;
private Produit ProduitEntity;
private Produit selectedProduitEntity;
@PostConstruct
    public void init() {
        ProduitEntity = new Produit();
        selectedProduitEntity = new Produit();
    }
    public Produit getProduitEntity() {
        return ProduitEntity;
    }

    public void setProduitEntity(Produit ProduitEntity) {
        this.ProduitEntity = ProduitEntity;
    }

    public Produit getSelectedProduitEntity() {
        return selectedProduitEntity;
    }

    public void setSelectedProduitEntity(Produit selectedProduitEntity) {
        this.selectedProduitEntity = selectedProduitEntity;
    }


    public List<Produit> getProduit() {
        return ProduitEJB.findAll();
    }
    public List<Produit> search(String key) {
        return ProduitEJB.findbyName(key);
    }
    public List<Produit> top(){
        return ProduitEJB.top();
    }

    
    
    
    
           
    /**
     * Creates a new instance of ProduitBean
     */
    public ProduitBean() {
    }
    
}
