package milos.davitkovic.javautil.utills.services.impl.utils.cache;

/**
 * Generic cache interface providing LRU (Least Recently Used) caching functionality.
 * 
 * <p>This interface defines the contract for a cache implementation that provides
 * efficient storage and retrieval of key-value pairs with automatic eviction of
 * least recently used entries when the cache reaches its capacity limit.</p>
 * 
 * <p>Key features of this cache interface:</p>
 * <ul>
 *   <li>Fixed capacity that must be specified during creation</li>
 *   <li>LRU eviction policy for managing cache size</li>
 *   <li>Thread-safe operations for concurrent access</li>
 *   <li>Generic key-value pair storage</li>
 * </ul>
 * 
 * @param <K> the type of keys maintained by this cache
 * @param <V> the type of mapped values
 * @author Milos Davitkovic
 * @version 1.0
 * @since 1.0
 */
public interface Cache<K, V> {

    /**
     * Removes all entries from the cache.
     * 
     * <p>This method clears all key-value pairs from the cache, effectively
     * resetting it to an empty state. The cache capacity remains unchanged.</p>
     */
    void clear();

    /**
     * Retrieves the value associated with the specified key.
     * 
     * <p>This method returns the value mapped to the given key, or null if no
     * mapping exists. Accessing a key counts as a "use" operation for LRU
     * eviction purposes.</p>
     * 
     * @param key the key whose associated value is to be returned
     * @return the value to which the specified key is mapped, or null if no mapping exists
     */
    V get(K key);

    /**
     * Associates the specified value with the specified key in the cache.
     * 
     * <p>If the cache previously contained a mapping for the key, the old value
     * is replaced by the specified value. This operation counts as a "use"
     * for LRU eviction purposes.</p>
     * 
     * @param key the key with which the specified value is to be associated
     * @param value the value to be associated with the specified key
     */
    void put(K key, V value);

    /**
     * Removes the mapping for the specified key from the cache.
     * 
     * <p>This method removes the key-value pair associated with the specified key
     * if it exists in the cache.</p>
     * 
     * @param key the key whose mapping is to be removed from the cache
     * @return true if the cache contained a mapping for the specified key, false otherwise
     */
    boolean remove(Object key);

    /**
     * Returns the current number of entries in the cache.
     * 
     * @return the number of key-value mappings currently in the cache
     */
    int size();

    /**
     * Returns the maximum capacity of this cache.
     * 
     * <p>The capacity is fixed and cannot be changed after cache creation.
     * When the cache reaches this capacity, the LRU eviction policy will
     * automatically remove the least recently used entries.</p>
     * 
     * @return the maximum number of entries this cache can hold
     */
    int capacity();
}
