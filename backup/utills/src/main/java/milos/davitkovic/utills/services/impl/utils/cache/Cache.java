package milos.davitkovic.utills.services.impl.cache;

/**
 * Implementation of this interface provide a LRU (least recently used) cache.
 *
 * <ul>
 * <li>The fixed capacity of the cache needs to be specified (see {@link CacheFactory}</li>
 * </ul>
 */

public interface Cache<K, V> {

    /**
     * Delete all cache entries
     */
    void clear();

    /**
     * Returns the value to which the specified key is mapped, or <code>null</code> if this map contains no mapping for
     * the key. Counts as used.
     */
    V get(K key);

    /**
     * Puts an object into the cache. Overwrites existing entries.
     */
    void put(K key, V value);

    /**
     * Remove an entry from the cache.
     *
     * @return <code>true</code> if the cache contained the specified element
     */
    boolean remove(Object key);

    /**
     * Returns current number of cache entries
     */
    int size();

    /**
     * Returns capacity of this cache, which is fixed.
     */
    int capacity();
}
