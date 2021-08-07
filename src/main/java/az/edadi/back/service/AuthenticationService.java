package az.edadi.back.service;

  import az.edadi.back.model.response.SignInResponseModel;
  import az.edadi.back.model.request.SignInRequestModel;
 import az.edadi.back.model.request.SignUpRequestModel;
  import freemarker.template.TemplateException;

  import javax.mail.MessagingException;
  import java.io.IOException;

public interface AuthenticationService {

  SignInResponseModel login(SignInRequestModel signInRequestModel);

   void register(SignUpRequestModel signUpRequestModel);

   String sendTokenByEmail(String usernamOrEmail) throws MessagingException, IOException, TemplateException;

 }
