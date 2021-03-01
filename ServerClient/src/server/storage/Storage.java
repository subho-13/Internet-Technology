package server.storage;

import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Storage {
    ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    Map<String, Map<String, String>> cache = new TreeMap<>();

    public void store(String user, String key, String value) {
        if (!cache.containsKey(user)) {
            cache.put(user, new TreeMap<>());
        }


        try {
            lock.writeLock().lock();
            cache.get(user).put(key, value);
        } finally {
            lock.writeLock().unlock();
        }
    }

    public String access(String user, String key) {
        if (!cache.containsKey(user)) {
            return null;
        }

        String value;

        try {
            lock.readLock().lock();
            value = cache.get(user).get(key);
        } finally {
            lock.readLock().unlock();
        }

        return value;
    }
}
