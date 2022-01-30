package com.example.backend.service;

import com.example.backend.entity.ItemsEntity;
import org.apache.lucene.search.Query;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import java.util.List;


@Service
public class SearchService implements SearchServiceItr{

    private final EntityManager entityManager;

    @Autowired
    public SearchService(EntityManagerFactory manager) {
        this.entityManager = manager.createEntityManager();
    }

    @PostConstruct
    public void initializeHibernateSearch(){
        try {
            FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(entityManager);
            fullTextEntityManager.createIndexer().startAndWait();
        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    @Transactional
    @Override
    public List<ItemsEntity> searchItems(String searchItm) {
        FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(entityManager);

        QueryBuilder qb = fullTextEntityManager.getSearchFactory().buildQueryBuilder().forEntity(ItemsEntity.class).get();

        Query luceneQuery = qb.keyword()
                .fuzzy()
                .withEditDistanceUpTo(2)
                .withPrefixLength(1)
                .onFields("name","optionalStringField1","optionalStringField2","optionalStringField3",
                        "optionalTextField1","optionalTextField2","optionalTextField3","comments.comment",
                        "collection.name","collection.description")
                .matching(searchItm+"*").createQuery();

        javax.persistence.Query jpaQuery = fullTextEntityManager.createFullTextQuery(luceneQuery,ItemsEntity.class);

        List<ItemsEntity> itemList = null;
        try {
            itemList = jpaQuery.getResultList();
        }catch (NoResultException nre){
            nre.printStackTrace();
        }
        return itemList;
    }
}
