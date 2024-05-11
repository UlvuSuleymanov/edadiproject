package az.edadi.back.bootstrap.dev;

import az.edadi.back.constants.type.EntityType;
import az.edadi.back.entity.app.Topic;
import az.edadi.back.entity.auth.User;
import az.edadi.back.entity.message.Conversation;
import az.edadi.back.entity.message.Thread;
import az.edadi.back.entity.post.Post;
import az.edadi.back.entity.roommate.Region;
import az.edadi.back.entity.university.University;
import az.edadi.back.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Configuration
@Profile("test")
public class InitDb implements CommandLineRunner {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final TopicRepository topicRepository;
    private final UniversityRepository universityRepository;
    private final RegionRepository regionRepository;
    private final Environment environment;
    private final PostRepository postRepository;

    private final ThreadRepository threadRepository;

    private final ConversationRepository conversationRepository;


    public InitDb(UserRepository userRepository,
                  PasswordEncoder passwordEncoder,
                  TopicRepository topicRepository,
                  UniversityRepository universityRepository,
                  RegionRepository regionRepository,
                  Environment environment, PostRepository postRepository, ThreadRepository threadRepository, ConversationRepository conversationRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.topicRepository = topicRepository;
        this.universityRepository = universityRepository;
        this.regionRepository = regionRepository;
        this.environment = environment;
        this.postRepository = postRepository;
        this.threadRepository = threadRepository;
        this.conversationRepository = conversationRepository;
    }


    @Override
    public void run(String... args) throws Exception {

        addDummyUniversities();

        addDummyUsers();

        addQuestions();

        addRegions();

        addSearchItems();

        addPosts();

        addThreads();
    }

    private void addPosts() {
        List<Post> postList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Post post = new Post();
            post.setText("dummy adsa dsa ads asd sasad as");
            post.setUser(new User(1L));
            post.setParent(EntityType.TOPIC);
            post.setTopic(new Topic(1L));
            postList.add(post);
         }
            postRepository.saveAll(postList);
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
        user.setPicture("assets/images/user/default.png");
        user.setPassword(passwordEncoder.encode("admin"));
        userRepository.saveAndFlush(user);
        for (int i = 0; i < 3; i++) {
            User user1 = new User();
            user1.setUsername(UUID.randomUUID().toString().substring(5, 20));
            user1.setEmail(UUID.randomUUID().toString().substring(5, 20) + "@gmail.com");
            user1.setPassword(passwordEncoder.encode("password"));
            user1.setName(UUID.randomUUID().toString().substring(1, 11));
            user1.setPicture("assets/images/user/default.png");

            userList.add(user1);
        }
        userRepository.saveAllAndFlush(userList);
    }

    void addQuestions() {
        List<Topic> topics = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            Topic topic = new Topic();
            topic.setDate(new Date());
            topic.setUser(new User(1L));
            topic.setTitle(UUID.randomUUID().toString());
            topics.add(topic);
        }
        topicRepository.saveAll(topics);
    }


    void addRegions() {
        List<Region> regions = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            Region region = new Region();
            region.setName(UUID.randomUUID().toString().substring(1, 10));
            regions.add(region);
        }
        regionRepository.saveAll(regions);
    }


    void addSearchItems() {
        String[] activeProfiles = environment.getActiveProfiles();

//        for (String profile : activeProfiles)
//            if (profile.equalsIgnoreCase("elasticsearch")) {
//                searchRepository.deleteAll();
//                List<SearchItem> items = userRepository
//                        .findAll()
//                        .stream()
//                        .map(SearchItem::new).toList();
//                searchRepository.saveAll(items);
//                break;
//            }


    }

    void addThreads(){
        Conversation conversation = new Conversation();
        conversation= conversationRepository.saveAndFlush(conversation);

        Thread thread = new Thread();
        thread.setConversation(conversation);
        thread.setUser(new User(1L));
        threadRepository.saveAndFlush(thread);

        Thread newThread = new Thread();
        newThread.setConversation(conversation);
        newThread.setUser(new User(2L));
        threadRepository.saveAndFlush(newThread);



     }
}
