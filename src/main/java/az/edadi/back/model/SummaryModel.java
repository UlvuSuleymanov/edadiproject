package az.edadi.back.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SummaryModel {
    private Long id;
    private String name;

    public SummaryModel(Long id, String name){
        this.id=id;
        this.name=name;
    }
}
