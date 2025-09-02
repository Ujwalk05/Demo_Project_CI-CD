package com.winmore.testactions;

import javax.mail.*;
import javax.mail.internet.*;

import com.winmore.base.TestBase;

import javax.activation.*;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

public class EmailReport extends TestBase{

    
    
    public static void sendMailReport()  {
        
        String ExecutedSuite = executionsuite;
        String EnvValue = environment;
        // Sender's email ID and password
        final String username = "winmoreautomationtester@gmail.com";
        final String password = "rtyvmyauohigbudx";

        // Recipient's email ID
        //String to = "giri.bathini@winmore.app";

        // SMTP server information
        String host = "smtp.gmail.com"; // e.g., smtp.gmail.com for Gmail
        String port = "587"; // or "465" for SSL

        // Get system properties
        Properties properties = new Properties();

        // Setup mail server
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", port);

        // Get the default Session object
        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            
            // Find the latest report file
            File latestReport = findLatestReport();
            File htmlReport = findHTMLReport();
     

            if (latestReport != null || htmlReport != null) {
            //if (latestReport.exists() && htmlReport.exists()) {
            
            // Create a default MimeMessage object
            MimeMessage message = new MimeMessage(session);

            // Set From: header field
            message.setFrom(new InternetAddress(username));

            // Set To: header field
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(maillist));
          //  message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            DateFormat dff = new SimpleDateFormat("EEE MMM dd, yyyy HH:mm:ss z");
            // Set Subject: header field
            message.setSubject( ExecutedSuite + " – "+ EnvValue +" – " + dff.format(new Date()).toString());
         //   message.setSubject("Automation Test Report");

            // Create the message part
            BodyPart messageBodyPart = new MimeBodyPart();
            BodyPart attachmentPart = new MimeBodyPart();

            // Now set the actual message
            
            /*
             * StringBuffer body = new
             * StringBuffer("<html>Hi, <br><br>Please find the attached Extent Report for the latest test execution."
             * +"Winmore"); body.append("<br><br>");
             * //body.append(AutomationReport.createEmailBody(scenarioList));
             * //body.append("<img src=\"cid:image\" width=\"100%\" height=\"100%\" /><br>"
             * ); body.append("<br><br><br> Thanks,<br>Automation Team");
             * body.append("</html>");
             * messageBodyPart.setContent(body.toString(),"text/html; charset=utf-8");
             */
            
               String emailContent = String.format("Hi,%n%n" +
                       "Please find the attached Extent Report for the latest test execution.%n%n" +
                       "Summary of Execution:%n" +
                       "Total Test Cases: %d%n" +
                       "Passed: %d%n" +
                       "Failed: %d%n" +
                       "Skipped: %d%n%n" +
                       "Thanks and Regards,%n" +
                       "Winmore Automation Team",
                       TestSummary.totalTests, TestSummary.passedTests, TestSummary.failedTests, TestSummary.skippedTests);
               messageBodyPart.setText(emailContent);
            
            
           // messageBodyPart.setText("Please find the attached Extent Report for the latest test execution.");

            // Create a multipar message
            Multipart multipart = new MimeMultipart();

            // Set text message part
            multipart.addBodyPart(messageBodyPart);

            // Part two is the attachment
            messageBodyPart = new MimeBodyPart();
           // String filename = "Reports/SparkReport 16-Aug-24 14-45-21/PDFReport/ExtentPdf.pdf"; // path to the report
            DataSource source = new FileDataSource(latestReport);
            messageBodyPart.setDataHandler(new DataHandler(source));
            messageBodyPart.setFileName(ExecutedSuite + "_"+ EnvValue +"_" +dff.format(new Date()).toString()+".pdf");
            multipart.addBodyPart(messageBodyPart);
            
            //Attach HTML Reprt
            attachmentPart = new MimeBodyPart();
           // String filename = "Reports/SparkReport 16-Aug-24 14-45-21/PDFReport/ExtentPdf.pdf"; // path to the report
            DataSource source1 = new FileDataSource(htmlReport);
            attachmentPart.setDataHandler(new DataHandler(source1));
            attachmentPart.setFileName(ExecutedSuite + "_"+ EnvValue +"_" +dff.format(new Date()).toString()+".html");
            multipart.addBodyPart(attachmentPart);

            // Attach healed_locators.json
            try {
                File healedLocatorsFile = new File(System.getProperty("user.dir") + "/src/test/resources/healed_locators.json");
                if (healedLocatorsFile.exists()) {
                    BodyPart healedLocatorsPart = new MimeBodyPart();
                    DataSource healedSource = new FileDataSource(healedLocatorsFile);
                    healedLocatorsPart.setDataHandler(new DataHandler(healedSource));
                    healedLocatorsPart.setFileName("healed_locators.json");
                    multipart.addBodyPart(healedLocatorsPart);
                }
            } catch (Exception e) {
                System.out.println("Could not attach healed_locators.json: " + e.getMessage());
            }

            // Send the complete message parts
            message.setContent(multipart);

            // Send message
            Transport.send(message);
            System.out.println("Sent message successfully....");
            } else {
                System.out.println("No report found to attach.");
            }
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }

    
    

    private static File findHTMLReport() {
          File reportsDir = new File("Reports");
            File[] directories = reportsDir.listFiles(File::isDirectory);

            if (directories != null && directories.length > 0) {
                // Sort directories by last modified date descending
                File latestDir = directories[0];
                for (File dir : directories) {
                    if (dir.lastModified() > latestDir.lastModified()) {
                        latestDir = dir;
                    }
                }

                // Look for the PDF report in the latest directory
                File htmlReport = new File(latestDir, "Reports/Spark.html");
                if (htmlReport.exists()) {
                    return htmlReport;
                }
            }
            return null;
    }




    private static File findLatestReport() {
          File reportsDir = new File("Reports");
            File[] directories = reportsDir.listFiles(File::isDirectory);

            if (directories != null && directories.length > 0) {
                // Sort directories by last modified date descending
                File latestDir = directories[0];
                for (File dir : directories) {
                    if (dir.lastModified() > latestDir.lastModified()) {
                        latestDir = dir;
                    }
                }

                // Look for the PDF report in the latest directory
                File pdfReport = new File(latestDir, "PDFReport/ExtentPdf.pdf");
                if (pdfReport.exists()) {
                    return pdfReport;
                }
            }
            return null;
        }
    
    
    
}

