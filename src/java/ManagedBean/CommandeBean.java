/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagedBean;

import ejb.CommandeFacade;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import jpa.Commande;
import jpa.Produit;
import jpa.User;

/**
 *
 * @author nos
 */
@ManagedBean
@SessionScoped
public class CommandeBean implements Serializable{
@EJB
private CommandeFacade CommandeEJB;
private Commande CommandeEntity;
private Commande selectedCommandeEntity;
private static final long serialVersionUID = 7765876811740798584L;
@PostConstruct
    public void init() {
        CommandeEntity = new Commande();
        selectedCommandeEntity = new Commande();
    }

    public Commande getCommandeEntity() {
        return CommandeEntity;
    }

    public void setCommandeEntity(Commande CommandeEntity) {
        this.CommandeEntity = CommandeEntity;
    }

    public Commande getSelectedCommandeEntity() {
        return selectedCommandeEntity;
    }

    public void setSelectedCommandeEntity(Commande selectedCommandeEntity) {
        this.selectedCommandeEntity = selectedCommandeEntity;
    }
    /**
     * Creates a new instance of CommandeBean
     */
    public CommandeBean() {
    }
    
    public void ajouterCart(User user,Produit produit){
        
        CommandeEJB.ajouterCmd(user, produit);
    }
    public String delete(User user,Produit produit){
        CommandeEJB.remove(CommandeEJB.deleteCmd(user, produit));
        return "panier";
    }
    ///commande bien passé
    public String passer(User user,Commande cmd){
        CommandeEJB.acheter(user,cmd);
        return "facture";
    }
        public List<Produit> panier(String login) {
        return CommandeEJB.cartbyUser(login);
    }
    ///pour calculer la some des prix //
    public Float some(String login){
        List<Produit> items =  panier(login);
        Float s =new Float(0);
        for(Produit p:items){
            s+=p.getPrixAchat();
        }
        return s;
    }
    
    
    
}
