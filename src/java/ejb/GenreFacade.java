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

/**
 *
 * @author nos
 */
@Stateless
public class GenreFacade extends AbstractFacade<Genre> {

    @PersistenceContext(unitName = "ecommercev0.02PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public GenreFacade() {
        super(Genre.class);
    }
           public  Genre findbyid(Integer idGenre) {
		Genre entity = new Genre();
    	try{ 
            Query query = em.createQuery("SELECT u FROM Genre u WHERE u.idGenre = :idGenre");
        query.setParameter("idGenre",idGenre);
	    entity=(Genre) query.getSingleResult();
	  
		return entity;
        }  catch(NoResultException e) {
        return null;
    }
	}
    
}
