package ru.gothmog.urbanplanner.model.entities.registry.auth;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.envers.Audited;
import ru.gothmog.urbanplanner.model.entities.CloneableEntity;
import ru.gothmog.urbanplanner.model.entities.IsogdEntity;

import javax.persistence.*;

/**
 * @author d.grushetskiy
 */
@Entity
@Table(name = "authorities")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Audited
public class EmployeeAuthority implements IsogdEntity, CloneableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "authorities_id")
    private Long id;

//    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE,
//            CascadeType.PERSIST, CascadeType.REFRESH})
//    @JoinColumn(name = "username")
//    private EmployeeUser user;

    @Column
    @Enumerated(EnumType.STRING)
    private Authority authority;

    public EmployeeAuthority() {

    }

    public EmployeeAuthority(Authority authority) {
        this.authority = authority;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

//    public EmployeeUser getUser() {
//        return user;
//    }

//    public void setUser(EmployeeUser user) {
//        this.user = user;
//    }

    public Authority getAuthority() {
        return authority;
    }

    public void setAuthority(Authority authority) {
        this.authority = authority;
    }

    // public Employee getEmployee() {
    // return employee;
    // }
    //
    // public void setEmployee(Employee employee) {
    // this.employee = employee;
    // }

//------------------  cloneable ------------------------------------------------

    @Override
    public EmployeeAuthority clone() throws CloneNotSupportedException {
        EmployeeAuthority ret_val = new EmployeeAuthority();
        charge(ret_val);
        return ret_val;
    }

    public EmployeeAuthority lazyClone() throws CloneNotSupportedException {
        EmployeeAuthority ret_val = new EmployeeAuthority();
        lazyCharge(ret_val);
        return ret_val;
    }

    @Override
    public void lazyCharge(IsogdEntity entity) throws CloneNotSupportedException {
        EmployeeAuthority ret_val = (EmployeeAuthority) entity;
        ret_val.setId(getId());
        if (getAuthority() != null) {
            ret_val.setAuthority(getAuthority());
        }
    }

    @Override
    public void charge(IsogdEntity entity) throws CloneNotSupportedException {
        EmployeeAuthority ret_val = (EmployeeAuthority) entity;
        lazyCharge(entity);
//        if (getUser() != null) {
//            ret_val.setUser(getUser().lazyClone());
//        }

    }
}
