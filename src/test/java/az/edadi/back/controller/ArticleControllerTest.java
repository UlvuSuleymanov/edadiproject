//package az.edadi.back.controller;
//
//import static org.mockito.Mockito.anyInt;
//import static org.mockito.Mockito.when;
//
//import az.edadi.back.model.request.ArticleRequestModel;
//import az.edadi.back.model.response.ArticleResponseModel;
//import az.edadi.back.service.ArticleService;
//
//import java.util.ArrayList;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.databind.ObjectWriter;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.Mockito;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.context.ApplicationEventPublisher;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//
//@ContextConfiguration(classes = {ArticleController.class})
//@RunWith(SpringJUnit4ClassRunner.class)
//public class ArticleControllerTest {
//    @MockBean
//    private ApplicationEventPublisher applicationEventPublisher;
//
//    @Autowired
//    private ArticleController articleController;
//
//    @MockBean
//    private ArticleService articleService;
//
//    ObjectMapper mapper = new ObjectMapper();
//
//    @Test
//    public void testAddArticle() throws Exception {
//
//        when(articleService.getArticleList(anyInt(), anyInt(), Mockito.<String>any())).thenReturn(new ArrayList<>());
//        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/article");
//
//
//        MockMvcBuilders.standaloneSetup(articleController)
//                .build()
//                .perform(requestBuilder)
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
//                .andExpect(MockMvcResultMatchers.content().string("[]"));
//    }
//
//
//    @Test
//    public void testGetArticle() throws Exception {
//        when(articleService.getArticle(Mockito.<String>any())).thenReturn(new ArticleResponseModel());
//        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/article/{slug}", "Slug");
//        MockMvcBuilders.standaloneSetup(articleController)
//                .build()
//                .perform(requestBuilder)
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
//                .andExpect(MockMvcResultMatchers.content()
//                        .string(mapper.writeValueAsString(new ArticleResponseModel())));
//    }
//
//
//    @Test
//    public void testGetArticles() throws Exception {
//        when(articleService.getArticleList(anyInt(), anyInt(), Mockito.<String>any())).thenReturn(new ArrayList<>());
//
//        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/api/article");
//        MockHttpServletRequestBuilder paramResult = getResult.param("page", String.valueOf(1));
//        MockHttpServletRequestBuilder requestBuilder = paramResult.param("size", String.valueOf(1)).param("sort", "foo");
//        MockMvcBuilders.standaloneSetup(articleController)
//                .build()
//                .perform(requestBuilder)
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
//                .andExpect(MockMvcResultMatchers.content().string("[]"));
//    }
//}
//
