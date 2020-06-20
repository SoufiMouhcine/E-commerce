/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import jpa.Genre;
import jpa.Marque;

/**
 *
 * @author nos
 */
@Stateless
public class MarqueFacade extends AbstractFacade<Marque> {

    @PersistenceContext(unitName = "ecommercev0.02PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MarqueFacade() {
        super(Marque.class);
    }
               public  Marque findbyid(Integer idMarque) {
		Marque entity = new Marque();
    	try{ 
            Query query = em.createQuery("SELECT u FROM Marque u WHERE u.idMarque = :idMarque");
        query.setParameter("idMarque",idMarque);
	    entity=(Marque) query.getSingleResult();
	  
		return entity;
        }  catch(NoResultException e) {
        return null;
    }
	}
    
}
