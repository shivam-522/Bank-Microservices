package com.shiva.accounts.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@MappedSuperclass // This annotation indicates to the spring data jpa that this class going to act as supper class for all my entities where i tried to extend my this base entity class. These all fields will be considerd in the class that calls it
@Getter @Setter @ToString
@EntityListeners(AuditingEntityListener.class) /**Acts as a watchdog for entity lifecycle events like insert, update, delete Helps handle cross-cutting concerns (audit, log, mask) outside business logic**/
public class BaseEntity {
    @CreatedDate /** By using this annotation our Spring data JPA Take Responsibility to update this field whenever data is updated in db **/
    @Column(updatable = false)//ensures that the column participates only in INSERT statements, not UPDATE statements
    private LocalDateTime createdAt;

    @CreatedBy
    @Column(updatable = false)
    private String createdBy;

    @LastModifiedDate    /** By using this annotation our Spring data JPA Take Responsibility to update this field whenever data is updated in db **/
    @Column(insertable = false)//INSERT ke time ye field DB me nahi jayegi UPDATE ke time ye field allowed hai (update hoga). So very first time we do not need to update this.
    private LocalDateTime updatedAt;

    @LastModifiedBy
    @Column(insertable = false)
    private String updatedBy;

}
