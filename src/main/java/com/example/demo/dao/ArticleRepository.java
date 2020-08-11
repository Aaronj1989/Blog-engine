package com.example.demo.dao;


import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.editor.Article;
@Repository
public interface ArticleRepository extends PagingAndSortingRepository<Article, Integer> {

	
	
	   
}
