package com.toyota.esy.service;


import com.toyota.esy.dao.TravelDAO;
import com.toyota.esy.dto.TraveldetailsDTO;
import com.toyota.esy.model.Traveldetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;


@Service
public class MailReportService {

    @Autowired
    private TravelDAO travelDAO;

    /**
     *  Bildirimleri açık olan silinmemiş kullanıcılar tespit edilir
     *  Ay Gün ve Hafta sağlayan kullanıcıların mail adresleri alınır
     * **/
    public List<TraveldetailsDTO> timelyUser() {
        List<TraveldetailsDTO> traveldetails = new ArrayList<TraveldetailsDTO>();
        try {

            DateFormat day = new SimpleDateFormat("W,EEEE");//4 tane E de Tam yazı geliyor
            Calendar calendar = Calendar.getInstance();
            String strDate = day.format(calendar.getTime());
            Date now =new Date();

            //Aylık olan kullanıcıların takibi
            if (strDate.endsWith("1")) {
                String notification="Aylık, "+strDate.substring(2,strDate.length());
                for (Traveldetails travel : travelDAO.userMail( notification, new java.sql.Date(now.getTime()))) {
                    traveldetails.add(new TraveldetailsDTO().convert(travel));
                }
            }
            //Haftalık olan kullanıcıların takibi
            else
            {
                String notification="Haftalık, "+strDate.substring(2,strDate.length());
                for (Traveldetails travel : travelDAO.userMail(notification,new java.sql.Date(now.getTime()))) {
                    traveldetails.add(new TraveldetailsDTO().convert(travel));

                }
            }
        }catch (Exception e){
            return null;
        }
        return traveldetails;
    }
    /**
     *  Mail gönderme işlemine hazırlık burada yapılır
     *  Mail adresleri mail içeriği burada düzenlenir
     *  Bulk messaging Toplu mesaj atmak için
     * */
    public void bulkMessaging() {
        //TODO : development
        //Mail gönderme işlemi burada yapılacak
        String from = "root.toyota@gmail.com";
        String pass = "toyota.root";

        List<TraveldetailsDTO> forMailList=timelyUser();
        String[] to = new String[forMailList.size()]; // list of recipient email addresses
        String [] body = new String[forMailList.size()];
        for (int i=0 ; i<forMailList.size();i++)
        {
            to[i]=forMailList.get(i).getUser().getUserMail();
            body[i] ="Sayın "+forMailList.get(i).getUser().getUserName()+"\n"+
                    forMailList.get(i).getTravelLocation()+ " lokasyonuna seyahatiniz var.\n "
                    +forMailList.get(i).getTravelStartDate()+" tarihinde başlayıp "
                    +forMailList.get(i).getTravelFinishDate()
                    +" tarihinde bitmektedir."+
                    "\nDetaylı bilgi için sisteme giriş yapınız. ";
        }
        String subject = "Toyota Bilgilendirme Servisi";
        sendFromGMail(from, pass, to, subject, body);
    }

    /**
     * Mail gönderme işlemi yapılır Toplu mesaj için uygun
     * */
    //Smtp sunucu üzerinden mail gönderme TLS bağlantı kuruldu
    private static void sendFromGMail(String from, String pass, String[] to, String subject, String []body) {

        Properties props = System.getProperties();
        String host = "smtp.gmail.com";
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.user", from);
        props.put("mail.smtp.password", pass);
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");

        Session session = Session.getDefaultInstance(props);// protokoller sessiona(Javax mail nesnesi) atıldı
        MimeMessage message = new MimeMessage(session);//session mesaj olcak hale geldi

        try {
            message.setFrom(new InternetAddress(from));
            InternetAddress[] toAddress = new InternetAddress[to.length];//Giddecek olan kullanıcı kadar adres belirlendi

            // To get the array of addresses
            for (int i = 0; i < to.length; i++) {
                toAddress[i] = new InternetAddress(to[i]);
            }
            //Adres kadar mail gidicek
            for (int i = 0; i < toAddress.length; i++) {
                //message.addRecipient(Message.RecipientType.TO, toAddress[i]);
                message.setRecipient(Message.RecipientType.TO,toAddress[i]);
                message.setSubject(subject,"ISO-8859-9");// Türkçe karakter problemi için
                message.setText(body[i],"ISO-8859-9");// Türkçe karakter problemi için
                Transport transport = session.getTransport("smtp");
                transport.connect(host, from, pass);
                transport.sendMessage(message, message.getAllRecipients());
                transport.close();
            }
        } catch (AddressException ae) {
            ae.printStackTrace();
        } catch (MessagingException me) {
            me.printStackTrace();
        }
    }
}