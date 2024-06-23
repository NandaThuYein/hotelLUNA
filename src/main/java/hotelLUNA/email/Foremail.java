package hotelLUNA.email;

import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import hotelLUNA.bean.CustomBook;
import hotelLUNA.bean.Customer;

@Service
public class Foremail {

	@Autowired
	JavaMailSender jms;
	
	
	public void sendemail(Customer cus,CustomBook ci) {
		
		SimpleMailMessage smm = new SimpleMailMessage();
		smm.setTo(cus.getCemail());
		smm.setSubject("Your receipt");
		smm.setText("hello "+cus.getCname()+"\n"+"You can cancel booking before "+ci.getBookCheckIn()+
				"if u want to do"+"\n"+"Cancel ur booking :"+"http://localhost:8080/hotelLUNA/tomail");
		
		System.out.println(jms);
		
		jms.send(smm);
		
	}
	
	public void thz(Customer c) {
		SimpleMailMessage smm = new SimpleMailMessage();
		smm.setTo(c.getCemail());
		smm.setSubject("Reply");
		smm.setText("Your cancelation is Successful !!!! "+"\n"+"Thank you,"+c.getCname());
		
		System.out.println(jms);
		
		jms.send(smm);
	}
}
