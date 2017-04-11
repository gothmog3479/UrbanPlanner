package ru.gothmog.urbanplanner.model.entities.registry.auth;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.envers.Audited;
import ru.gothmog.urbanplanner.core.audit.AuditAnnotation;
import ru.gothmog.urbanplanner.model.entities.CloneableEntity;
import ru.gothmog.urbanplanner.model.entities.IsogdEntity;
import ru.gothmog.urbanplanner.model.entities.registry.RegistryItem;

import javax.persistence.*;

/**
 * @author d.grushetskiy
 */
@Entity
@Table(name = "employee", indexes = {@javax.persistence.Index(name = "idx_employee_username", columnList = "username", unique = true)})
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Audited
@AuditAnnotation(title = "Сотрудник")
public class Employee implements RegistryItem, CloneableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // имя
    @Column(name = "first_name", nullable = false, length = 64)
    private String firstName = "";

    // отчество
    @Column(nullable = false, length = 128)
    private String patronymic = "";

    // фамилия
    @Column(name = "last_name", nullable = false, length = 128)
    private String lastName = "";

    @OneToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "username")
    private EmployeeUser user;

    // должность
    @Column(length = 512)
    private String job;

    @Column(name = "full_name", length = 512, nullable = false)
    private String fullName;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public EmployeeUser getUser() {
        if (user == null) {
            user = new EmployeeUser();
            // user.setEmployee(this);
        }
        return user;
    }

    public void setUser(EmployeeUser user) {
        this.user = user;
    }

    @Transient
    public String getRoles() {
        String roles = "";

        if (getUser() != null) {
            if (getUser().getAuthorities() != null) {
                for (EmployeeAuthority auth : getUser().getAuthorities()) {
                    if (!roles.equals(""))
                        roles += ", ";

                    roles += auth.getAuthority().toString();
                }
            }
        }
        return roles;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    @Override
    @Transient
    public String getTitle() {
        return getFullName();
    }

    @Override
    public String toString() {
        return getTitle();
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    /**
     * Метод генерирует полное имя сотрудника
     */
    public String createFullName() {
        return (getLastName() + " " + getFirstName() + " " + (getPatronymic() != null ? getPatronymic()
                : "")).trim();
    }

//------------------  cloneable ------------------------------------------------

    @Override
    public Employee clone() throws CloneNotSupportedException {
        Employee ret_val = new Employee();
        charge(ret_val);
        return ret_val;
    }

    @Override
    public Employee lazyClone() throws CloneNotSupportedException {
        Employee ret_val = new Employee();
        charge(ret_val);
        return ret_val;
    }

    @Override
    public void lazyCharge(IsogdEntity entity) throws CloneNotSupportedException {
    }

    @Override
    public void charge(IsogdEntity entity) throws CloneNotSupportedException {
        Employee ret_val = (Employee) entity;
        ret_val.setId(getId());
        ret_val.setFirstName(getFirstName());
        ret_val.setPatronymic(getPatronymic());
        ret_val.setLastName(getLastName());

        if (getUser() != null) {
            ret_val.setUser(getUser().clone());
        }

        ret_val.setJob(getJob());
        ret_val.setFullName(getFullName());
    }
}
