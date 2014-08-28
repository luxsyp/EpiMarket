/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package services;
 
import ORM.model.AbstractProduct;
import ORM.model.Client;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class GmailMail {

        private Properties props = new Properties();
        private Session session = null;
        
        private String host         = "smtp.gmail.com";
        private int port            = 587;
        private String username     = "EpiMarketMail";
        private String password     = "epimarket42";
        
        public GmailMail()
        { 
            
 
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
 
        session = Session.getDefaultInstance(props,
            new javax.mail.Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(username, password);
                }
            });

        }

        public void sendMail(List<Client> userlist, List<AbstractProduct> gameList, List<AbstractProduct> dvdList)
        {
            if (session != null)
            {
                try 
                {
                    Message message = new MimeMessage(session);
                    message.setFrom(new InternetAddress("Admin@EpiMarket.com"));
                    
                    // Ajout des destinataires                    
                    Iterator i = userlist.iterator();
                    while (i.hasNext())
                    {
                        Client tmp = (Client)i.next();
                        message.addRecipients(Message.RecipientType.TO,
                                    InternetAddress.parse(tmp.getMail()));
                    }

                    // CONSTRUCTION DU MESSAGE 
                    
                    Calendar c = Calendar.getInstance(); 
                    message.setSubject("NewsLetter du " 
                            + c.get(Calendar.DAY_OF_WEEK_IN_MONTH) 
                            + "/" + (c.get(Calendar.MONTH) + 1)
                            + "/" + c.get(Calendar.YEAR));
                    String msg = ""
                            + "<div> "
                            
                            + "<br />"
                            + "<div style=\"font-size: 16px\" >"
                            + "EpiMarket NewsLetter du " 
                            + c.get(Calendar.DAY_OF_WEEK_IN_MONTH) 
                            + "/" + (c.get(Calendar.MONTH) + 1)
                            + "/" + c.get(Calendar.YEAR)
                            + "</div>"
                            + "<br />"

                            + "<div style=\"font-size: 14px\" >"
                            + "Dernieres sorties Dvd :"
                            + "</div>"
                            + "<br />";
                    
                    i = gameList.iterator();
                    int count = 0;
                    while (i.hasNext() && count < 3)
                    {
                        AbstractProduct tmp = (AbstractProduct) i.next();
                        ++count;
                        msg += "\t- " + tmp.getName() + "\t\t-\t" + tmp.getPrice() + "e<br />";
                    }
                                   
                            msg += "<br /><div style=\"font-size: 14px\" >"
                            + "Dernieres sorties Jeu :"
                            + "</div>"
                            + "<br />";

                    i = dvdList.iterator();
                    count = 0;
                    while (i.hasNext() && count < 3)
                    {
                        AbstractProduct tmp = (AbstractProduct) i.next();
                        ++count;
                        msg += "\t- " + tmp.getName() + "\t\t-\t" + tmp.getPrice() + "e<br />";
                    }

                           
                            msg += "</div>";

                    // MESSAGE CONSTRUIT
                            
                    message.setContent(msg, "text/html"); 
                    Transport transport = session.getTransport("smtp");
                    transport.connect(host, port, username, password);
                    Transport.send(message);
 
                    System.out.println("NewsLetter envoye !");               

                    }
                    catch (MessagingException e)
                    {
                        throw new RuntimeException(e);
                    }
                }
        }           
}
