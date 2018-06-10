package com.freitech.kotetsu.db;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;


@NoRepositoryBean
public interface RepositoryBase<T> extends JpaRepository<T, Long>, JpaSpecificationExecutor<T> {

	/**
	 * 未削除データを取得する。
	 * 
	 * @return
	 */
	Optional<T> findByIdAndDeletedIsNull(Long id);

	/**
	 * 未削除データを取得する。
	 * 
	 * @return
	 */
	Optional<List<T>> findByDeletedIsNull();

}
