package com.rzandjavagit.core.logwriter;

public class LogWriter {
    private static Class thisClass = LogWriter.class;
    private static String classFullName = thisClass.getName();
    private static String className = thisClass.getSimpleName();
    private static Class stackTraceClass = null;
    private static StackTraceElement stackTraceElement = null;

    public LogWriter() {
        debugPrint(getCallerClassName());
    }

    public static void log(String argMessage, boolean argIsDebug) {
        if (argIsDebug) {
            log(argMessage);
        }
    }

    public static void log(String argMessage) {
        runStackTraceElement();
        String buildMessage = "";
        String packageName = getCallerPackageName();
        packageName = "".equals(packageName) ? "(default package)." : "";
        buildMessage = "Message: " + argMessage + " "
                //+ "Package Name: " + getCallerPackageName() + " - "
                + "Class Name: " + packageName + getCallerClassName() + " - "
                + "Method Name: " + getCallerMethodName() + " - "
                + "Line Number: " + getCallerLineNumber();
        debugPrint(buildMessage);
        //System.out.println("Package name: " + ("".equals(packageName) ? "[default package]" : packageName));
    }

    public static String getCallerPackageName() {
        String className = stackTraceElement.getClassName();
        //String packageName = extractPackageName(argClassName);
        //System.out.println("LN: " + stackTraceElements.length);
        if ((null == className) || ("".equals(className))) {
            return "";
        }
        int lastDot = className.lastIndexOf('.');
        if (0 >= lastDot) {
            return "";
        }
        return className.substring(0, lastDot);
    }

    public static String getCallerClassName() {
        return stackTraceElement.getClassName();
    }

    public static String getCallerMethodName() {
        return stackTraceElement.getMethodName();
    }

    public static String getCallerLineNumber() {
        return "" + stackTraceElement.getLineNumber();
    }

    private static void runStackTraceElement() {
        //StackTraceElement[] stackTraceElements = new Throwable().getStackTrace();
        StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
        int stackElementsLength = stackTraceElements.length;
        int index = 0;
        boolean isFoundGlobal = false;
        boolean isFound = false;
        for (int i = 0; i < stackElementsLength; i++) {
            isFound = false;
            StackTraceElement element = stackTraceElements[i];
            try {
                Class<?> clazz = Class.forName(element.getClassName());
                if (clazz.getName().equalsIgnoreCase(classFullName) || clazz.getSimpleName().equalsIgnoreCase(className)) {
                    index = i;
                    isFoundGlobal = true;
                    isFound = true;
                }
                if (isFoundGlobal && !isFound) {
                    break;
                }
            } catch (Exception e) {
            }
        }
        index++;
        if (stackElementsLength > index) {
            stackTraceElement = stackTraceElements[index];
            try {
                stackTraceClass = Class.forName(stackTraceElement.getClassName());
            } catch (Exception e) {
            }
        }
    }

    private static void debugPrint(String argMessage) {
        System.out.println("DEBUG_LOG_WRITER_PRINT: " + argMessage);
    }
}

//https://stackoverflow.com/questions/19352549/java-loop-through-stack-trace-and-get-class-and-method-instance
//https://stackoverflow.com/questions/19352549/java-loop-through-stack-trace-and-get-class-and-method-instance