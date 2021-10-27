package az.edadi.back.service;

import az.edadi.back.entity.university.Subject;
import az.edadi.back.model.request.SubjectRequestModel;
import az.edadi.back.model.response.SubjectSummaryResponseModel;

import java.util.List;

public interface SubjectService {
    List<SubjectSummaryResponseModel> getSubjects(Long specialityId);
    Subject addSubject(SubjectRequestModel subjectRequestModel);
}
