package com.elastic.repository;



import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpHost;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.elastic.entity.User;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class UserRepositoryImpl implements UserRepository {
	
	@Autowired
	private ObjectMapper objectMapper;
	
	//client to interact with Elastic search
	RestHighLevelClient client = new RestHighLevelClient(
			RestClient.builder(new HttpHost("localhost", 9200, "http")));

	@Override
	public List<User> findAllUser() {
		
		SearchRequest searchRequest = new SearchRequest();
		searchRequest.indices("userdata");
		
		SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
		searchSourceBuilder.query(QueryBuilders.matchAllQuery());
		searchRequest.source(searchSourceBuilder);
		
		List<User> userList = new ArrayList<>();
		SearchResponse searchResponse = null;
		try {
			searchResponse =client.search(searchRequest, RequestOptions.DEFAULT);
			if (searchResponse.getHits().getTotalHits().value > 0) {
				SearchHit[] searchHit = searchResponse.getHits().getHits();
				for (SearchHit hit : searchHit) {
					Map<String, Object> map = hit.getSourceAsMap();
					userList.add(objectMapper.convertValue(map, User.class));
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		log.info("inside findAllUser of UserRepositoryImpl");
		return userList;
	}

	//exact match
	@Override
	public List<User> findAllUserByName(String userName) {
		SearchRequest searchRequest = new SearchRequest();
		searchRequest.indices("userdata");
		SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
		searchSourceBuilder.query(QueryBuilders.boolQuery().must(QueryBuilders.termQuery("userName.keyword", userName)));
		searchRequest.source(searchSourceBuilder);
		List<User> userList = new ArrayList<>();
		
		try {
			SearchResponse searchResponse = null;
			searchResponse =client.search(searchRequest, RequestOptions.DEFAULT);
			if (searchResponse.getHits().getTotalHits().value > 0) {
				SearchHit[] searchHit = searchResponse.getHits().getHits();
				for (SearchHit hit : searchHit) {
					Map<String, Object> map = hit.getSourceAsMap();
					userList.add(objectMapper.convertValue(map, User.class));
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return userList;
	}

	@Override
	public List<User> findAllUserByNameAddress(String userName, String address) {
		log.info("inside findAllUserByNameAddress of UserRepositoryImpl");
		SearchRequest searchRequest = new SearchRequest();
		searchRequest.indices("userdata");
		SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
		searchSourceBuilder.query(QueryBuilders.boolQuery().must(QueryBuilders.termQuery("userName.keyword", userName))
				                                           .must(QueryBuilders.termQuery("address.keyword", address)));
		searchRequest.source(searchSourceBuilder);
		List<User> userList = new ArrayList<>();
		
		try {
			SearchResponse searchResponse = null;
			searchResponse =client.search(searchRequest, RequestOptions.DEFAULT);
			if (searchResponse.getHits().getTotalHits().value > 0) {
				SearchHit[] searchHit = searchResponse.getHits().getHits();
				for (SearchHit hit : searchHit) {
					Map<String, Object> map = hit.getSourceAsMap();
					userList.add(objectMapper.convertValue(map, User.class));
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return userList;
	}

}