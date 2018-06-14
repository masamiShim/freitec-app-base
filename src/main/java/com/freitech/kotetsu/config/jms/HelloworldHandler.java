package com.freitech.kotetsu.config.jms;

import java.util.List;

import com.freitech.kotetsu.models.information.Information;

public class HelloworldHandler {
	public void handleMessage(List<Information> list) {
		System.out.println("Received > " + list.toString());
	}
}
