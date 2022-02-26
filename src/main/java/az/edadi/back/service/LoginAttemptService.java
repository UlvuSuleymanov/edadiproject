package az.edadi.back.service;

public interface LoginAttemptService {

    boolean isGoodAttemmpt();

    void addAttempt();

    void clear();

    void clearAll();
}
