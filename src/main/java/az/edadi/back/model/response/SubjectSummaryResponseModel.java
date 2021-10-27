package az.edadi.back.model.response;

import az.edadi.back.entity.university.Subject;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class SubjectSummaryResponseModel {

    private Long id;
    private String name;

    public SubjectSummaryResponseModel(Subject subject){
        id=subject.getId();
        name=subject.getName();
    }

}
