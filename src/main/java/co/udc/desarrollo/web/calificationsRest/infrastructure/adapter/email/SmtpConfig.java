package co.udc.desarrollo.web.calificationsRest.infrastructure.adapter.email;

public record SmtpConfig(
    String host, int port, String username, String password, String fromAddress, String fromName )
{
}
