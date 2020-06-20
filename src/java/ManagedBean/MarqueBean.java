/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagedBean;

import ejb.MarqueFacade;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.ApplicationScoped;
import jpa.Marque;

/**
 *
 * @author nos
 */
@Named(value = "marqueBean")
@ApplicationScoped
public class MarqueBean {
@EJB
private MarqueFacade MarqueEJB;
private Marque MarqueEntity;
private Marque selectedMarqueEntity;
@PostConstruct
    public void init() {
        MarqueEntity = new Marque();
        selectedMarqueEntity = new Marque();
    }

    public Marque getMarqueEntity() {
        return MarqueEntity;
    }

    public void setMarqueEntity(Marque MarqueEntity) {
        this.MarqueEntity = MarqueEntity;
    }

    public Marque getSelectedMarqueEntity() {
        return selectedMarqueEntity;
    }

    public void setSelectedMarqueEntity(Marque selectedMarqueEntity) {
        this.selectedMarqueEntity = selectedMarqueEntity;
    }
    /**
     * Creates a new instance of MarqueBean
     */
    public MarqueBean() {
    }
        public Marque findbyid(Integer idMarque){
        return MarqueEJB.findbyid(idMarque);
    }
    
}
