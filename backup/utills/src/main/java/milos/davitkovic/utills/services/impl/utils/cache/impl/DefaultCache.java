package milos.davitkovic.utills.services.impl.cache.impl;

import milos.davitkovic.utills.services.impl.cache.Cache;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Hashtable;
import java.util.Map;
import java.util.Set;

@Service
public class DefaultCache<K, V> implements Cache<K, V> {

    private class MyObject {
        private V value;
        private Date lastUse;

        public Date getLastUse() {
            return lastUse;
        }

        public void setLastUse(Date lastUse) {
            this.lastUse = lastUse;
        }

        public V getValue() {
            return value;
        }

        public void setValue(V value) {
            this.value = value;
        }
    }

    private Hashtable<K, MyObject> map = new Hashtable<K, MyObject>();
    private int capacity = 1024;
    private int size = 0;

    @Override
    public void clear() {
        map.clear();
    }

    @Override
    public V get(K key) {
        final MyObject myObject = map.get(key);
        return myObject.getValue();
    }

    @Override
    public void put(K key, V value) {
        if (size == capacity) {
            remove(getLRU());
        }
        final MyObject myObject = new MyObject();
        myObject.setValue(value);
        myObject.setLastUse(new Date());
        map.put(key, myObject);
        size++;
    }

    @Override
    public boolean remove(Object key) {
        map.remove(key);
        return true;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public int capacity() {
        return capacity;
    }

    private Object getLRU() {
        long currentDate = System.currentTimeMillis();
        long lruDate =  0;
        Object lru = new Object();

        final Set<Map.Entry<K, MyObject>> entries = map.entrySet();
        for(Map.Entry<K, MyObject> map : entries) {
            final Date lastUse = map.getValue().lastUse;
            final long timeBetween = currentDate - lastUse.getTime();
            if(timeBetween > lruDate) {
                lruDate = timeBetween;
                lru = map.getKey();
            }
        }

        return lru;
    }
}
