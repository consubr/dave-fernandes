/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.davefernandes.ejb;

import br.com.davefernandes.model.Ator;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author dave
 */
@Stateless
public class AtorFacade extends AbstractFacade<Ator> {
    @PersistenceContext(unitName = "sakilabrPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AtorFacade() {
        super(Ator.class);
    }
    
}
