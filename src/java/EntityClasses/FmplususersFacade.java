/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EntityClasses;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author John Lynch
 */
@Stateless
public class FmplususersFacade extends AbstractFacade<Fmplususers> {
    @PersistenceContext(unitName = "FacemashplusPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public FmplususersFacade() {
        super(Fmplususers.class);
    }
    
}
