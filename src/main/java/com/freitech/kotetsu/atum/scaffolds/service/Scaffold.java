package com.freitech.kotetsu.atum.scaffolds.service;

public interface Scaffold {
	/**
	 * テンプレートを作成する
	 * @param clazz
	 * @return
	 */
	boolean create(String beanName); 
	
}
