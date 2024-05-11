package az.edadi.back.entity;

import az.edadi.back.constants.State;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.Objects;

@Data
@MappedSuperclass
public class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date_created", nullable = false, updatable = false)
    private Date dateCreated;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date_modified")
    private Date dateModified;

    @Enumerated(EnumType.STRING)
    @Column(name = "state")
    private State state;

    @PrePersist
    protected void onCreate() {
        this.dateCreated = new Date();
        this.dateModified = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
        this.dateModified = new Date();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BaseEntity that = (BaseEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
