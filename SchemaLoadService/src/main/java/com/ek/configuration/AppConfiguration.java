package com.ek.configuration;

import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCache;
import org.apache.ignite.Ignition;
import org.apache.ignite.configuration.CacheConfiguration;
import org.apache.ignite.configuration.IgniteConfiguration;
import org.apache.ignite.spi.tracing.opencensus.OpenCensusTracingSpi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfiguration {
    @Bean
    public IgniteCache<?,?> igniteCache () {
        // Starting Ignite
        IgniteConfiguration cfg = new IgniteConfiguration();

        cfg.setClientMode(true);
        cfg.setPeerClassLoadingEnabled(true);
        cfg.setTracingSpi(new OpenCensusTracingSpi());

        Ignite ignite = Ignition.start(cfg);

        return ignite.getOrCreateCache(new CacheConfiguration("dynamicCache"));
    }
}
