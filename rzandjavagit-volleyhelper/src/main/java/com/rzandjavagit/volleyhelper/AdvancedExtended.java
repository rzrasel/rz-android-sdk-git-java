package com.rzandjavagit.volleyhelper;

import com.google.gson.annotations.SerializedName;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class AdvancedExtended {
    public static List<Field> getAllFields(Class argClass) {
        List<Field> fieldList = new ArrayList<>();
        Field fields[] = argClass.getDeclaredFields(); // returns all members including private members but not inherited members.
        //Field[] fields = HTTPVolleyParser.class.getFields(); // returns inherited members but not private members.
        for (int i = 0; i < fields.length; i++) {
            //System.out.println("Variable Name is : " + fields[i].getName());
            fieldList.add(fields[i]);
        }
        return fieldList;
    }

    public static List<String> getAllFieldName(Class argClass) {
        List<String> fieldList = new ArrayList<>();
        Field fields[] = argClass.getDeclaredFields(); // returns all members including private members but not inherited members.
        //Field[] fields = HTTPVolleyParser.class.getFields(); // returns inherited members but not private members.
        for (Field item : fields) {
            //System.out.println("Variable Name is : " + fields[i].getName());
            fieldList.add(item.getName());
        }
        return fieldList;
    }

    public static List<SerializedName> getAllSerializedName(Class argClass) {
        List<Field> fieldList = getAllFields(argClass);
        List<SerializedName> serializedNameList = new ArrayList<>();

        for (Field field : fieldList) {
            //System.out.println("Variable Name is : " + field.getName());
            SerializedName annotation = field.getAnnotation(SerializedName.class);
            if (annotation != null) {
                //System.out.println("SerializedName: getName " + fieldList.get(i).getName());
                //System.out.println("SerializedName: annotation " + annotation.value());
                serializedNameList.add(annotation);
            } else {
                //System.out.println("Annotation is null");
            }
        }
        return serializedNameList;
    }

    public static List<String> getAllAnnotation(Class argClass) {
        List<Field> fieldList = getAllFields(argClass);
        List<String> serializedNameList = new ArrayList<>();

        for (Field field : fieldList) {
            //System.out.println("Variable Name is : " + field.getName());
            SerializedName annotation = field.getAnnotation(SerializedName.class);
            if (annotation != null) {
                //System.out.println("SerializedName: getName " + fieldList.get(i).getName());
                //System.out.println("SerializedName: annotation " + annotation.value());
                serializedNameList.add(annotation.value());
            } else {
                //System.out.println("Annotation is null");
            }
        }
        return serializedNameList;
    }

    public static List<Method> getAllMethods(Class argClass) {
        List<Method> methodList = new ArrayList<>();
        Method[] methods = argClass.getMethods();
        for (Method method : methods) {
            methodList.add(method);
        }
        return methodList;
        /*for (Method method : methods) {
            String name = method.getName();
            System.out.println("Method Name is : " + name);
            if (name.startsWith("get")) {
                System.out.println(name.substring(3));
            } else if (name.startsWith("is")) {
                System.out.println(name.substring(2));
            }
        }*/
    }

    private void onExtended(Class argClass) {
        //https://stackoverflow.com/questions/13298823/get-variable-by-name-from-a-string
        //https://coderanch.com/t/510554/java/variable-string-variable
        //Class aClass = ModelHTTPVolley.class;
        /*FooClass fooClass = new FooClass();
        Method mFoo1 = fooClass.getClass().getMethod("getFoo" + increment + "()");
        mFoo1.invoke(fooClass);*/
        /*try {
            Object object = argClass.newInstance();
            Method method = argClass.getMethod("sayHi()");
            //object = argClass.cast(object);
            method.invoke(argClass.cast(object));
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }*/
        try {
            String methodName = "";
            Object object = argClass.newInstance();
            /*Method method = argClass.getMethod("sayHi()");
            //object = argClass.cast(object);
            method.invoke(argClass.cast(object));*/
            System.out.println(object.getClass());
            methodName = "sayHi";
            Method setNameMethod = object.getClass().getMethod(methodName);
            setNameMethod.invoke(object);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
            //https://stackoverflow.com/questions/160970/how-do-i-invoke-a-java-method-when-given-the-method-name-as-a-string
        }
        try {
            /*ModelHTTPVolley modelHTTPVolley = new ModelHTTPVolley();
            Field field = aClass.getField("testVar");
            String str1 = "";
            System.out.println(field.get(modelHTTPVolley));*/
            //Class<?> theClass = Class.forName(theType);
            //Object obj = theClass.cast(something);
            Object object = argClass.newInstance();
            //MethodDynamic methodDynamic = new MethodDynamic();
            Field field = argClass.getField("testVar");
            //field.set(methodDynamic, "new value");
            System.out.println(field.get(object));
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }
}
