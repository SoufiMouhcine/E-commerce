/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagedBean;

import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author nos
 */
@Named(value = "searchBean")
@RequestScoped
public class SearchBean {
private String key;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
    /**
     * Creates a new instance of SearchBean
     */
    public SearchBean() {
    }
    
}
