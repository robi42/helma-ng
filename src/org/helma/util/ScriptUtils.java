/*
 *  Copyright 2006 Hannes Wallnoefer <hannes@helma.at>
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package org.helma.util;

import org.mozilla.javascript.*;

import java.util.List;
import java.util.Map;

/**
 * A collection of Rhino utility methods.
 */
public class ScriptUtils {

    /**
     * Coerce/wrap a java object to a JS object, and mask Lists and Maps
     * as native JS objects.
     * @param obj the object to coerce/wrap
     * @param scope the scope
     * @return the wrapped/masked java object
     */
    public static Object javaToJS(Object obj, Scriptable scope) {
        if (obj instanceof List) {
            return new ScriptableList(scope, (List) obj);
        } else if (obj instanceof Map) {
            return new ScriptableMap(scope, (Map) obj);
        } else {
            return Context.javaToJS(obj, scope);
        }
    }

    /**
     * Return a class prototype, or the object prototype if the class
     * is not defined.
     * @param scope the scope
     * @param className the class name
     * @return the class or object prototype
     */
    public static Scriptable getClassOrObjectProto(Scriptable scope, String className) {
        Scriptable proto = ScriptableObject.getClassPrototype(scope, className);
        if (proto == null) {
            proto = ScriptableObject.getObjectPrototype(scope);
        }
        return proto;
    }

    /**
     * Make sure that number of arguments is valid.
     * @param args the argument array
     * @param min the minimum number of arguments
     * @param max the maximum number of arguments
     * @throws IllegalArgumentException if the number of arguments is not valid
     */
    public static void checkArguments(Object[] args, int min, int max) {
        if (min > -1 && args.length < min)
            throw new IllegalArgumentException();
        if (max > -1 && args.length > max)
            throw new IllegalArgumentException();
    }

    /**
     * Get an argument as string
     * @param args the argument array
     * @param pos the position of the requested argument
     * @return the argument as string
     */
    public static String getStringArgument(Object[] args, int pos) {
        if (pos >= args.length || args[pos] == null || args[pos] == Undefined.instance)
            return null;
        return ScriptRuntime.toString(args[pos].toString());
    }

    /**
     * Get an argument as object
     * @param args the argument array
     * @param pos the position of the requested argument
     * @return the argument as object
     */
    public static Object getObjectArgument(Object[] args, int pos) {
        if (pos >= args.length || args[pos] == null || args[pos] == Undefined.instance)
            return null;
        return Context.jsToJava(args[pos], Object.class);
    }

}
