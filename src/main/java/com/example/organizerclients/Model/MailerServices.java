package com.example.organizerclients.Model;

import org.simplejavamail.api.email.Email;
import org.simplejavamail.api.mailer.Mailer;
import org.simplejavamail.api.mailer.config.TransportStrategy;
import org.simplejavamail.mailer.MailerBuilder;


public final class MailerServices {
    private static Mailer mailer;

    static {
        createMailer();
    }

    public static void sendMail(String emailTo) {
        Email mail = MessageMailTextService.createMail(emailTo);
        mailer.sendMail(mail);
        mailer.getOperationalConfig()
                .getExecutorService().shutdownNow();
    }

    private static Mailer createMailer() {
        if (mailer == null) {
            mailer = MailerBuilder
                    .withTransportStrategy(TransportStrategy.SMTP_TLS)
                    .async()
                    .buildMailer();
        }
        return mailer;
    }
}

