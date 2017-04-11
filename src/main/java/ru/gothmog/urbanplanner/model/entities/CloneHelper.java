package ru.gothmog.urbanplanner.model.entities;

import org.apache.log4j.Logger;
import org.hibernate.Hibernate;
import org.hibernate.proxy.HibernateProxy;
import ru.gothmog.urbanplanner.model.entities.registry.RegistryItem;
import ru.gothmog.urbanplanner.model.entities.registry.auth.EmployeeAuthority;

import java.util.LinkedList;
import java.util.List;

/**
 * @author d.grushetskiy
 */
public class CloneHelper {

    Logger logger = Logger.getLogger("common");

    public static RegistryItem initializeAndUnproxy(RegistryItem entity) {
        if (entity == null) {
            throw new
                    NullPointerException("Entity passed for initialization is null");
        }

//      Hibernate.initialize(entity);
        if (entity instanceof HibernateProxy) {
            Hibernate.initialize(entity);
            entity = (RegistryItem) ((HibernateProxy) entity).getHibernateLazyInitializer()
                    .getImplementation();
        }
        return entity;
    }

    public static List<EmployeeAuthority> cloneAuthoritiesList(List<EmployeeAuthority> list) {
        List<EmployeeAuthority> cloneAuthorities = new LinkedList<EmployeeAuthority>();
        for (EmployeeAuthority theAuthority : list) {
            try {
                cloneAuthorities.add(theAuthority.clone());
            } catch (CloneNotSupportedException e) {
                LoggerUtil.error(e.getMessage());
            }
        }
        return cloneAuthorities;
    }
}
