/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagedBean;

import ejb.GenreFacade;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.ApplicationScoped;
import jpa.Genre;
/**
 *
 * @author nos
 */
@Named(value = "genreBean")
@ApplicationScoped
public class GenreBean {
@EJB
private GenreFacade GenreEJB;
private Genre GenreEntity;
private Genre selectedGenreEntity;
@PostConstruct
    public void init() {
        GenreEntity = new Genre();
        selectedGenreEntity = new Genre();
    }

    public Genre getGenreEntity() {
        return GenreEntity;
    }

    public void setGenreEntity(Genre GenreEntity) {
        this.GenreEntity = GenreEntity;
    }

    public Genre getSelectedGenreEntity() {
        return selectedGenreEntity;
    }

    public void setSelectedGenreEntity(Genre selectedGenreEntity) {
        this.selectedGenreEntity = selectedGenreEntity;
    }
    
    /**
     * Creates a new instance of Genre
     */
    public GenreBean() {
    }
    public Genre findbyid(Integer idGenre){
        return GenreEJB.findbyid(idGenre);
    }
    
}
