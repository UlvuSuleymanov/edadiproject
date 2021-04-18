package com.camaat.first.service;


import com.camaat.first.model.response.SpecialitySummaryResModel;

import java.util.List;

public interface SpecialityService {

     List<SpecialitySummaryResModel> getSpecialitiesOfUni(String abbr,Integer group);

 }
