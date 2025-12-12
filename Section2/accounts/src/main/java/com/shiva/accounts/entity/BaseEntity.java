package com.shiva.accounts.entity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@MappedSuperclass // This annotation indicates to the spring data jpa that this class going to act as supper class for all my entities where i tried to extend my this base entity class. These all fields will be considerd in the class that calls it
@Getter @Setter @ToString
public class BaseEntity {

    @Column(updatable = false)//ensures that the column participates only in INSERT statements, not UPDATE statements
    private LocalDateTime createdAt;

    @Column(updatable = false)
    private String createdBy;

    @Column(insertable = false)//INSERT ke time ye field DB me nahi jayegi UPDATE ke time ye field allowed hai (update hoga). So very first time we do not need to update this.
    private LocalDateTime updatedAt;

    @Column(insertable = false)
    private String updatedBy;

}
