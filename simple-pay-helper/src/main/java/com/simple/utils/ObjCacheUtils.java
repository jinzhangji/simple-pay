package com.simple.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.function.Supplier;

/**
 * Created by Jin.Z.J  2021/2/24
 */
public class ObjCacheUtils {

    private static final SimpleCache<String,Object> POOL = new SimpleCache<>();

    public static Object get(String key){
        return POOL.get(key);
    }

    public static Object get(String key,Supplier<Object> supplier){
        return POOL.get(key,supplier);
    }


    private static class SimpleCache<K,V>{

        private final Map<K,V> cache = new HashMap<>();

        private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();


        public V get(K key){
            lock.readLock().lock();
            try{
                return this.cache.get(key);
            }finally {
                lock.readLock().unlock();
            }
        }


        public V get(K key,Supplier<V> supplier){
            V v = this.get(key);
            if(v == null && supplier != null){
                this.lock.writeLock().lock();
                try {
                    v = this.cache.get(key);
                    if (null == v) {
                        v = supplier.get();
                        if(v != null){
                            this.cache.put(key, v);
                        }
                    }
                } finally {
                    this.lock.writeLock().unlock();
                }
            }
            return v;
        }

        public V remove(K key) {
            this.lock.writeLock().lock();
            try {
                return this.cache.remove(key);
            } finally {
                this.lock.writeLock().unlock();
            }
        }


        public void clear() {
            this.lock.writeLock().lock();

            try {
                this.cache.clear();
            } finally {
                this.lock.writeLock().unlock();
            }

        }
    }

}
