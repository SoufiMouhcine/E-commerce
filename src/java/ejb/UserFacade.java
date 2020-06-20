/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import jpa.Role;
import jpa.User;

/**
 *
 * @author nos
 */
@Stateless
public class UserFacade extends AbstractFacade<User> {

    @PersistenceContext(unitName = "ecommercev0.02PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UserFacade() {
        super(User.class);
    }

   public  User trouver(String username, String password) throws NoSuchAlgorithmException {
		User entity = new User();
                MessageDigest md = MessageDigest.getInstance("SHA-256");
                md.update(password.getBytes());

        byte byteData[] = md.digest();
 StringBuffer sb = new StringBuffer();
        for (int i = 0; i < byteData.length; i++) {
         sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
         
        }
    	entity.setLogin(username);
    	entity.setPassword(password);
    	try{ 
            Query query = em.createQuery("SELECT u FROM User u WHERE u.login = :login AND u.password = :password ");
        query.setParameter("login",username);
                    String st = new String(sb);
	query.setParameter("password", st);
	    entity=(User) query.getSingleResult();
	  
		return entity;
        }  catch(NoResultException e) {
        return null;
    }
	}
   public void ajouter(User user) throws NoSuchAlgorithmException{
       Role idRole=new Role();
       idRole.setIdRole(2);
                       MessageDigest md = MessageDigest.getInstance("SHA-256");
                md.update(user.getPassword().getBytes());

        byte byteData[] = md.digest();
 StringBuffer sb = new StringBuffer();
        for (int i = 0; i < byteData.length; i++) {
         sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
         String st = new String(sb);
         user.setPassword(st);
        }
       user.setIdRole(idRole);
       super.create(user);
   }
      public void modifier(User user){
       Role idRole=new Role();
       idRole.setIdRole(2);
       user.setIdRole(idRole);
       super.edit(user);
   }

/*@Transactional
public void ajouter( User user) {
    em.createNativeQuery("INSERT INTO user (login, password) VALUES (?,?)")
      .setParameter(1, user.getLogin())
      .setParameter(2, user.getPassword())
      .executeUpdate();
}*/
}
