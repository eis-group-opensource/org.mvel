package org.mvel2.util;

import java.util.Map;

public final class ClassUtils
{
  private static final Map<ClassLoader, Map<String, Object>> classResolverCache = new ConcurrentReferenceHashMap(1, 1.0F);
  private static final Object NULL_VALUE = new Object();
  
  public static Class<?> forName(String className, ClassLoader classLoader)
    throws ClassNotFoundException
  {
    Map<String, Object> resolvedClassCache = getResolvedClassCache(classLoader);
    Object result = resolvedClassCache.get(className);

    if (result == NULL_VALUE) {
      return forNameWithInner(className, classLoader);
    }

    if (result == null)
    {
      try
      {
        result = Class.forName(className, true, classLoader);
      }
      catch (ClassNotFoundException e)
      {
        resolvedClassCache.put(className, NULL_VALUE);
        if (classLoader != Thread.currentThread().getContextClassLoader())
        {
          result = Class.forName(className, true, Thread.currentThread().getContextClassLoader());
        }
        else
        {
          resolvedClassCache.put(className, NULL_VALUE);
          throw e;
        }
      }
      resolvedClassCache.put(className, result);
    }
    else if (result == NULL_VALUE)
    {
      throw new ClassNotFoundException("No class found for name " + className);
    }
    return (Class)result;
  }
  
  public static Class forNameWithInner(String className, ClassLoader classLoader)
    throws ClassNotFoundException
  {
    Map<String, Object> resolvedClassCache = getResolvedClassCache(classLoader);
    Object result = resolvedClassCache.get(className);
    if (result == null)
    {
      try
      {
        result = Class.forName(className, true, classLoader);
        resolvedClassCache.put(className, result);
      }
      catch (ClassNotFoundException e)
      {
        resolvedClassCache.put(className, NULL_VALUE);
        result = resolveInner(className, classLoader, resolvedClassCache);
        if (result == null) {
          throw e;
        }
      }
    }
    else if (result == NULL_VALUE)
    {
      result = resolveInner(className, classLoader, resolvedClassCache);
      if (result == null) {
        throw new ClassNotFoundException("No class found for name " + className);
      }
    }
    return (Class)result;
  }
  
  private static Class<?> resolveInner(String className, ClassLoader classLoader, Map<String, Object> resolvedClassCache)
  {
    for (int lastDotPos = className.lastIndexOf('.'); lastDotPos > 0; lastDotPos = className.lastIndexOf('.'))
    {
      className = className.substring(0, lastDotPos) + "$" + className.substring(lastDotPos + 1);
      Object result = resolvedClassCache.get(className);
      if (result == null) {
        try
        {
          result = Class.forName(className, true, classLoader);
          resolvedClassCache.put(className, result);
          return (Class)result;
        }
        catch (ClassNotFoundException e)
        {
          resolvedClassCache.put(className, NULL_VALUE);
        }
      } else if (result != NULL_VALUE) {
        return (Class)result;
      }
    }
    return null;
  }
  
  private static Map<String, Object> getResolvedClassCache(ClassLoader classLoader)
  {
    Map<String, Object> resolvedClassCache = (Map)classResolverCache.get(classLoader);
    if (resolvedClassCache == null)
    {
      resolvedClassCache = new ConcurrentReferenceHashMap(10);
      classResolverCache.put(classLoader, resolvedClassCache);
    }
    return resolvedClassCache;
  }
}