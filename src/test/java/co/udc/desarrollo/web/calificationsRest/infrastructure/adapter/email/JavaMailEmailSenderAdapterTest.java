package co.udc.desarrollo.web.calificationsRest.infrastructure.adapter.email;

import co.udc.desarrollo.web.calificationsRest.domain.models.EmailDestinationModel;
import jakarta.mail.internet.MimeMessage;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class JavaMailEmailSenderAdapterTest {

    @Test
    void buildMessageUsesHtmlBodyValueAsContent() throws Exception {
        final JavaMailEmailSenderAdapter adapter = new JavaMailEmailSenderAdapter(
                new SmtpConfig(
                        "smtp.gmail.com",
                        587,
                        "sender@example.com",
                        "password",
                        "sender@example.com",
                        "Gestion de Usuarios"));
        final EmailDestinationModel destination = new EmailDestinationModel(
                "maria@example.com",
                "Maria Gomez",
                "Cuenta creada",
                "<h1>Hola Maria</h1>");

        final MimeMessage message = adapter.buildMessage(destination);
        message.saveChanges();

        assertThat(message.getContent()).isEqualTo("<h1>Hola Maria</h1>");
        assertThat(message.getContentType()).contains("text/html");
    }
}
