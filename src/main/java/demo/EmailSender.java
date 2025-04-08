package demo;

import jakarta.mail.*;
import jakarta.mail.internet.*;
import jakarta.activation.*;

import java.io.File;
import java.util.Properties;

public class EmailSender {

    public static void sendEmailWithReport(String toEmail, String subject, String body, String reportPath) {
        final String fromEmail = "ahmadaljahed669@gmail.com";        // ✉️ إيميل المرسل
        final String password = "jfrlcpzgiegrldrh";            // 🔐 كلمة مرور التطبيق

        // إعدادات SMTP
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        // جلسة البريد
        Session session = Session.getInstance(props, new jakarta.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromEmail, password);
            }
        });

        try {
            // إنشاء الرسالة
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(fromEmail));
            message.setRecipients(
                    Message.RecipientType.TO, InternetAddress.parse(toEmail));
            message.setSubject(subject);

            // 1. الجزء النصي من الرسالة
            MimeBodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setText(body);

            // 2. الجزء الخاص بالمرفق (تقرير Extent)
            MimeBodyPart attachmentPart = new MimeBodyPart();
            File file = new File(reportPath);
            if (!file.exists()) {
                System.out.println("❌ Report file not found: " + reportPath);
                return;
            }
            DataSource source = new FileDataSource(file);
            attachmentPart.setDataHandler(new DataHandler(source));
            attachmentPart.setFileName(file.getName());

            // 3. تجميع الرسالة مع المرفق
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBodyPart);
            multipart.addBodyPart(attachmentPart);

            message.setContent(multipart);

            // إرسال الرسالة
            Transport.send(message);

            System.out.println("✅ Email with report sent successfully.");

        } catch (MessagingException e) {
            e.printStackTrace();
            System.out.println("❌ Failed to send email with report.");
        }
    }

    public static void main(String[] args) {
        sendEmailWithReport("ahmadaljahed669@gmail.com", "Test Send Email With Report", "Hello This Ahmad From Team " +
                "Of" +
                " QA I send this email to test send Report Emial", "/Users/mac/selenium-tests/reports/ExtentReport.html");
    }
}
