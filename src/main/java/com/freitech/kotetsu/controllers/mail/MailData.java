package com.freitech.kotetsu.controllers.mail;

import java.io.IOException;
import java.time.LocalDate;
import java.time.temporal.Temporal;
import java.util.Date;
import java.util.List;

import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.thymeleaf.extras.java8time.util.TemporalCreationUtils;

import com.freitech.kotetsu.controllers.mail.exception.MessageHandleException;

import lombok.Data;

@Data
public class MailData {

	@Value("${com.freitech.base.mail.overviewLength:100}")
	private int overviewLength;

	private Address sender;

	private Date receiveDate;

	private String title;

	private String contents;

	private List<Multipart> attached;

	public MailData(Message message) {
		try {
			sender = message.getFrom()[0];
			System.out.println(sender);
			title = message.getSubject();
			receiveDate = message.getReceivedDate();
			contents = getText(message.getContent());
		}
		catch (IOException | MessagingException e) {
			throw new MessageHandleException();
		}
	}

	private String getText(Object content) throws IOException, MessagingException {
		String text = null;
		StringBuffer sb = new StringBuffer();

		if (content instanceof String) {
			String temp = (String) content;
			if(temp.length() < 100) {
				sb.append(temp, 100, temp.length());				
			}else {
				sb.append(temp, 100, 100);								
			}
		}
		/*
		else if (content instanceof Multipart) {
			Multipart mp = (Multipart) content;
			
			for (int i = 0; i < mp.getCount(); i++) {
				BodyPart bp = mp.getBodyPart(i);
				sb.append(getText(bp.getContent()));
			}
		}*/

		text = sb.toString();
		return text;
	}

	/**
	 * 概要を取得する
	 * @return
	 */
	public String getOverview() {
		if (StringUtils.isNotBlank(contents)) {
			return StringUtils.EMPTY;
		}
		return contents.length() >= overviewLength ? StringUtils.substring(contents, 0, overviewLength)
		  : StringUtils.substring(contents, 0, contents.length());
	}
}
