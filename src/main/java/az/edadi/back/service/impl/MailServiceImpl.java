package az.edadi.back.service.impl;

 import az.edadi.back.service.MailService;
 import freemarker.template.Configuration;
 import freemarker.template.Template;
 import freemarker.template.TemplateException;
 import lombok.RequiredArgsConstructor;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.mail.javamail.JavaMailSender;
 import org.springframework.mail.javamail.MimeMessageHelper;
 import org.springframework.scheduling.annotation.Async;
 import org.springframework.stereotype.Service;
 import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

 import javax.mail.MessagingException;
 import javax.mail.internet.InternetAddress;
 import javax.mail.internet.MimeMessage;
 import java.io.IOException;
 import java.nio.charset.StandardCharsets;
 import java.util.Map;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MailServiceImpl implements MailService {
    private final JavaMailSender mailSender;
    private final Configuration configuration;

    @Override
    @Async(value = "sendMailExecutor")
    public void sendResetPasswordMail(String to, Map<String,String> mailMessage) throws MessagingException, IOException, TemplateException {

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                StandardCharsets.UTF_8.name());

        Template t = configuration.getTemplate("mailTemp.html");

        String html = FreeMarkerTemplateUtils.processTemplateIntoString(t, mailMessage);

        mimeMessageHelper.setTo(to);
        mimeMessageHelper.setText(html, true);
        mimeMessageHelper.setSubject("Şifrə bərpası");
        mimeMessageHelper.setFrom(new InternetAddress("security@edadi.az","Edadi"));
        mailSender.send(message);
    }
}
