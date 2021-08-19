package com.catlogelastic.repository;

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

import com.catlogelastic.entity.Product;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;


@Component
@Slf4j
public class ProductRepositoryImpl  implements ProductRepository{

	@Autowired
	private ObjectMapper objectMapper;
	
	//client to interact with Elastic search
	RestHighLevelClient client = new RestHighLevelClient(
			RestClient.builder(new HttpHost("localhost", 9200, "http")));

	@Override
	public List<Product> findAll() {
		SearchRequest searchRequest = new SearchRequest();
		searchRequest.indices("product");
		
		SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
		searchSourceBuilder.query(QueryBuilders.matchAllQuery());
		searchRequest.source(searchSourceBuilder);
		
		List<Product> productList = new ArrayList<>();
		SearchResponse searchResponse = null;
		try {
			searchResponse =client.search(searchRequest, RequestOptions.DEFAULT);
			if (searchResponse.getHits().getTotalHits().value > 0) {
				SearchHit[] searchHit = searchResponse.getHits().getHits();
				for (SearchHit hit : searchHit) {
					Map<String, Object> map = hit.getSourceAsMap();
					productList.add(objectMapper.convertValue(map, Product.class));
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		log.info("inside findAll of ProductRepositoryImpl");
		return productList;
	}
	

	@Override
	public List<Product> findById(int productId) {
		SearchRequest searchRequest = new SearchRequest();
		searchRequest.indices("product");
		SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
		searchSourceBuilder.query(QueryBuilders.boolQuery().must(QueryBuilders.termQuery("productId.keyword", productId)));
		searchRequest.source(searchSourceBuilder);
		List<Product> productList = new ArrayList<>();
		
		try {
			SearchResponse searchResponse = null;
			searchResponse =client.search(searchRequest, RequestOptions.DEFAULT);
			if (searchResponse.getHits().getTotalHits().value > 0) {
				SearchHit[] searchHit = searchResponse.getHits().getHits();
				for (SearchHit hit : searchHit) {
					Map<String, Object> map = hit.getSourceAsMap();
					productList.add(objectMapper.convertValue(map, Product.class));
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return productList;
	}

	@Override
	public Product save(Product product) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteById(int id) {
		// TODO Auto-generated method stub
		
	}


}
