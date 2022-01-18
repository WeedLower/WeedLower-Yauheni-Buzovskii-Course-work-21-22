//package com.example.backend.config;
//
//import com.example.backend.service.SearchService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import javax.persistence.EntityManager;
//
//@EnableAutoConfiguration
//@Configuration
//public class HibernateSearchConfiguration {
//
//    final EntityManager bentityManager;
//
//    @Autowired
//    public HibernateSearchConfiguration(EntityManager entityManager) {
//        this.bentityManager = entityManager;
//    }
//
//    @Bean
//    SearchService searchService(){
//        SearchService searchService = new SearchService(bentityManager);
//        searchService.initializeHibernateSearch();
//        return searchService;
//    }
//}
