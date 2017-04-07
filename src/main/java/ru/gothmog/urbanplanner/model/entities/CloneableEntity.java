package ru.gothmog.urbanplanner.model.entities;

/**
 * @author d.grushetskiy
 */
public interface CloneableEntity {

    IsogdEntity clone() throws CloneNotSupportedException;

    IsogdEntity lazyClone() throws CloneNotSupportedException;

    void charge(IsogdEntity entity) throws CloneNotSupportedException;

    void lazyCharge(IsogdEntity ret_val) throws CloneNotSupportedException;
}
