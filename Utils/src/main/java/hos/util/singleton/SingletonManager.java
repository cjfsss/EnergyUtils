package hos.util.singleton;





import java.util.HashMap;
import java.util.Map;

import hos.util.func.Function1;


/**
 * 单例服用
 */
public class SingletonManager implements ISingletonManager {

    private Map<Class<?>, ISingletonWrapper> mMap;

    private SingletonManager() {
    }


    public static SingletonManager get() {
        return SingletonManagerHolder.getSingletonManager();
    }

    private static final class SingletonManagerHolder {

        private volatile static SingletonManager singletonManager = null;

        static SingletonManager getSingletonManager() {
            if (singletonManager == null) {
                synchronized (SingletonManagerHolder.class) {
                    if (singletonManager == null) {
                        return singletonManager = new SingletonManager();
                    }
                }
            }
            return singletonManager;
        }
    }


    private <P extends ISingletonWrapper> P getInstance( Class<P> key) {
        ISingletonWrapper singletonWrapper = get().getSingletonWrappers().get(key);
        if (singletonWrapper == null) {
            return null;
        }
        return key.cast(singletonWrapper);
    }

    /**
     * 单例，弱引用
     */
    @Override

    public <P extends ISingletonWrapper> P getInstance( final Class<P> key,
                                                        final Function1<Class<P>, P> function) {
        P singleton = getInstance(key);
        if (singleton != null) {
            return singleton;
        }
        singleton = function.invoke(key);
        register(key, singleton);
        return singleton;
    }



    private Map<Class<?>, ISingletonWrapper> getSingletonWrappers() {
        if (mMap != null) {
            return mMap;
        }
        return newSingletonMap();
    }

    protected Map<Class<?>, ISingletonWrapper> newSingletonMap() {
        return mMap = new HashMap<>();
    }

    private <P extends ISingletonWrapper> void register( Class<P> key,
                                                         ISingletonWrapper ifWrapper) {
        get().getSingletonWrappers().put(key, ifWrapper);
    }


}
