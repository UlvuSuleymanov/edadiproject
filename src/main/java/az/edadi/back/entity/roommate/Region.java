package az.edadi.back.entity.roommate;

import lombok.Data;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Region {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "region")
    private List<RoommateAd> roommateAds = new ArrayList<>();
}
