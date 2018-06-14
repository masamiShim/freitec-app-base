package com.freitech.kotetsu.controllers.mail;

import java.security.Security;
import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Store;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

// FIXME: 重いから何とかしよう
@Component
public class GmailReceiver {

	@Value("${com.freitech.base.pageSize:20}")
	private int pageSize = 20;

	// FIXME: 取得するメール情報を可変にする場合はDBより取得
	@Value("${gmail.receiver.host:imap.gmail.com}")
	private String HOST;
	@Value("${gmail.receiver.user:}")
	private String USER;

	@Value("${gmail.receiver.password:}")
	private String PASSWORD;

	@Value("${gmail.receiver.port:993}")
	private String PORT;

	private Properties props = new Properties();

	private MailDataList received;

	private Session session;

	@PostConstruct
	@SuppressWarnings("restriction")
	public void init() {
		Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());

		props.put("mail.imap.starttls.enable", "true");
		props.put("mail.imap.auth", "true");
		props.put("mail.imap.socketFactory.port", PORT);
		props.put("mail.imap.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.imap.socketFactory.fallback", "false");

		session = Session.getDefaultInstance(props, null);

	}

	public MailDataList receive() {

		received = new MailDataList();
		Store store = null;

		try {
			store = session.getStore("imap");
			store.connect(HOST, USER, PASSWORD);
			// IMAPの場合はラベル名を指定すればそのラベルのメールが取得出来る
			// (POP3の場合はエラーが発生します)
			Folder folder = store.getFolder("INBOX");
			folder.open(Folder.READ_ONLY);

			// メッセージ件数分
			for (Message message : folder.getMessages(1, pageSize)) {
				received.add(message);
			}
			folder.close(false);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				if (store != null) {
					store.close();
				}
			}
			catch (MessagingException e) {
				e.printStackTrace();
			}
		}
		return received;
	}

	public MailDataList receive(int page) {

		Store store = null;

		try {
			store = session.getStore("imap");
			store.connect(HOST, USER, PASSWORD);
			// IMAPの場合はラベル名を指定すればそのラベルのメールが取得出来る
			// (POP3の場合はエラーが発生します)
			Folder folder = store.getFolder("INBOX");
			folder.open(Folder.READ_ONLY);
			// メッセージ件数分
			for (Message message : folder.getMessages(1, pageSize * page)) {
				received.add(message);
			}
			folder.close(false);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				if (store != null) {
					store.close();
				}
			}
			catch (MessagingException e) {
				e.printStackTrace();
			}
		}
		return received;
	}

}
