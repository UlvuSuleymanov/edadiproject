package az.edadi.back.bootstrap;

import az.edadi.back.entity.app.Question;
import az.edadi.back.entity.auth.User;
import az.edadi.back.entity.roommate.Region;
import az.edadi.back.entity.search.SearchItem;
import az.edadi.back.entity.university.University;
import az.edadi.back.repository.QuestionRepository;
import az.edadi.back.repository.RegionRepository;
import az.edadi.back.repository.UniversityRepository;
import az.edadi.back.repository.UserRepository;
import az.edadi.back.repository.search.SearchRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Profile("test")
@Configuration
public class TestInit implements CommandLineRunner {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    private final QuestionRepository questionRepository;
    private final UniversityRepository universityRepository;

    private final RegionRepository regionRepository;

    private final SearchRepository searchRepository;


    public TestInit(UniversityRepository universityRepository,
                    UserRepository userRepository,
                    PasswordEncoder passwordEncoder, QuestionRepository questionRepository, RegionRepository regionRepository, SearchRepository searchRepository) {
        this.universityRepository = universityRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.questionRepository = questionRepository;
        this.regionRepository = regionRepository;
        this.searchRepository = searchRepository;
    }


    @Override
    public void run(String... args) throws Exception {
        //add dummy universities
        addDummyUniversities();
        //add dummy users
        addDummyUsers();

        addQuestions();

        addRegions();

        addSearchItems();
    }

    void addDummyUniversities() {
        List<University> universityList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            University university = new University();
            university.setAbbr(UUID.randomUUID().toString().substring(5, 10));
            university.setNameAz(UUID.randomUUID().toString().substring(5, 25));
            university.setNameEn(UUID.randomUUID().toString().substring(5, 30));
            university.setInfo(UUID.randomUUID().toString());
            universityList.add(university);

        }
        universityRepository.saveAllAndFlush(universityList);
    }

    void addDummyUsers() {
        List<User> userList = new ArrayList<>();
        User user = new User();
        user.setName("admin");
        user.setUsername("admin");
        user.setEmail("admin@gmail.com");
        user.setPicture("https://cdn.vectorstock.com/i/1000x1000/30/97/flat-business-man-user-profile-avatar-icon-vector-4333097.webp");
        user.setPassword(passwordEncoder.encode("admin"));
        userRepository.saveAndFlush(user);
        for (int i = 0; i <3; i++) {
            User user1 = new User();
            user1.setUsername(UUID.randomUUID().toString().substring(5, 20));
            user1.setEmail(UUID.randomUUID().toString().substring(5, 20) + "@gmail.com");
            user1.setPassword(passwordEncoder.encode(UUID.randomUUID().toString().substring(5, 20)));
            user1.setName(UUID.randomUUID().toString().substring(1, 11));
            userList.add(user1);
        }
        userRepository.saveAllAndFlush(userList);
    }

    void addQuestions(){
        List<Question> questions = new ArrayList<>();

        for(int i=0;i<5; i++){
            Question question = new Question();
            question.setDate(new Date());
            question.setUser(new User(1L));
            question.setTitle(UUID.randomUUID().toString());
            questions.add(question);
        }
        questionRepository.saveAll(questions);
    }


    void addRegions(){
        List<Region> regions = new ArrayList<>();

        for(int i=0;i<10; i++){
             Region region = new Region();
             region.setName(UUID.randomUUID().toString().substring(1,10));
             regions.add(region);
        }
        regionRepository.saveAll(regions);
    }


    void addSearchItems(){
        List<SearchItem> items = userRepository.findAll().stream().map(user -> new SearchItem(user)).toList();
        searchRepository.saveAll(items);

    }


}
