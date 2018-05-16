package com.freitech.kotetsu.config.setting;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class PathBuilder {
	private final String DISP = "disp";
	private final String INDEX = "index";
	private final String INPUT = "input";
	private final String DETAIL = "detail";
	private final String DELETE = "delete";
	private final String CONFIRM = "confirm";
	private final String COMPLETE = "complete";
	private final String CREATE = "create";
	private final String BACK = "back";
	private final String LIST = "list";

	private final StringBuilder PATH = new StringBuilder();
	private final StringBuilder PARAM = new StringBuilder();

	public PathBuilder join(Path path) {
		PATH.append("/").append(path.getPath());
		return this;
	}

	public PathBuilder join(Object path) {
		PATH.append("/").append(path);
		return this;
	}

	public PathBuilder list() {
		PATH.append("/").append(LIST);
		return this;
	}

	public PathBuilder index() {
		PATH.append("/").append(INDEX);
		return this;
	}

	public PathBuilder input() {
		PATH.append("/").append(INPUT);
		return this;
	}

	public PathBuilder confirm() {
		PATH.append("/").append(CONFIRM);
		return this;
	}

	public PathBuilder complete() {
		PATH.append("/").append(COMPLETE);
		return this;
	}

	public PathBuilder delete() {
		PATH.append("/").append(DELETE);
		return this;
	}

	public PathBuilder detial() {
		PATH.append("/").append(DETAIL);
		return this;
	}

	public PathBuilder redirect() {
		PATH.insert(0, "redirect:");
		return this;
	}

	public PathBuilder forward() {
		PATH.insert(0, "forward:");
		return this;
	}

	public PathBuilder addParam(String id, Object obj) {
		PARAM.append(id).append("=").append(obj.toString());
		return this;
	}

	public String build() {
		if (PARAM.length() > 0) {
			PATH.append("?").append(PARAM.toString());
		}
		return PATH.toString();
	}
}
