package com.project.draw.global.util.jms;

import com.project.draw.global.exception.MailSendException;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.internet.MimeMessage;
import java.io.IOException;

@RequiredArgsConstructor
@Component
public class JmsUtil {

    private final JmsProperties jmsProperties;
    private final JavaMailSender mailSender;

    public void sendMail(MailType mailType, String email, String authenticationCode) {

        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper messageHelper = new MimeMessageHelper(message, false, "UTF-8");

            messageHelper.setTo(email);
            messageHelper.setFrom(jmsProperties.getUsername());
            messageHelper.setSubject("[Togather] 이메일 인증");

            String text = getFormattedString(mailType, authenticationCode.split(""));
            boolean isHTML = true;

            messageHelper.setText(text,isHTML);

            mailSender.send(message);
        } catch (Exception e){
            throw MailSendException.EXCEPTION;
        }

    }

    private String getFormattedString(MailType mailType, String[] codes) {
        return String.format(getMailTemplate(mailType),
                codes[0],
                codes[1],
                codes[2],
                codes[3],
                codes[4],
                codes[5]);
    }

    private String getMailTemplate(MailType mailType){
        try {
            byte[] bytes = new ClassPathResource("static/"+mailType.getFileName()).getInputStream().readAllBytes();
            return new String(bytes);
        } catch (IOException e) {
            throw MailSendException.EXCEPTION;
        }
    }

}