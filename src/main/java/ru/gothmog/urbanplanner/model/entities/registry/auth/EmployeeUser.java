package ru.gothmog.urbanplanner.model.entities.registry.auth;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.envers.Audited;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.DigestUtils;
import ru.gothmog.urbanplanner.core.audit.AuditAnnotation;
import ru.gothmog.urbanplanner.model.entities.CloneHelper;
import ru.gothmog.urbanplanner.model.entities.CloneableEntity;
import ru.gothmog.urbanplanner.model.entities.IsogdEntity;

import javax.persistence.*;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.HashSet;
import java.util.Set;

/**
 * @author d.grushetskiy
 */
@Entity
@Table(name = "users")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Audited
@AuditAnnotation(visibleInHistory = false)
public class EmployeeUser implements Serializable, IsogdEntity, CloneableEntity {

    private static final Logger logger = LoggerFactory.getLogger(EmployeeUser.class);

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(name = "username", unique = true, nullable = false)
    private String username;

    @Column(length = 50, nullable = false)
    private String password;

    @Column
    private Boolean enabled = true;

    @Column(name = "email", nullable = false, length = 150)
    private String email;

//    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true,
//            fetch = FetchType.LAZY)
//    private List<EmployeeAuthorityDao> authorities;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_authorities",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "authorities_id")})
    private Set<EmployeeAuthority> authorities = new HashSet<>();

    @Transient
    private String prePassword;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPrePassword() {
        return prePassword;
    }

    public void setPrePassword(String prePassword)
            throws UnsupportedEncodingException {
        this.prePassword = prePassword;

        if (prePassword != null) {
            this.password = DigestUtils.md5DigestAsHex(prePassword
                    .getBytes("UTF-8"));
            logger.debug("Password changed");
        }
    }

    public Set<EmployeeAuthority> getAuthorities() {
        if (authorities == null)
            authorities = new HashSet<>();
        return authorities;
    }

    public void setAuthorities(Set<EmployeeAuthority> authorities) {
        this.authorities = authorities;
    }

    public void removeAuthority(Authority authority) {
        if (getAuthorities() != null && !getAuthorities().isEmpty()
                && authority != null)
            getAuthorities().remove(authority);
    }
//------------------  cloneable ------------------------------------------------

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public EmployeeUser clone() throws CloneNotSupportedException {
        EmployeeUser ret_val = new EmployeeUser();
        charge(ret_val);
        return ret_val;
    }

    public EmployeeUser lazyClone() throws CloneNotSupportedException {
        EmployeeUser ret_val = new EmployeeUser();
        lazyCharge(ret_val);
        return ret_val;
    }

    @Override
    public void lazyCharge(IsogdEntity entity) throws CloneNotSupportedException {
        EmployeeUser ret_val = (EmployeeUser) entity;
        ret_val.setUsername(getUsername());
        ret_val.setPassword(getPassword());
        ret_val.setEnabled(getEnabled());
    }

    @Override
    public void charge(IsogdEntity entity) throws CloneNotSupportedException {
        EmployeeUser ret_val = (EmployeeUser) entity;
        lazyCharge(entity);
        ret_val.setAuthorities(CloneHelper.cloneAuthoritiesSet(getAuthorities()));
    }
}
