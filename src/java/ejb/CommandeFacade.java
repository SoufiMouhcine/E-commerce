/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.persistence.Query;
import jpa.Commande;
import jpa.CommandePK;
import jpa.Produit;
import jpa.User;

/**
 *
 * @author nos
 */
@Stateless
public class CommandeFacade extends AbstractFacade<Commande> {

    @PersistenceContext(unitName = "ecommercev0.02PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CommandeFacade() {
        super(Commande.class);
    }
    public void ajouterCmd( User user,Produit produit) {
        
    try{em.createNativeQuery("INSERT INTO Commande (idProduit,login) VALUES (?,?)")
      .setParameter(1, produit.getIdProduit())
      .setParameter(2, user.getLogin())
      .executeUpdate();}  catch(Exception e) {
        
    }
}
    @Transactional
        public Commande deleteCmd( User user,Produit produit) {
            CommandePK IDcmd= new CommandePK( produit.getIdProduit() , user.getLogin());
      try{Query q= em.createQuery("select DISTINCT c from Commande AS c, Produit AS p , User AS u  where c.user.login =:login AND c.produit.idProduit=:id");
      q.setParameter("login",user.getLogin());
      q.setParameter("id",produit.getIdProduit());
      return (Commande) q.getSingleResult(); }  catch(NoResultException e) {
        return null;
    }
}
        //pour passeer commande 
    public void acheter( User user,Commande cmd) {
     try{ em.createQuery("UPDATE Commande AS c SET c.etatreglement=true ,c.payeLivraison="+cmd.getPayeLivraison()+
            ",c.villeLivraison="+cmd.getVilleLivraison()+
            ",c.adressLivraison="+cmd.getAdressLivraison()+ 
            " WHERE c.user.login=:login")
      .setParameter("login", user.getLogin())
      .executeUpdate();
      }  catch(NoResultException e) {
        
    }
}
        public  List<Produit> cartbyUser(String login){
                   try{ 
            Query query = em.createQuery("SELECT DISTINCT p FROM Produit p ,User u,Commande c  WHERE c.user.login= :login and p.idProduit=c.produit.idProduit and c.etatreglement='false' ");
        query.setParameter("login",login);
	    List<Produit> entity=query.getResultList();
	  
		return entity;
        }  catch(NoResultException e) {
        return null;
    }
               }

                       
}
