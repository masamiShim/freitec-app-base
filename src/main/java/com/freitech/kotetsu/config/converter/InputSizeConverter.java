package com.freitech.kotetsu.config.converter;

public class InputSizeConverter {
	private static final String DEFAULT_INPUT_SIZE = "col-md-6 col-sm-6 col-xs-12 col-md-offset-3";
	private static final String DEFAULT_LABEL_SIZE = "col-md-3 col-sm-3 col-xs-12";
	private static final String DEFAULT_ROW_SIZE = "col-md-12 col-sm-12 col-xs-12";

	static public String convertInputSize(int size) {
		switch (size) {
		case 1:
			return DEFAULT_INPUT_SIZE;
		case 2:
			return DEFAULT_INPUT_SIZE;
		default:
			return DEFAULT_INPUT_SIZE;
		}
	}

	static public String convertLabelSize(int size) {
		switch (size) {
		case 1:
			return DEFAULT_LABEL_SIZE;
		case 2:
			return DEFAULT_LABEL_SIZE;
		default:
			return DEFAULT_LABEL_SIZE;
		}
	}

	static public String convertRowSize(int size) {
		switch (size) {
		case 1:
			return DEFAULT_ROW_SIZE;
		case 2:
			return DEFAULT_ROW_SIZE;
		default:
			return DEFAULT_ROW_SIZE;
		}
	}

}
