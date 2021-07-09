package de.schwarzrot.util

import java.lang.reflect.InvocationTargetException
import java.lang.reflect.Method

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
class PropertyAccessor(private val type: Class<*>) {

    private val getters = mutableMapOf<String, Method>()
    private val setters = mutableMapOf<String, Method>()

    val getterKeys: Set<String>
        get() = getters.keys

    val settersKeys: Set<String>
        get() = setters.keys


    fun getProperty(bean: Any, propertyName: String): Any? {
        var rv: Any? = null
        val getter = getters[propertyName]
        require(bean.javaClass.isAssignableFrom(type)) {
            ("bean is not of expected type: "
                    + type.name)
        }
        if (getter != null) {
            try {
                rv = getter.invoke(bean, *arrayOfNulls(0))
            } catch (e: IllegalAccessException) {
                e.printStackTrace()
            } catch (e: IllegalArgumentException) {
                e.printStackTrace()
            } catch (e: InvocationTargetException) {
                e.printStackTrace()
            }
        }
        return rv
    }

    fun setProperty(bean: Any, propertyName: String, value: Any?) {
        val setter = setters[propertyName]
        val type: Any = setter!!.parameterTypes[0]
        require(bean.javaClass.isAssignableFrom(this.type)) {
            ("bean is not of expected type: "
                    + this.type.name)
        }
        try {
            if (value == null) {
                setter.invoke(bean, value)
            } else {
                if (type == Int::class.java || type == Int::class.javaPrimitiveType) {
                    if (value is Number) {
                        setter.invoke(bean, value.toInt())
                    } else if (value is String) {
                        setter.invoke(bean, value.toInt())
                    }
                } else if (type == Double::class.java || type == Double::class.javaPrimitiveType) {
                    if (value is Number) {
                        setter.invoke(bean, value.toDouble())
                    } else if (value is String) {
                        setter.invoke(bean, value.toDouble())
                    }
                } else if (type == Boolean::class.java || type == Boolean::class.javaPrimitiveType) {
                    if (value is Boolean) {
                        setter.invoke(bean, value)
                    } else if (value is Number) {
                        setter.invoke(
                            bean,
                            value.toInt() != 0
                        )
                    } else if (value is String) {
                        setter.invoke(
                            bean,
                            java.lang.Boolean.parseBoolean(value as String?)
                        )
                    }
                } else if (type == String::class.java) {
                    if (value is String) {
                        setter.invoke(bean, value)
                    } else {
                        setter.invoke(bean, value.toString())
                    }
                } else {
                    setter.invoke(bean, value)
                }
            }
        } catch (e: IllegalAccessException) {
            e.printStackTrace()
        } catch (e: IllegalArgumentException) {
            e.printStackTrace()
        } catch (e: InvocationTargetException) {
            e.printStackTrace()
        }
    }

    protected fun propertyNameOf(methodName: String): String {
        val sb = StringBuilder()
        if (methodName.startsWith("get") || methodName.startsWith("set")) {
            sb.append(methodName.substring(3, 4).toLowerCase())
            sb.append(methodName.substring(4))
        } else if (methodName.startsWith("is")) {
            sb.append(methodName.substring(2, 3).toLowerCase())
            sb.append(methodName.substring(3))
        }
        return sb.toString()
    }

    init {
        type.methods.forEach { method ->
            val returnType = method.returnType
            val parameterTypes: Array<Class<*>> = method.parameterTypes

            // System.out.println("found method: " + m.getName()
            // + " - return-type: "
            // + rt
            // + " - parameter-type: "
            // + pt);
            if (method.name.startsWith("get") || method.name.startsWith("is")) {
                if ("Class".compareTo(method.name.substring(3)) == 0) return@forEach
                getters[propertyNameOf(method.name)] = method
            } else if (method.name.startsWith("set")) {
                setters[propertyNameOf(method.name)] = method
            }
        }
    }
}