package az.edadi.back.service;


import freemarker.template.TemplateException;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.Map;

public interface MailService {

    void sendResetPasswordMail(String to, Map<String,String> body) throws MessagingException, IOException, TemplateException;

}
