package ru.savinov.shop.webservices;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.mail.Authenticator;
import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;


@Service
public class EmaiService {

    @Value("${mail.user.password}")
    private String emailPassword;

    @Value("${mail.user.login}")
    private String emailLogin;

    @Value("${mail.inbox.protocol}")
    private String mailInboxProtocol;
    private final String INBOX_PROTOCOL = "mail.store.protocol";

    @Value("${mail.host}")
    private String mailHost;

    @Value("${mail.smtp.host}")
    private String mailSmtpHost;
    private final String MAIL_SMTP_HOST = "mail.smtp.host";


    @Value("${mail.smtp.ssl.enable}")
    private String mailSmtpSslEnable;
    private final String MAIL_SMTP_SSL_ENABLE = "mail.smtp.ssl.enable";

    @Value("${mail.smtp.ssl.port}")
    private String mailSmtpSslPort;
    private final String MAIL_SMTP_SSL_PORT = "mail.smtp.ssl.port";

    @Value("${mail.smtp.ssl.auth}")
    private String mailSmtpSslAuth;
    private final String MAIL_SMTP_SSL_AUTH = "mail.smtp.auth";

    Properties properties;

    private final String INBOX = "INBOX";

    private final String TO = "savinov_vu@mail.ru";

    private Folder initInputMessagesConnect() {
        Folder inbox = null;
        Properties prop = new Properties();
        prop.put(INBOX_PROTOCOL, mailInboxProtocol); //SSL
        try {
            Store store = Session.getDefaultInstance(prop).getStore();
            store.connect(mailHost, emailLogin, emailPassword);
            inbox = store.getFolder(INBOX);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return inbox;
    }

    private Session initSendSession() {
        Properties prop = new Properties();
        prop.setProperty(MAIL_SMTP_HOST, mailSmtpHost);
        prop.setProperty(MAIL_SMTP_SSL_ENABLE, mailSmtpSslEnable);
        prop.setProperty(MAIL_SMTP_SSL_PORT, mailSmtpSslPort);
        prop.setProperty(MAIL_SMTP_SSL_AUTH, mailSmtpSslAuth);
        Session session = Session.getDefaultInstance(prop, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(emailLogin, emailPassword);
            }
        });
        return session;
    }

    public Integer getCountInputMessages() {
        Folder inbox = initInputMessagesConnect();
        try {
            inbox.open(Folder.READ_ONLY);
            return inbox.getMessageCount();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void deleteLastInputMessage() {
        Message message = null;
        Folder inbox = initInputMessagesConnect();
        try {
            message = inbox.getMessage(inbox.getMessageCount());
            message.setFlag(Flags.Flag.DELETED, Boolean.TRUE);
        } catch (MessagingException e) {
            e.printStackTrace();
        } finally {
            try {
                inbox.close();
            } catch (MessagingException e) {
                e.printStackTrace();
            }
        }
    }

    public void sendMail(String titleMessage, String bodyMessage, InternetAddress [] internetAddresses) {
        Session session = initSendSession();
        Message msg = new MimeMessage(session);
        try {
            msg.setFrom(new InternetAddress(emailLogin));
            msg.setRecipients(Message.RecipientType.TO, internetAddresses);
            msg.setSubject(titleMessage);
            msg.setSentDate(new Date());
            msg.setText(bodyMessage);
            Transport.send(msg);
        } catch (MessagingException e) {
            e.printStackTrace();
        }

    }
}
