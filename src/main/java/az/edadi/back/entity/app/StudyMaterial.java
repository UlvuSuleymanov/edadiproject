package az.edadi.back.entity.app;

import az.edadi.back.entity.BaseEntity;
import az.edadi.back.entity.auth.User;
import az.edadi.back.entity.university.Subject;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
public class StudyMaterial extends BaseEntity {

    private String name;

    private String url;

    @ManyToOne
    private User user;

    @ManyToOne
    private Subject subject;


}
