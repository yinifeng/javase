package com.hobart.guava.cache;

import com.google.common.cache.*;

import java.util.Set;
import java.util.concurrent.TimeUnit;

public class CacheMap {
    private static Cache<String, Object> cache;
    
    private static LoadingCache<String,String> loadingCache;

    static {
        cache = CacheBuilder.newBuilder().maximumSize(1000)
                //过期时间
                //.expireAfterWrite(24, TimeUnit.HOURS)
                .expireAfterWrite(5, TimeUnit.SECONDS)
                .initialCapacity(10)
                //监听
                .removalListener(new RemovalListener<String, Object>() {
                    @Override
                    public void onRemoval(RemovalNotification<String, Object> rn) {
                        System.out.println(String.format("被移除的缓存%s:%s", rn.getKey(), rn.getValue()));

                        if (rn.wasEvicted()){
                            System.out.println("hi wasEvicted");
                        }
                        if (rn.getCause() == RemovalCause.EXPIRED){
                            System.out.println("过期");
                        }
                    }
                })
                .build();


        loadingCache = CacheBuilder.newBuilder()
                .maximumSize(1000)
                .expireAfterAccess(5, TimeUnit.SECONDS)
                .build(new CacheLoader<String, String>() {
                    @Override
                    public String load(String key) throws Exception {
                        System.out.println(key);
                        return "删除";
                    }
                });
    }

    /**
     * 获取缓存
     *
     * @param key
     * @return
     */
    public static Object get(String key) {
        return key == null ? null : cache.getIfPresent(key);
    }

    /**
     * 放入缓存
     * 
     * @param key
     * @param value
     */
    public static void put(String key,Object value){
        if (key != null){
            cache.put(key, value);
        }
    }

    /**
     * 移除缓存
     * 
     * @param key
     */
    public static void remove(String key){
        if (key != null){
            cache.invalidate(key);
        }
    }

    /**
     * 批量删除缓存
     * 
     * @param keys
     */
    public static void remove(Set<String> keys){
        if (keys != null && keys.size() > 0){
            cache.invalidateAll(keys);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        put("100", "AAA");
        put("101", "BBB");
        System.out.println(cache.asMap());
        TimeUnit.SECONDS.sleep(2);
        remove("101");
        TimeUnit.SECONDS.sleep(8);
        System.out.println(cache.asMap());

        System.out.println("=============================");
        loadingCache.put("200", "111");
        loadingCache.put("201", "222");
        System.out.println(loadingCache.asMap());
        TimeUnit.SECONDS.sleep(2);
        loadingCache.invalidate("201");
        loadingCache.invalidate("200");
        System.out.println(loadingCache.asMap());
        TimeUnit.SECONDS.sleep(8);
        System.out.println(loadingCache.asMap());
    }
}
