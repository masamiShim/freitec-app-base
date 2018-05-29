package com.freitech.kotetsu.atum.builders;

import java.util.Map;
import java.util.Stack;

import org.apache.commons.lang3.StringUtils;

public class HtmlElementBuilder {

	private static final String HTML = "html";
	private static final String HEAD = "head";
	private static final String BODY = "body";

	private StringBuilder builder;
	private Stack<String> tagHistory;

	public HtmlElementBuilder() {
		builder = new StringBuilder();
		tagHistory = new Stack<>();
	}

	public HtmlElementBuilder tag(String tagName, Map<String, Object> attributes) {
		builder.append("<").append(tagName);
		if (attributes != null) {
			attributes.keySet().stream().forEach(key -> attr(key, attributes.get(key)));
		}
		builder.append(">");
		tagHistory.push(tagName);
		return this;
	}

	private HtmlElementBuilder attr(String attr, Object value) {
		builder.append(" ").append(attr);
		if(StringUtils.isNotBlank(value.toString())) {			
			builder.append("=").append("\"").append(value.toString()).append("\"");
		}
		return this;
	}

	public HtmlElementBuilder tag(String tagName, String attr, Object value) {
		builder.append("<").append(tagName);
		attr(attr, value);
		builder.append(">");
		tagHistory.push(tagName);
		return this;
	}

	public HtmlElementBuilder tag(String tagName) {
		builder.append(startTag(tagName));
		tagHistory.push(tagName);
		return this;
	}

	public HtmlElementBuilder closeTag(String tagName) {
		builder.append(close(tagName));
		return this;
	}

	private String startTag(String tag) {
		return String.format("<%s>", tag);
	}

	public String close(String tag) {
		return String.format("</%s>", tag);
	}

	/**
	 * tagを切る
	 * @return
	 */
	public HtmlElementBuilder close() {
		for (int i = 0; i < tagHistory.size(); i++) {
			builder.append(close(tagHistory.pop()));
		}
		tagHistory.clear();
		return this;
	}

	public HtmlElementBuilder append(String elem) {
		builder.append(elem);
		return this;
	}

	/**
	 * 改行する
	 * @param tagName
	 * @return
	 */
	public HtmlElementBuilder newl() {
		builder.append(System.lineSeparator());
		return this;
	}

	public String toString() {
		return builder.toString();
	}

}
