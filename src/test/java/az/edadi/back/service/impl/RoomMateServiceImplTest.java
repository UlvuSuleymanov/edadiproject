//package az.edadi.back.service.impl;
//
//import static org.junit.Assert.assertThrows;
//import static org.junit.Assert.assertTrue;
//import static org.mockito.Mockito.*;
//
//import az.edadi.back.entity.User;
//import az.edadi.back.entity.roommate.Region;
//import az.edadi.back.entity.roommate.RoommateAd;
//import az.edadi.back.entity.university.Speciality;
//import az.edadi.back.entity.university.University;
//import az.edadi.back.exception.model.UserAuthorizationException;
//import az.edadi.back.model.request.RoommateRequestModel;
//import az.edadi.back.model.response.RoommateResponseModel;
//import az.edadi.back.repository.RegionRepository;
//import az.edadi.back.repository.RoomMateRepository;
//import az.edadi.back.repository.UserEventsRepository;
//import az.edadi.back.repository.UserRepository;
//import az.edadi.back.utility.AuthUtil;
//
//import java.time.LocalDate;
//import java.time.ZoneOffset;
//
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.HashSet;
//import java.util.Optional;
//
//import org.junit.Ignore;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.MockedConstruction;
//import org.mockito.MockedStatic;
//import org.mockito.Mockito;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.data.domain.PageImpl;
//import org.springframework.data.domain.Pageable;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//
//@ContextConfiguration(classes = {RoomMateServiceImpl.class})
//@RunWith(SpringJUnit4ClassRunner.class)
//public class RoomMateServiceImplTest {
//
//
//    @MockBean
//    private RegionRepository regionRepository;
//
//    @MockBean
//    private RoomMateRepository roomMateRepository;
//
//    @Autowired
//    private RoomMateServiceImpl roomMateServiceImpl;
//
//    @MockBean
//    private UserEventsRepository userEventsRepository;
//
//    @MockBean
//    private UserRepository userRepository;
//
//    /**
//     * Method under test: {@link RoomMateServiceImpl#addRoommate(RoommateRequestModel)}
//     */
//    @Test
//    public void testAddRoommate() {
//        MockedStatic<AuthUtil> mockAuth = Mockito.mockStatic(AuthUtil.class);
//        MockedConstruction<RoommateResponseModel> responseMock = mockConstruction(RoommateResponseModel.class);
//        mockAuth.when(() ->AuthUtil.getCurrentUserId()).thenReturn(1L);
//
//        RoommateRequestModel roommateRequestModel = new RoommateRequestModel();
//        roommateRequestModel.setAmount(10);
//        roommateRequestModel.setContact("Contact");
//        roommateRequestModel.setInfo("Info");
//        roommateRequestModel.setRegion(1L);
//        roomMateServiceImpl.addRoommate(roommateRequestModel);
//    }
//
//    @Test
//    public void testAddRoommate2() {
//        when(AuthUtil.getCurrentUserId()).thenReturn(1L);
//
//        RoommateRequestModel roommateRequestModel = mock(RoommateRequestModel.class);
//        when(roommateRequestModel.getAmount()).thenReturn(10);
//        when(roommateRequestModel.getContact()).thenReturn("Contact");
//        when(roommateRequestModel.getInfo()).thenReturn("Info");
//        doNothing().when(roommateRequestModel).setAmount(Mockito.<Integer>any());
//        doNothing().when(roommateRequestModel).setContact(Mockito.<String>any());
//        doNothing().when(roommateRequestModel).setInfo(Mockito.<String>any());
//        doNothing().when(roommateRequestModel).setRegion(Mockito.<Long>any());
//        roommateRequestModel.setAmount(10);
//        roommateRequestModel.setContact("Contact");
//        roommateRequestModel.setInfo("Info");
//        roommateRequestModel.setRegion(1L);
//        roomMateServiceImpl.addRoommate(roommateRequestModel);
//    }
//
//    /**
//     * Method under test: {@link RoomMateServiceImpl#getRoommates(Long, int)}
//     */
//    @Test
//    public void testGetRoommates() {
//        when(roomMateRepository.getRoommatesByRegion(Mockito.<Long>any(), Mockito.<Pageable>any()))
//                .thenReturn(new ArrayList<>());
//        assertTrue(roomMateServiceImpl.getRoommates(1L, 1).isEmpty());
//        verify(roomMateRepository).getRoommatesByRegion(Mockito.<Long>any(), Mockito.<Pageable>any());
//    }
//
//    /**
//     * Method under test: {@link RoomMateServiceImpl#getRoommates(Long, int)}
//     */
//    @Test
//    public void testGetRoommates2() {
//
//
//        Region region = new Region();
//        region.setId(1L);
//        region.setName("date");
//        region.setRoommateAds(new ArrayList<>());
//
//        University university = new University();
//        university.setAbbr("date");
//        university.setAbbrAz("date");
//        university.setId(1L);
//        university.setInfo("date");
//        university.setNameAz("date");
//        university.setNameEn("date");
//        university.setPostList(new ArrayList<>());
//        university.setSpecialities(new ArrayList<>());
//
//        Speciality speciality = new Speciality();
//        speciality.setId(1L);
//        speciality.setMinEntryPoints(new ArrayList<>());
//        speciality.setName("date");
//        speciality.setPostList(new ArrayList<>());
//        speciality.setSpecialityCode(5L);
//        speciality.setSpecialityGroup(5L);
//        speciality.setSubjects(new ArrayList<>());
//        speciality.setTextbookAds(new ArrayList<>());
//        speciality.setType("date");
//        speciality.setUniversity(university);
//        speciality.setUsers(new ArrayList<>());
//
//        User user = new User();
//        user.setArticles(new ArrayList<>());
//        user.setAuthorities(new HashSet<>());
//        user.setComments(new ArrayList<>());
//        user.setEmail("jane.doe@example.org");
//        user.setId(1L);
//        user.setImageName("date");
//        user.setImages(new ArrayList<>());
//        user.setLogins(new ArrayList<>());
//        user.setMessages(new ArrayList<>());
//        user.setName("date");
//        user.setPassword("iloveyou");
//        user.setPosts(new ArrayList<>());
//        user.setProfileBirthDay(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
//        user.setQuestions(new ArrayList<>());
//        user.setRoommateAds(new ArrayList<>());
//        user.setSpeciality(speciality);
//        user.setSubjects(new ArrayList<>());
//        user.setTextBookFiles(new ArrayList<>());
//        user.setTextbookAds(new ArrayList<>());
//        user.setUserThreads(new ArrayList<>());
//        user.setUsername("janedoe");
//        user.setVotes(new ArrayList<>());
//
//        RoommateAd roommateAd = new RoommateAd();
//        roommateAd.setAmount(10);
//        roommateAd.setContact("date");
//        roommateAd.setDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
//        roommateAd.setId(1L);
//        roommateAd.setInfo("date");
//        roommateAd.setRegion(region);
//        roommateAd.setUser(user);
//
//        ArrayList<RoommateAd> roommateAdList = new ArrayList<>();
//        roommateAdList.add(roommateAd);
//        when(roomMateRepository.getRoommatesByRegion(Mockito.<Long>any(), Mockito.<Pageable>any()))
//                .thenReturn(roommateAdList);
//        roomMateServiceImpl.getRoommates(1L, 1);
//    }
//
//
//    @Test
//    public void testGetAllRoommateAds() {
//        when(roomMateRepository.findAll(Mockito.<Pageable>any())).thenReturn(new PageImpl<>(new ArrayList<>()));
//        assertTrue(roomMateServiceImpl.getAllRoommateAds(1).isEmpty());
//        verify(roomMateRepository).findAll(Mockito.<Pageable>any());
//    }
//
//
//    @Test
//    public void testGetAllRoommateAds2() {
//
//
//        when(roomMateRepository.findAll(Mockito.<Pageable>any())).thenReturn(null);
//        roomMateServiceImpl.getAllRoommateAds(1);
//    }
//
//
//    @Test
//    public void testGetAllRoommateAds3() {
//
//
//        when(roomMateRepository.findAll(Mockito.<Pageable>any())).thenReturn(new PageImpl<>(new ArrayList<>()));
//        roomMateServiceImpl.getAllRoommateAds(-1);
//    }
//
//
//    @Test
//    public void testGetAllRoommateAds4() {
//        when(roomMateRepository.findAll(Mockito.<Pageable>any())).thenThrow(new UserAuthorizationException());
//        assertThrows(UserAuthorizationException.class, () -> roomMateServiceImpl.getAllRoommateAds(1));
//        verify(roomMateRepository).findAll(Mockito.<Pageable>any());
//    }
//
//    @Test
//    public void testDeleteRoommateAd() {
//        when(AuthUtil.getCurrentUserId()).thenReturn(1L);
//        when(roomMateRepository.findById(anyLong())).thenReturn(Optional.of(new RoommateAd()));
//
//        roomMateServiceImpl.deleteRoommateAd(1L);
//    }
//}
//
