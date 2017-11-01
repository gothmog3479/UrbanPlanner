package ru.gothmog.urbanplanner.model.dao.registry.auth.impl;


import org.springframework.transaction.annotation.Transactional;
import ru.gothmog.urbanplanner.model.dao.registry.auth.PasswordResetTokenDao;
import ru.gothmog.urbanplanner.model.dao.registry.impl.BasicDaoImpl;
import ru.gothmog.urbanplanner.model.entities.registry.auth.EmployeeUser;
import ru.gothmog.urbanplanner.model.entities.registry.auth.PasswordResetToken;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.Date;
import java.util.stream.Stream;
@Transactional
public class PasswordResetTokenDaoImpl extends BasicDaoImpl<PasswordResetToken> implements PasswordResetTokenDao{

//    private String sql_token = "SELECT t FROM password_reset_token t WHERE t.token LIKE :token";
//    private String sql_emplUser = "";

//    @PersistenceContext
//    private EntityManager entityManager;

    public PasswordResetTokenDaoImpl(Class<PasswordResetToken> entityClass) {
        super(entityClass);
    }

    @Override
    public PasswordResetToken findByToken(String token) {
        Query query = sessionFactory.createEntityManager().createQuery("from PasswordResetToken p where p.token =:token");
        query.setParameter("token", token);
//        TypedQuery<PasswordResetToken> query = entityManager.createNamedQuery(sql_token, PasswordResetToken.class);
//        query.setParameter("token", token);
       return (PasswordResetToken) query.getSingleResult();
    }

    @Override
    public PasswordResetToken findByEmployeeUser(EmployeeUser employeeUser) {

//        Query query = sessionFactory
//                .createEntityManager()
//                .createQuery("from PasswordResetToken p join EmployeeUser u on p.user = u.id where u.username = EmployeeUser.username ");
//        if (employeeUser == null){
//
//        }
//        query.setParameter( );
//        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
//        CriteriaQuery<PasswordResetToken> criteriaQuery = criteriaBuilder.createQuery(PasswordResetToken.class);
//        Root<PasswordResetToken> passwordResetTokenRoot = criteriaQuery.from(PasswordResetToken.class);
//        passwordResetTokenRoot.;
     return (PasswordResetToken) getCurrentSession().merge(employeeUser);
    }

    @Override
    public Stream<PasswordResetToken> findAllByExpiryDateLessThan(Date now) {
        return null;
    }

    @Override
    public void deleteAllExpiredSince(Date now) {

    }
}
