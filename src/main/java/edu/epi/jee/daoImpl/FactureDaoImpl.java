
package edu.epi.jee.daoImpl;

import edu.epi.jee.dao.FactureDao;
import edu.epi.jee.dao.GenericDaoImpl;
import edu.epi.jee.entities.FactureEntity;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.Query;

/**
 *
 * @author HLOL
 */
@Stateless
public class FactureDaoImpl  extends GenericDaoImpl<FactureEntity> implements FactureDao{

    @Override
    public List<FactureEntity> findAllFactures() {
                Query query = em.createQuery("select f FROM FactureEntity f"); 
		
		return query.getResultList();    }
 
}
