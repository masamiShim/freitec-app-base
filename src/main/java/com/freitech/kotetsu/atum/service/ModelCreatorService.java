package com.freitech.kotetsu.atum.service;

import java.util.ArrayList;
import java.util.List;

import com.freitech.kotetsu.atum.ModelItem;

public interface ModelCreatorService {

	List<ModelItem> models = new ArrayList<>();

	List<ModelItem> loadModel();

	List<String> searchModel();
}
