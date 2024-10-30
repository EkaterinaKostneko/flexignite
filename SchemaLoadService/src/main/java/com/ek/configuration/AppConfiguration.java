package com.ek.configuration;

import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCache;
import org.apache.ignite.Ignition;
import org.apache.ignite.cache.QueryEntity;
import org.apache.ignite.cache.QueryIndex;
import org.apache.ignite.calcite.CalciteQueryEngineConfiguration;
import org.apache.ignite.configuration.CacheConfiguration;
import org.apache.ignite.configuration.ClientConnectorConfiguration;
import org.apache.ignite.configuration.IgniteConfiguration;
import org.apache.ignite.configuration.SqlConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class AppConfiguration {

    @Bean
    public Ignite ignite() {

        IgniteConfiguration igniteCfg = new IgniteConfiguration();

        ClientConnectorConfiguration ccfg = new ClientConnectorConfiguration();
        ccfg.setHost("127.0.0.1");
        ccfg.setPort(10800);
        ccfg.setPortRange(0);

// Set client connection configuration in IgniteConfiguration
        igniteCfg.setClientConnectorConfiguration(ccfg);
        igniteCfg.setSqlConfiguration(
            new SqlConfiguration().setQueryEnginesConfiguration(
                new CalciteQueryEngineConfiguration().setDefault(true)
            )
        );

// Start Ignite node
        return Ignition.start(igniteCfg);
    }

    @Bean
    public IgniteCache<Long, Object> igniteCache(Ignite ignite) {

        CacheConfiguration<Long, Object> personCacheCfg = new CacheConfiguration<Long, Object>();
        personCacheCfg.setName("dynamicTable");

        QueryEntity queryEntity = new QueryEntity(Long.class.getName(), "dynamicCache")
            .addQueryField("id", Long.class.getName(), null)
            .addQueryField("field", String.class.getName(), null);
        queryEntity.setIndexes(Arrays.asList(new QueryIndex("id")));

        return ignite.getOrCreateCache(personCacheCfg);
    }
}
