package az.edadi.back.service;

import az.edadi.back.model.response.ReelsRes;

import java.util.List;

public interface ReelsService {
    List<ReelsRes> getReels(int page);
}
