package com.freitech.kotetsu.controllers.mail;

import java.util.ArrayList;
import java.util.List;

import javax.mail.Message;

import org.apache.commons.collections.CollectionUtils;

import lombok.Getter;

@Getter
public class MailDataList {

	private int currentPage = 0;

	private int maxPage = 0;

	private final List<MailData> list = new ArrayList<>();

	public void add(Message message) {
		list.add(new MailData(message));
	}

	public void remove(MailData data) {
		list.remove(data);
	}

	public int size() {
		return list.size();
	}

	public boolean isNotEmpty() {
		return CollectionUtils.isNotEmpty(list);
	}

}
