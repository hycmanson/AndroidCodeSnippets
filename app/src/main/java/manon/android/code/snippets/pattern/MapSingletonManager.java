package manon.android.code.snippets.pattern;

import java.util.HashMap;
import java.util.Map;

public class MapSingletonManager {
    private static Map<String, Object> mObjectMap = new HashMap<>();

    private MapSingletonManager() {
    }

    public static void putSingleton(String key, Object instance) {
        if (!mObjectMap.containsKey(key)) {
            mObjectMap.put(key, instance);
        }
    }

    public static Object getmObjectMap(String key) {
        return mObjectMap.get(key);
    }
}
