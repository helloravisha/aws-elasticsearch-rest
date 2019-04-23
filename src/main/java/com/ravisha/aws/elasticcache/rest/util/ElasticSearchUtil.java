package com.ravisha.aws.elasticcache.rest.util;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ravisha.aws.elasticcache.rest.constants.ElasticSearchConstants;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.Operator;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;

/**
 *
 * Elastic search utility for interacting with elastic search server making use of elastic
 * search core libraries.
 *
 * @author ravi katta.
 */
public class ElasticSearchUtil {

    private static ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    public static SearchRequest buildSearchRequest(String index) throws Exception {

        SearchRequest searchRequest = new SearchRequest();
        searchRequest.indices(index);
        return searchRequest;
    }

    /**
     * Can help in getting the number of records.
     *
     * @param response
     *        Data retrieved from elastic search server.
     * @return
     *        returns the number of records present in the elastic search server.
     *
     * @throws Exception
     *        Any exception while fetching the required  records.
     */
    public static SearchHit[] getRecordsInElasticSerch(SearchResponse response) throws Exception {
        return response.getHits().getHits();
    }


    /**
     * Used for building the search request for fetching the records from elastic search
     * @param searchCriteria
     *        mentions the criteria on which the reocords need to be fetched , go to the seach criteria
     *        class to have the complete information.
     *
     *
     * @return
     */
    public static SearchRequest getSearchRequest(SearchCriteria searchCriteria) {

        SearchRequest searchRequest = new SearchRequest();
        searchRequest.indices(ElasticSearchConstants.INDEX);
        searchRequest.types(ElasticSearchConstants.TYPE);
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        MatchQueryBuilder matchQueryBuilder = QueryBuilders
                .matchQuery(searchCriteria.getAttributeName(), searchCriteria.getAttributeValue())
                .operator(Operator.AND);
        searchSourceBuilder.query(matchQueryBuilder);
        searchSourceBuilder.from(searchCriteria.getOffset());
        searchSourceBuilder.size(searchCriteria.getSize());
        searchRequest.source(searchSourceBuilder);
        return searchRequest;
    }
}
