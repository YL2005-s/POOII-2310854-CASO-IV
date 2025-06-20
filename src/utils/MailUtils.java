package utils;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

public class MailUtils {

    public static void sendEmail(String senderMail, String senderPass, String recieverMail,
                                 String issue, String message) throws EmailException {
        try {
            SimpleEmail email = new SimpleEmail();
            email.setHostName("smtp.gmail.com");
            email.setSmtpPort(587);
            email.setAuthenticator(new DefaultAuthenticator(senderMail, senderPass));
            email.setStartTLSEnabled(true);

            email.setFrom(senderMail);
            email.setSubject(issue);
            email.setMsg(message);
            email.addTo(recieverMail);

            email.send();
            System.out.println("Correo enviado exitosamente a " + recieverMail);
        } catch (EmailException e) {
            System.err.println("Error al enviar el correo:");
            e.printStackTrace();
            throw e;
        }
    }
}