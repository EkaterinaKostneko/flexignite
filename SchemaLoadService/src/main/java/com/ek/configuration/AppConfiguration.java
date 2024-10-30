package com.ek.configuration;

import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCache;
import org.apache.ignite.Ignition;
import org.apache.ignite.configuration.CacheConfiguration;
import org.apache.ignite.configuration.ClientConfiguration;
import org.apache.ignite.configuration.IgniteConfiguration;
import org.apache.ignite.spi.discovery.tcp.TcpDiscoverySpi;
import org.apache.ignite.spi.discovery.tcp.ipfinder.multicast.TcpDiscoveryMulticastIpFinder;
//import org.apache.ignite.spi.tracing.opencensus.OpenCensusTracingSpi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;

@Configuration
public class AppConfiguration {
//    @Bean
//    public IgniteCache<?,?> igniteCache () {
//        // Starting Ignite
//        IgniteConfiguration cfg = new IgniteConfiguration();
//
//        cfg.setClientMode(true);
//        cfg.setPeerClassLoadingEnabled(true);
//        cfg.setTracingSpi(new OpenCensusTracingSpi());
//
//        // Setting up an IP Finder to ensure the client can locate the servers.
//        TcpDiscoveryMulticastIpFinder ipFinder = new TcpDiscoveryMulticastIpFinder();
//        ipFinder.setAddresses(Collections.singletonList("109.172.89.16:47500"));
//        cfg.setDiscoverySpi(new TcpDiscoverySpi().setIpFinder(ipFinder));
//
//        Ignite ignite = Ignition.start(cfg);
//
//        return ignite.getOrCreateCache(new CacheConfiguration("dynamicCache"));
//    }

    @Bean
    public ClientConfiguration clientConfiguration() {

        return new ClientConfiguration()
            .setAddresses("109.172.89.16:10800");
    }
}
