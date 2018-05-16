package com.freitech.kotetsu.atum;

import lombok.Getter;

@Getter
public enum ModelType {

												LONG("Long", Long.class),
												STRING("String", String.class),
												LIST("List", java.util.List.class),
												ENUM("Enum", Enum.class),
												UNKNOWN("Unknown", null);

	public String type;

	public Class<?> clazz;

	private ModelType(final String type, final Class<?> clazz) {
		this.type = type;
		this.clazz = clazz;
	}

	public static ModelType getTypeFromEnumValue(String type) {
		for (ModelType mType : ModelType.values()) {
			if (mType.getType().equals(type))
				return mType;
		}
		return ModelType.UNKNOWN;
//		return Arrays.stream(ModelType.values()).filter(e -> e.getType().equals(type)).findFirst()
//		  .orElse(ModelType.UNKNOWN);
	}
}
