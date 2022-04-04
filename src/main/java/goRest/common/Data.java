package goRest.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class Data {

    private final Map<Long, Map<String, Object>> data = new HashMap<>();
    private static final Logger log = LoggerFactory.getLogger(Data.class);
    private static Data instance = null;

    private static Data getInstance() {
        if (null == instance) {
            instance = new Data();
        }
        return instance;
    }

    private Data() {}

    public static Object get(String key) {
        return Data.getInstance().getData(key);
    }

    public static void put(String key, Object value) {
        Data.getInstance().putData(key, value);
    }

    public static void remove(String key) {
        Data.getInstance().removeKey(key);
    }

    public static boolean has(String key) {
        return Data.getInstance().hasKey(key);
    }

    public static void clear() {
        Data.getInstance().clearData();
    }

    public static void print() {
        Data.getInstance().printData();
    }

    private Object getData(String key) {
        Long id = Thread.currentThread().getId();
        Map<String, Object> threadData;
        synchronized (data) {
            threadData = data.get(id);
        }
        if (null == threadData) {
            log.error("Storage doesn't contain data for the current thread");
            return null;
        }
        return threadData.get(key);
    }

    private void putData(String key, Object value) {
        Long id = Thread.currentThread().getId();
        Map<String, Object> threadData;
        synchronized (data) {
            threadData = data.computeIfAbsent(id, k -> new HashMap<>());
        }
        threadData.put(key, value);
    }

    private void removeKey(String key) {
        Long id = Thread.currentThread().getId();
        synchronized (data) {
            Map<String, Object> threadData = data.get(id);
            if (null != threadData) {
                threadData.remove(key);
            } else {
                log.error("Storage doesn't contain data for the current thread");
            }
        }
    }

    private boolean hasKey(String key) {
        Long id = Thread.currentThread().getId();
        Map<String, Object> threadData;
        synchronized (data) {
            threadData = data.get(id);
        }
        if (null != threadData) {
            return threadData.containsKey(key);
        } else {
            log.error("Storage doesn't contain data for the current thread");
        }
        return false;
    }

    private void clearData() {
        Long id = Thread.currentThread().getId();
        synchronized (data) {
            Map<String, Object> threadData = data.get(id);
            if (null != threadData) {
                threadData.clear();
            } else {
                log.error("Storage doesn't contain data for the current thread");
            }
        }
    }

    private void printData() {
        Long id = Thread.currentThread().getId();
        Map<String, Object> threadData;
        synchronized (data) {
            threadData = data.get(id);
        }
        if (null != threadData) {
            threadData.forEach((key, value) -> log.info("key: " + key + " - " + "value: " + value));
        } else {
            log.error("Storage doesn't contain data for the current thread");
        }
    }

}
