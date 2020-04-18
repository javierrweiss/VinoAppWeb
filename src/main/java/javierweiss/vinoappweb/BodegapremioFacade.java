/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javierweiss.vinoappweb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import vinoapp.entidades.Bodegapremio;

/**
 *
 * @author josejavier84
 */
@Stateless
public class BodegapremioFacade extends AbstractFacade<Bodegapremio> {

    @PersistenceContext(unitName = "javierweiss_VinoAppWeb_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public BodegapremioFacade() {
        super(Bodegapremio.class);
    }
    
}
