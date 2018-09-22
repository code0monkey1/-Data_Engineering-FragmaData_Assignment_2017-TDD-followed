package wrappers;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

public interface IMap<K,V> extends Map<K,V> {

    @Override
    V put(K key, V value);

    @Override
    default V getOrDefault(Object key, V defaultValue) {
        return null;
    }

    @Override
    boolean containsKey(Object key);

    @Override
    boolean containsValue(Object value);

    @Override
    Set<K> keySet();

    @Override
    Set<Entry<K, V>> entrySet();

    @Override
    Collection<V> values();
}
