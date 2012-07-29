package com.higok.util;

import java.lang.reflect.Type;
import java.util.Map;

import com.google.gson.reflect.TypeToken;

/**
 * @author xueqiang.mi
 * @since 2012-7-29
 */
public class TypeUtils {

  public static final Type MAP_STRING_STRING = new TypeToken<Map<String, String>>() {
  }.getType();

}
