package com.freitech.kotetsu.forms.bases;

import java.time.LocalDate;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.freitech.kotetsu.config.annotations.ViewAttribute;

import lombok.Data;

@Data
public class FromToSearchForm {
	@NotNull(message = "開始日は必須です")
	@ViewAttribute(name = "開始日", placeholder = "yyyy-mm-dd")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate startDate;

	@ViewAttribute(name = "終了日", placeholder = "yyyy-mm-dd")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate endDate;

	/**
	 * FromToの前後関係チェックする
	 * 
	 * @return 両方入力されている場合に整合性をチェック
	 */
	@AssertTrue
	public boolean validFromTo() {

		if (startDate != null && endDate != null && endDate.isBefore(startDate)) {
			return false;
		}

		return true;
	}

	public FromToSearchForm of(LocalDate initDate) {
		FromToSearchForm form = new FromToSearchForm();
		form.startDate = initDate;
		return form;
	}

}
