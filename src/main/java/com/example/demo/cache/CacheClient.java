package com.example.demo.cache;

import com.example.demo.order.Order;
import com.hazelcast.config.Config;
import com.hazelcast.config.MapConfig;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.map.IMap;
import org.springframework.stereotype.Component;

@Component
public class CacheClient {

    public static final String ORDERS = "orders";
    private final HazelcastInstance hazelcastInstance = Hazelcast.newHazelcastInstance(createConfig());

    public Config createConfig() {
        Config config = new Config();
        config.addMapConfig(mapConfig());
        return config;
    }

    private MapConfig mapConfig() {
        MapConfig mapConfig = new MapConfig(ORDERS);
        mapConfig.setTimeToLiveSeconds(360);
        mapConfig.setMaxIdleSeconds(20);
        return mapConfig;
    }

    public Order put(Integer id, Order order) {
        IMap<Integer, Order> map = hazelcastInstance.getMap(ORDERS);
        return map.putIfAbsent(id, order);
    }

    public Order get(String key) {
        IMap<String, Order> map = hazelcastInstance.getMap(ORDERS);
        return map.get(key);
    }
}
