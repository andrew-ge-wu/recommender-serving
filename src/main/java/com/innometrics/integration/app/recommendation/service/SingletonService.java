package com.innometrics.integration.app.recommendation.service;


import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author andrew, Innometrics
 */
public class SingletonService {
    private static final String DEFAULT_CONFIG = "/default.properties";
    private static final Logger logger = Logger.getLogger(SingletonService.class.getCanonicalName());
    private static Map<String, Object> objectMap = new ConcurrentHashMap<>();

    private static Configuration configuration;

    public static Configuration getConfiguration() {
        if (configuration == null) {
            try {
                configuration = new PropertiesConfiguration(SingletonService.class.getClassLoader().getResource(DEFAULT_CONFIG));
            } catch (ConfigurationException e) {
                throw new RuntimeException(e);
            }
        }
        return configuration;
    }


    public static <T> T getObjectSingleton(Class<T> implementationClassName) {
        String qualifiedKey = implementationClassName.getCanonicalName();
        if (!objectMap.containsKey(qualifiedKey) || objectMap.get(qualifiedKey) == null) {
            logger.info("Creating object:" + implementationClassName);
            try {
                Constructor<?> constructor;
                constructor = implementationClassName.getConstructor();
                objectMap.put(qualifiedKey, constructor.newInstance());
            } catch (InvocationTargetException | InstantiationException | IllegalAccessException e) {
                logger.log(Level.WARNING, "Error while initialize Object:" + qualifiedKey, e);
                throw new RuntimeException(e);
            } catch (NoSuchMethodException ex2) {
                throw new RuntimeException(new NoSuchMethodException("Can not find default constructor."));
            }
        }
        return implementationClassName.cast(objectMap.get(qualifiedKey));
    }

    public static <T> T getInstanceFromConfig(Class<T> interfaceClass) {
        try {
            if (interfaceClass.isInterface()) {
                String implClass = getConfiguration().getString(interfaceClass.getCanonicalName());
                if (implClass != null && implClass.length() > 0) {
                    return interfaceClass.cast(getObjectSingleton(Class.forName(implClass)));
                } else {
                    throw new RuntimeException("Can not find implementation details for interface:" + interfaceClass.getCanonicalName());
                }
            } else {
                return getObjectSingleton(interfaceClass);
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
