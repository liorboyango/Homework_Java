package homework.Utils;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.sql.SQLException;
import java.util.Properties;
import javax.mail.Authenticator;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class sendEmail {

    private static SecureRandom random = new SecureRandom();
    final static String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
    final static String username = "homeworkapp2016@gmail.com"; //email to send from
    final static String password = "myHwA2016"; //email to send from - password

    public static boolean send(String userEmail) throws UnsupportedEncodingException {
        
        Properties props = new Properties();
        props.setProperty("mail.smtp.host", "smtp.gmail.com");
        props.setProperty("mail.smtp.socketFactory.class", SSL_FACTORY);
        props.setProperty("mail.smtp.socketFactory.fallback", "false");
        props.setProperty("mail.smtp.port", "465");
        props.setProperty("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.auth", "true");
        props.put("mail.debug", "true");
        props.put("mail.store.protocol", "pop3");
        props.put("mail.transport.protocol", "smtp");
        Session session = Session.getDefaultInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        String newPassword = resetPasswordDB(userEmail);
        if(newPassword.isEmpty())
            return false;
        
        String msgBody = "This is your new Password:'" + newPassword + "' Use it to Login.";

        try {
            Message msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress(username, "Homework App"));
            msg.addRecipient(Message.RecipientType.TO,
                    new InternetAddress(userEmail, ""));
            msg.setSubject("Password Reset");
            msg.setText(msgBody);
            Transport.send(msg);
            System.out.println("Email Was Successfully Sent");
            return true;

        } catch (AddressException ex) {
            System.out.println("Failed to send Email");
            ex.printStackTrace();
            return false;
        } catch (MessagingException ex) {
            System.out.println("Failed to send Email");
            ex.printStackTrace();
            return false;
        } catch (Exception ex) {
            System.out.println("Failed to send Email");
            ex.printStackTrace();
            return false;
        }
    }

    private static String resetPasswordDB(String email) {//resets the password in database for user with given email
        String newPassword = generateNewPassword();
        try {
            DataBase.getInstance().resetPassword(email, newPassword);
        } catch (SQLException ex) {
            ex.printStackTrace();
            return "";
        }
        return newPassword;
    }

    private static String generateNewPassword() {   //generates random string with digits and letters
        return new BigInteger(130, random).toString(32);

    }

}
