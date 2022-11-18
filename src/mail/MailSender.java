package mail;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class MailSender {
    public static void sendMessage(String error) throws Exception{

        Properties properties = new Properties();

        properties.put("mail.smtp.auth","true");

        properties.put("mail.smtp.starttls.enable","true");

        properties.put("mail.smtp.socketFactory.port", "465"); //SSL Port

        properties.put("mail.smtp.socketFactory.class",
                "javax.net.ssl.SSLSocketFactory"); //SSL Factory Class

        properties.put("mail.smtp.host","smtp.ukr.net");

        properties.put("mail.smtp.port","465");

        properties.put("mail.smtp.ssl.Enable","true");

        properties.put("mail.smtp.user","sofiamohyla@ukr.net");

        Authenticator auth = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("sofiamohyla@ukr.net", "XhRaw4KhY34ZV9IZ");
            }
        };

        Session session = Session.getDefaultInstance(properties,auth);
        System.out.println("Session created");

        MimeMessage message = new MimeMessage(session);

        message.addHeader("Content-type", "text/HTML; charset=UTF-8");
        message.addHeader("format", "flowed");
        message.addHeader("Content-Transfer-Encoding", "8bit");

        message.setFrom(new InternetAddress("sofiamohyla@ukr.net"));

        message.setReplyTo(InternetAddress.parse("sofiamohyla@ukr.net", false));

        message.setSubject("The critical error occurred","UTF-8");

        message.setText(error,"UTF-8");

        message.setRecipient(Message.RecipientType.TO,new InternetAddress("sofiamohyla@ukr.net"));
        Transport.send(message);
        System.out.println("Message was sent successfully...");

    }
}
