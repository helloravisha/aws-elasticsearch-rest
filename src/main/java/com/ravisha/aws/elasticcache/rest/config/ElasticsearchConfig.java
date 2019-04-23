package com.ravisha.aws.elasticcache.rest.config;

import org.apache.http.HttpHost;


import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.nio.client.HttpAsyncClientBuilder;
import org.elasticsearch.client.RestClient;

import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Responsible for holding the complete elastic search server configuration, will try to fetch the details
 * from application.properties for all the properties here. This bean is responsible for providing  the
 * Rest high level client.
 *
 * What is Rest high Level client ?
 * This is a java high level rest client library that is build on top of elastic search core libraries.
 *
 * @author ravi Katta.
 *
 *
 */
@Configuration
public class ElasticsearchConfig {


    @Value("${elasticsearch.host}")
    private String host;

    @Value("${elasticsearch.port}")
    private int port;

    @Value("${elasticsearch.username}")
    private String userName;

    @Value("${elasticsearch.password}")
    private String password;

    /**
     * Responsible for retuning the high level rest client reference.
     * @return
     */
    @Bean(destroyMethod = "close")
    public RestHighLevelClient restClient() {

        final CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
        credentialsProvider.setCredentials(AuthScope.ANY,
                new UsernamePasswordCredentials(userName, password));
        RestClientBuilder builder = RestClient.builder(new HttpHost(host, port));
        RestHighLevelClient client = new RestHighLevelClient(builder);
        return client;

    }


}
