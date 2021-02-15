package com.camaat.first.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor

public class UniResponseModel {
    private Long id;
    private String abbrName;
    private String info;
}
