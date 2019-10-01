package manon.android.code.snippets.pattern;

public class StaticClassSingleton {
    private StaticClassSingleton() {
    }

    private static class StaticClassSingletonHolder {
        private static final StaticClassSingleton STATIC_CLASS_SINGLETON = new StaticClassSingleton();
    }

    public static StaticClassSingleton getInstance() {
        return StaticClassSingletonHolder.STATIC_CLASS_SINGLETON;
    }
}
