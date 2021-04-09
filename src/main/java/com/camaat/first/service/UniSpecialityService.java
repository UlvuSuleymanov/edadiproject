package com.camaat.first.service;


  import com.camaat.first.model.response.SpecialitySummaryResModel;

 import java.util.List;

public interface UniSpecialityService {

     List<SpecialitySummaryResModel> getSpecialitiesOfUni(String abbr);

 }
