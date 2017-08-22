/**
 * 
 */
package com.bebo.elastic;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @author anthakur
 *
 */
public interface BookRepository extends ElasticsearchRepository<Book, String>{

	Page<Book> findByAuthor(String auther, Pageable pageable);
	List<Book> findByTitle(String title);
}
