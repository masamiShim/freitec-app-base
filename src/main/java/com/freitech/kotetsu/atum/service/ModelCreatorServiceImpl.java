package com.freitech.kotetsu.atum.service;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.freitech.kotetsu.atum.ModelItem;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ModelCreatorServiceImpl implements ModelCreatorService {

	@Value(value = "${com.freitech.model.creator.folder.path:}")
	private String serachRootPath;

	@Value(value = "${com.freitech.model.creator.folder.basePackage:}")
	private String basePackage;

	@Override
	public List<ModelItem> loadModel() {
		// TODO Auto-generated method stub
		return null;
	}

	/**	取得するファイル（ディレクトリ含む）のフィルタ設定 */
	FilenameFilter fFilter = new FilenameFilter() {
		@Override
		public boolean accept(File dir, String name) {
			// 指定文字列でフィルタする
			if (name.indexOf("model") != -1 || name.indexOf("models") != -1) {
				return true;
			}
			else {
				return false;
			}
		}
	};

	@Override
	public List<String> searchModel() {

		List<String> modelNames = new ArrayList<>();
		for (File f : new File(serachRootPath).listFiles(fFilter)) {
			exploreFolder(f, modelNames, basePackage);
		}
		return modelNames;
	}

	private void exploreFolder(File f, List<String> modelFiles, String packageName) {
		if (f.isDirectory()) {
			packageName = packageName.concat(".").concat(f.getName());
			for (File _f : f.listFiles()) {
				exploreFolder(_f, modelFiles, packageName);
			}
		}
		else if (f.isFile() && f.getName().contains(".java")) {
			modelFiles.add(packageName.concat(".").concat(StringUtils.remove(f.getName(), ".java")));
		}
	}

}
