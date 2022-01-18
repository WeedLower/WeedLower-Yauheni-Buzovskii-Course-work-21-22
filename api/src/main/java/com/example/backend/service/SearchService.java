//package com.example.backend.service;
//
//import com.example.backend.entity.CollectionsEntity;
//import org.apache.lucene.search.Query;
//import org.hibernate.search.jpa.FullTextEntityManager;
//import org.hibernate.search.jpa.Search;
//import org.hibernate.search.query.dsl.QueryBuilder;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import javax.persistence.EntityManager;
//import javax.persistence.NoResultException;
//import javax.transaction.Transactional;
//import java.util.List;
//
//@Service
//public class SearchService {
//
//    private EntityManager entityManager;
//
//    @Autowired
//    public SearchService(EntityManager entityManager) {
//        super();
//        this.entityManager = entityManager;
//    }
//
//    public void initializeHibernateSearch(){
//        try {
//            FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(entityManager);
//            fullTextEntityManager.createIndexer().startAndWait();
//        } catch (InterruptedException e){
//            e.printStackTrace();
//        }
//    }
//
//    @Transactional
//    public List<CollectionsEntity> collectionSearch(String search){
//        FullTextEntityManager fullTextEntityManager =Search.getFullTextEntityManager(entityManager);
//        QueryBuilder qb =  fullTextEntityManager.getSearchFactory().buildQueryBuilder().forEntity(CollectionsEntity.class).get();
//        Query q = qb.keyword().fuzzy().withEditDistanceUpTo(1).withPrefixLength(1).onField("name").matching(search).createQuery();
//
//        javax.persistence.Query jpaQuery = fullTextEntityManager.createFullTextQuery(q,CollectionsEntity.class);
//
//        List<CollectionsEntity> collectionsEntities = null;
//        try {
//            collectionsEntities = jpaQuery.getResultList();
//        } catch (NoResultException ne){
//            ne.printStackTrace();
//        }
//
//        return collectionsEntities;
//    }
//}
