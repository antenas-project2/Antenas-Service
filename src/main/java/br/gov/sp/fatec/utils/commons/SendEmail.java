package br.gov.sp.fatec.utils.commons;

import br.gov.sp.fatec.utils.exception.Exception.SendEmailFailedException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.Date;

@Service
@EnableAsync
public class SendEmail {

    @Autowired
    private JavaMailSender javaMailSender;

    private static final Logger logger = LogManager.getLogger(SendEmail.class);

    @Async
    public void sendEmail(String email, String url, String oldEmail) {
        try {
            JSONObject base64 = new JSONObject();
            base64.put("dateTime", new Date());
            base64.put("email", email);
            base64.put("oldEmail", oldEmail);

            String b64 = Base64.getEncoder().encodeToString(base64.toString().getBytes());
            String link = url + "/dev/user/activate/" + b64;
            SimpleMailMessage mailMessage = new SimpleMailMessage();

            mailMessage.setTo(email);

            if (oldEmail != null) {
                mailMessage.setSubject("Antenas - Alteração de email");
                mailMessage.setText("Para alterar seu email é necessário clicar link para confirma-lo: " + link);
            } else {
                mailMessage.setSubject("Antenas - Confirmação de conta");
                mailMessage.setText("Para confirmar sua conta, clique no link: " + link);
            }

            mailMessage.setFrom("fatec.antenas@gmail.com");

            javaMailSender.send(mailMessage);
            logger.info(String.format("Email sent to: %s  Change email: %s", email, oldEmail != null));
        } catch (Exception ex) {
            throw new SendEmailFailedException();
        }
    }
}
