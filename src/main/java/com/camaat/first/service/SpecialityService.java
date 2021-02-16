package com.camaat.first.service.impl;

import com.camaat.first.entity.Speciality;

import java.util.List;

public interface SpecialityService {
    List<Speciality> getGeneralSpeciality();
    List<Speciality> getSpecialitye(Long uniID);
}
