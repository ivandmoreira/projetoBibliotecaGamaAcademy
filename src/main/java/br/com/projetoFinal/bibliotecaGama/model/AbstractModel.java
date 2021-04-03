package br.com.projetoFinal.bibliotecaGama.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class AbstractModel implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private UUID id;

    @Column(nullable = false)
    private LocalDate createdAt;

    @Column(nullable = false)
    private LocalDate updatedAt;

    @Column(nullable = false)
    private boolean active;

    protected AbstractModel() {
        LocalDate now = LocalDate.now();
        this.id = UUID.randomUUID();
        this.createdAt = now;
        this.updatedAt = now;
        this.active = true;
    }

    protected UUID getId() {
        return this.id;
    }

    protected LocalDate getCreatedAt() {
        return this.createdAt;
    }

    protected LocalDate getUpdatedAt() {
        return this.updatedAt;
    }

    protected boolean getActive() {
        return this.active;
    }

    protected void setId(UUID id) {
        this.id = id;
    }

    protected void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    protected void setUpdatedAt(LocalDate updatedAt) {
        this.updatedAt = updatedAt;
    }

    protected void setActive(boolean active) {
        this.active = active;
    }

}
