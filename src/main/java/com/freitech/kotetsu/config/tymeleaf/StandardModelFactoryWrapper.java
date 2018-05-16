package com.freitech.kotetsu.config.tymeleaf;

import java.util.Collections;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

import org.thymeleaf.model.IModel;
import org.thymeleaf.model.IModelFactory;
import org.thymeleaf.model.IOpenElementTag;

public class StandardModelFactoryWrapper {

	private static final int QUEUE_SIZE = 255;

	Queue<String> tags = new ArrayBlockingQueue<>(QUEUE_SIZE);
	private final IModelFactory factory;
	private final IModel element;

	public StandardModelFactoryWrapper(IModelFactory factory) {
		this.factory = factory;
		element = factory.createModel();
	}

	/**
	 * tag open
	 * 
	 * @param tag
	 * @return
	 */
	public StandardModelFactoryWrapper open(String tag, Map<String, String> attributes) {
		IOpenElementTag t = factory.createOpenElementTag(tag);

		attributes.keySet().stream().forEachOrdered(key -> {
			factory.setAttribute(t, key, attributes.get(key));
		});

		tags.add(tag);

		element.add(t);

		return this;
	}

	/**
	 * tag open
	 * 
	 * @param tag
	 * @return
	 */
	public StandardModelFactoryWrapper open(String tag) {
		return this.open(tag, Collections.emptyMap());
	}

	/**
	 * tag with single Attribute
	 * 
	 * @param tag
	 * @return
	 */
	public StandardModelFactoryWrapper open(String tag, String attributeName, String attributeValue) {
		
		IOpenElementTag t = factory.createOpenElementTag(tag);

		factory.setAttribute(t, attributeName, attributeValue);

		tags.add(tag);
		element.add(t);
		
		return this;
	}

	/**
	 * close all tag
	 * 
	 * @return
	 */
	public StandardModelFactoryWrapper close() {
		tags.stream().forEach(t -> element.add(factory.createCloseElementTag(t)));
		return this;
	}

	public IModel build() {
		return this.element;
	}

}
