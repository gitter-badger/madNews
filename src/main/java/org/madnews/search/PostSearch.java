package org.madnews.search;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.madnews.entity.Post;
import org.madnews.utils.SearchResponseWrapper;
import org.springframework.stereotype.Repository;

/**
 * Search methods for the entity Post using Hibernate search.
 * The Transactional annotation ensure that transactions will be opened and
 * closed at the beginning and at the end of each method.
 */
@Repository
@Transactional
public class PostSearch {

	  // Spring will inject here the entity manager object
	  @PersistenceContext
	  private EntityManager entityManager;
	    
	  /**
	   * A basic search for the entity Post. The search is done by exact match per
	   * keywords on fields title, shortText and html.
	   * 
	   * @param text The query text.
	   */
	  public SearchResponseWrapper search(String text, int limit, int offset) {
	    
	    // get the full text entity manager
	    FullTextEntityManager fullTextEntityManager =
	        org.hibernate.search.jpa.Search.
	        getFullTextEntityManager(entityManager);
	    
	    // create the query using Hibernate Search query DSL
	    QueryBuilder queryBuilder = 
	        fullTextEntityManager.getSearchFactory()
	        .buildQueryBuilder().forEntity(Post.class).get();
	    
	    // a very basic query by keywords
	    org.apache.lucene.search.Query query =
	        queryBuilder
	          .keyword()
	          .onFields("title", "shortText", "html")
	          .matching(text)
	          .createQuery();

	    // wrap Lucene query in an Hibernate Query object
	    org.hibernate.search.jpa.FullTextQuery jpaQuery =
	        fullTextEntityManager.createFullTextQuery(query, Post.class);
	    jpaQuery.setFirstResult(offset);
	    jpaQuery.setMaxResults(limit);	    
	  
	    // execute search and return results (sorted by relevance as default)
	    
	    Integer number = jpaQuery.getResultSize();
	    List<Post> resultsList = jpaQuery.getResultList();
	    SearchResponseWrapper results = new SearchResponseWrapper(number, resultsList);
	    
	    return results;
	  } 
}
