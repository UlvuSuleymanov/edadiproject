package az.edadi.back.entity.textbook;

import az.edadi.back.entity.university.TextBookFile;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class TextBookType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "type")
    private List<TextBookFile> textBookFiles = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "type")
    private List<TextbookAd> textbookAds = new ArrayList<>();

}
