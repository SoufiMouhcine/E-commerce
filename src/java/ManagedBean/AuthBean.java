/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagedBean;

import ejb.UserFacade;
import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import jpa.Role;
import jpa.User;
import javax.faces.context.FacesContext;
import javax.faces.application.FacesMessage;
import javax.faces.bean.SessionScoped;
/**
 *
 * @author nos
 */
@ManagedBean
@SessionScoped
public class AuthBean implements Serializable  {
@EJB
private UserFacade UserEJB;
private User UserEntity;
private User selectedUserEntity;
private Boolean islogged=false;
private Role idRole;
private static final long serialVersionUID = 7765876811740798583L;
@PostConstruct
    public void init() {
        UserEntity = new User();
        selectedUserEntity = new User();
        idRole= new Role();
        islogged=false;
    }

    public Boolean getIslogged() {
        return islogged;
    }

    public void setIslogged(Boolean islogged) {
        this.islogged = islogged;
    }

    public User getUserEntity() {
        return UserEntity;
    }

    public void setUserEntity(User UserEntity) {
        this.UserEntity = UserEntity;
    }

    public User getSelectedUserEntity() {
        return selectedUserEntity;
    }

    public void setSelectedUserEntity(User selectedUserEntity) {
        this.selectedUserEntity = selectedUserEntity;
    }
    
    public boolean verifier() throws NoSuchAlgorithmException{
        User user=UserEJB.trouver(UserEntity.getLogin(), UserEntity.getPassword());
        if(user==null){
            this.islogged=false;
            return islogged;}
        if(user!=null) {
            this.islogged=true;
            return islogged;}
        this.islogged=false;
    return islogged;
    }
    public String login() throws NoSuchAlgorithmException{
        if(verifier()==true)
            return "/customer/acceuil.xhtml?faces-redirect=true"; 
        return "login";
    }
    public String register(){
        //try {
        /*idRole.setIdRole(2);
        idRole.setRole("user");
        UserEntity.setIdRole(idRole);*/
        try{
            UserEJB.ajouter(UserEntity);
            return "index";
        }catch(Exception e){
            }
            
            return "login";
       /* } catch (Exception e) {
            return "register";
        }*/
    }
    public String logout(){
        this.islogged=false;
        return "/visitor/index.xhtml?faces-redirect = true";
    }
    public String modifier(){
        UserEJB.modifier(UserEntity);
        return "acceuil.xhtml?faces-redirect = true";
    }
    /**
     * Creates a new instance of AuthBean
     */
    public AuthBean() {
    }
    
    
}
