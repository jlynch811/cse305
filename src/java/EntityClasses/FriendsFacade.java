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
public class FriendsFacade extends AbstractFacade<Friends> {
    @PersistenceContext(unitName = "FacemashplusPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public FriendsFacade() {
        super(Friends.class);
    }
    
}
