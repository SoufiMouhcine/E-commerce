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
import javax.persistence.Query;
import jpa.Produit;

/**
 *
 * @author nos
 */
@Stateless
public class ProduitFacade extends AbstractFacade<Produit> {

    @PersistenceContext(unitName = "ecommercev0.02PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProduitFacade() {
        super(Produit.class);
    }
               public  List<Produit> findbyName(String libelle) {
    	try{ 
            Query query = em.createQuery("SELECT u FROM Produit u WHERE u.libelle like :libelle");
        query.setParameter("libelle","%"+libelle+"%");
	    List<Produit> entity=query.getResultList();
	  
		return entity;
        }  catch(NoResultException e) {
        return null;
    }
	}
     public  List<Produit> top(){
                   try{ 
            Query query = em.createQuery("SELECT c.produit  FROM Produit AS p,Commande AS c WHERE p.idProduit=c.produit.idProduit GROUP BY c.produit order by COUNT(c.produit) DESC");
	    
	List<Produit> entity=query.setMaxResults(5).getResultList();
         
		return entity;
        }  catch(NoResultException e) {
        return null;
    }
               }

    
}
