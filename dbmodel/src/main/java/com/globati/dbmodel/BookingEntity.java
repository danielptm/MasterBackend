package com.globati.dbmodel;

import com.globati.enums.GlobatiPaymentStatus;
import javax.persistence.*;


@MappedSuperclass
public abstract class BookingEntity extends BaseEntity {

    @Column
    @Enumerated(EnumType.STRING)
    GlobatiPaymentStatus globatiPaymentStatus;


    public GlobatiPaymentStatus getGlobatiPaymentStatus() {
        return globatiPaymentStatus;
    }

    public void setGlobatiPaymentStatus(GlobatiPaymentStatus globatiPaymentStatus) {
        this.globatiPaymentStatus = globatiPaymentStatus;
    }
}
