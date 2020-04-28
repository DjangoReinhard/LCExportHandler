package de.schwarzrot.util;
/* 
 * **************************************************************************
 * 
 *  file:       PropertyAccessor.java
 *  project:    GUI for linuxcnc
 *  subproject: base library for exporthandlers
 *  purpose:    helper classes for creation of exporthandlers, that
 *              will be used to export tooltables from toolmanager
 *  created:    17.11.2019 by Django Reinhard
 *  copyright:  all rights reserved
 * 
 *  This program is free software: you can redistribute it and/or modify 
 *  it under the terms of the GNU General Public License as published by 
 *  the Free Software Foundation, either version 2 of the License, or 
 *  (at your option) any later version. 
 *   
 *  This program is distributed in the hope that it will be useful, 
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of 
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the 
 *  GNU General Public License for more details. 
 *   
 *  You should have received a copy of the GNU General Public License 
 *  along with this program.  If not, see <https://www.gnu.org/licenses/>.
 * 
 * **************************************************************************
 */

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;


public class PropertyAccessor {
   public PropertyAccessor(Class type) {
      this.type = type;
      getters   = new HashMap<String, Method>();
      setters   = new HashMap<String, Method>();

      for (Method m : type.getMethods()) {
         Object   rt = m.getReturnType();
         Object[] pt = m.getParameterTypes();

         // System.out.println("found method: " + m.getName()
         // + " - return-type: "
         // + rt
         // + " - parameter-type: "
         // + pt);
         if (m.getName().startsWith("get") || m.getName().startsWith("is")) {
            if ("Class".compareTo(m.getName().substring(3)) == 0) continue;
            getters.put(propertyNameOf(m.getName()), m);
         } else if (m.getName().startsWith("set")) {
            setters.put(propertyNameOf(m.getName()), m);
         }
      }
   }


   public Object getProperty(Object bean, String propertyName) {
      Object rv     = null;
      Method getter = getters.get(propertyName);

      if (!bean.getClass().isAssignableFrom(type)) {
         throw new IllegalArgumentException("bean is not of expected type: "
                                            + type.getName());
      }
      if (getter != null) {
         try {
            rv = getter.invoke(bean, new Object[0]);
         }
         catch (IllegalAccessException | IllegalArgumentException
               | InvocationTargetException e) {
            e.printStackTrace();
         }
      }
      return rv;
   }


   public Set<String> getters() {
      return getters.keySet();
   }


   public void setProperty(Object bean, String propertyName, Object value) {
      Method setter = setters.get(propertyName);
      Object type   = setter.getParameterTypes()[0];

      if (!bean.getClass().isAssignableFrom(this.type)) {
         throw new IllegalArgumentException("bean is not of expected type: "
                                            + this.type.getName());
      }
      try {
         if (value == null) {
            setter.invoke(bean, value);
         } else {
            if (type.equals(Integer.class) || type.equals(int.class)) {
               if (value instanceof Number) {
                  setter.invoke(bean, ((Number) value).intValue());
               } else if (value instanceof String) {
                  setter.invoke(bean, Integer.parseInt((String) value));
               }
            } else if (type.equals(Double.class) || type.equals(double.class)) {
               if (value instanceof Number) {
                  setter.invoke(bean, ((Number) value).doubleValue());
               } else if (value instanceof String) {
                  setter.invoke(bean, Double.parseDouble((String) value));
               }
            } else if (type.equals(Boolean.class)
                       || type.equals(boolean.class)) {
                          if (value instanceof Boolean) {
                             setter.invoke(bean, value);
                          } else if (value instanceof Number) {
                             setter.invoke(bean,
                                           ((Number) value).intValue() != 0);
                          } else if (value instanceof String) {
                             setter.invoke(bean,
                                           Boolean.parseBoolean((String) value));
                          }
                       } else if (type.equals(String.class)) {
                          if (value instanceof String) {
                             setter.invoke(bean, value);
                          } else {
                             setter.invoke(bean, value.toString());
                          }
                       } else {
                          setter.invoke(bean, value);
                       }
         }
      }
      catch (IllegalAccessException | IllegalArgumentException
            | InvocationTargetException e) {
         e.printStackTrace();
      }
   }


   public Set<String> setters() {
      return setters.keySet();
   }


   protected String propertyNameOf(String methodName) {
      StringBuilder sb = new StringBuilder();

      if (methodName.startsWith("get") || methodName.startsWith("set")) {
         sb.append(methodName.substring(3, 4).toLowerCase());
         sb.append(methodName.substring(4));
      } else if (methodName.startsWith("is")) {
         sb.append(methodName.substring(2, 3).toLowerCase());
         sb.append(methodName.substring(3));
      }
      return sb.toString();
   }

   @SuppressWarnings("rawtypes")
   private Class               type;
   private Map<String, Method> getters;
   private Map<String, Method> setters;
}
