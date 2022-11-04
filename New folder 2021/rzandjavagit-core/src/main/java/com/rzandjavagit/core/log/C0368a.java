package com.rzandjavagit.core.log;

import android.util.Log;

import java.io.PrintStream;

class C0368a {
    protected static boolean f1106a = true;
    private static final String f1107b = C0368a.class.getName();

    C0368a() {
    }

    protected static void m1401a(String str) {
        if (f1106a) {
            PrintStream printStream = System.out;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(C0368a.m1417k(""));
            stringBuilder.append(C0368a.m1416j(str));
            printStream.println(stringBuilder.toString());
        }
    }

    protected static void m1402a(String str, String str2) {
        if (f1106a) {
            PrintStream printStream = System.out;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(C0368a.m1417k(str));
            stringBuilder.append(C0368a.m1416j(str2));
            printStream.println(stringBuilder.toString());
        }
    }

    protected static void m1403b(String str) {
        if (f1106a) {
            Log.d(C0368a.m1417k(""), C0368a.m1416j(str));
        }
    }

    protected static void m1404b(String str, String str2) {
        if (f1106a) {
            Log.d(C0368a.m1417k(str), C0368a.m1416j(str2));
        }
    }

    protected static void m1405c(String str) {
        if (f1106a) {
            Log.e(C0368a.m1417k(""), C0368a.m1416j(str));
        }
    }

    protected static void m1406c(String str, String str2) {
        if (f1106a) {
            Log.e(C0368a.m1417k(str), C0368a.m1416j(str2));
        }
    }

    protected static void m1407d(String str) {
        if (f1106a) {
            Log.i(C0368a.m1417k(""), C0368a.m1416j(str));
        }
    }

    protected static void m1408d(String str, String str2) {
        if (f1106a) {
            Log.i(C0368a.m1417k(str), C0368a.m1416j(str2));
        }
    }

    protected static void m1409e(String str) {
        if (f1106a) {
            Log.v(C0368a.m1417k(""), C0368a.m1416j(str));
        }
    }

    protected static void m1410e(String str, String str2) {
        if (f1106a) {
            Log.v(C0368a.m1417k(str), C0368a.m1416j(str2));
        }
    }

    protected static void m1411f(String str) {
        if (f1106a) {
            Log.wtf(C0368a.m1417k(""), C0368a.m1416j(str));
        }
    }

    protected static void m1412f(String str, String str2) {
        if (f1106a) {
            Log.wtf(C0368a.m1417k(str), C0368a.m1416j(str2));
        }
    }

    private static String m1416j(String str) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(str);
        stringBuilder.append(" # ");
        stringBuilder.append(C0368a.m1400a());
        return stringBuilder.toString();
    }

    private static String m1417k(String str) {
        String str2 = "DEBUG_LOG_PRINT_WRITER";
        if (!C0368a.m1418l(str)) {
            str = str.toUpperCase();
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(str2);
            stringBuilder.append("_");
            stringBuilder.append(str.replaceAll("\\s+", "_"));
            str2 = stringBuilder.toString();
        }
        StringBuilder stringBuilder2 = new StringBuilder();
        stringBuilder2.append(str2);
        stringBuilder2.append(": ");
        return stringBuilder2.toString();
    }

    private static String m1400a() {
        StringBuilder stringBuilder = new StringBuilder();
        StackTraceElement[] stackTrace = new Throwable().fillInStackTrace().getStackTrace();
        StringBuilder stringBuilder2 = new StringBuilder();
        stringBuilder2.append("<unknown>");
        stringBuilder2.append(" ");
        stringBuilder.append(stringBuilder2.toString());
        StringBuilder stringBuilder3 = new StringBuilder();
        stringBuilder3.append("Method: ");
        stringBuilder3.append("<unknown>");
        stringBuilder3.append(" ");
        stringBuilder.append(stringBuilder3.toString());
        stringBuilder3 = new StringBuilder();
        stringBuilder3.append("Line: ");
        stringBuilder3.append("<unknown>");
        stringBuilder3.append(" ");
        stringBuilder.append(stringBuilder3.toString());
        stringBuilder3 = new StringBuilder();
        stringBuilder3.append(" File: ");
        stringBuilder3.append("<unknown>");
        stringBuilder.append(stringBuilder3.toString());
        for (int i = 2; i < stackTrace.length; i++) {
            String className = stackTrace[i].getClassName();
            if (!C0368a.m1418l(className) && !className.equals(f1107b) && !className.equals(LogWriter.f1110b)) {
                className = stackTrace[i].getClassName();
                String methodName = stackTrace[i].getMethodName();
                StringBuilder stringBuilder4 = new StringBuilder();
                stringBuilder4.append(stackTrace[i].getLineNumber());
                stringBuilder4.append("");
                String stringBuilder5 = stringBuilder4.toString();
                stringBuilder2 = new StringBuilder();
                stringBuilder2.append(stackTrace[i].getFileName());
                stringBuilder2.append("");
                String stringBuilder6 = stringBuilder2.toString();
                stringBuilder.setLength(0);
                stringBuilder3 = new StringBuilder();
                stringBuilder3.append(className);
                stringBuilder3.append(" ");
                stringBuilder.append(stringBuilder3.toString());
                stringBuilder3 = new StringBuilder();
                stringBuilder3.append("Method: ");
                stringBuilder3.append(methodName);
                stringBuilder3.append(" ");
                stringBuilder.append(stringBuilder3.toString());
                stringBuilder3 = new StringBuilder();
                stringBuilder3.append("Line: ");
                stringBuilder3.append(stringBuilder5);
                stringBuilder3.append(" ");
                stringBuilder.append(stringBuilder3.toString());
                stringBuilder3 = new StringBuilder();
                stringBuilder3.append(" File: ");
                stringBuilder3.append(stringBuilder6);
                stringBuilder.append(stringBuilder3.toString());
                break;
            }
        }
        return stringBuilder.toString();
    }

    static class C0367a {
        protected static void m1387a(String str, String str2) {
            if (C0368a.f1106a) {
                PrintStream printStream = System.out;
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(C0368a.m1417k(""));
                stringBuilder.append(C0367a.m1399g(str, str2));
                printStream.println(stringBuilder.toString());
            }
        }

        protected static void m1388a(String str, String str2, String str3) {
            if (C0368a.f1106a) {
                PrintStream printStream = System.out;
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(C0368a.m1417k(str2));
                stringBuilder.append(C0367a.m1399g(str, str3));
                printStream.println(stringBuilder.toString());
            }
        }

        protected static void m1389b(String str, String str2) {
            if (C0368a.f1106a) {
                Log.d(C0368a.m1417k(""), C0367a.m1399g(str, str2));
            }
        }

        protected static void m1390b(String str, String str2, String str3) {
            if (C0368a.f1106a) {
                Log.d(C0368a.m1417k(str2), C0367a.m1399g(str, str3));
            }
        }

        protected static void m1391c(String str, String str2) {
            if (C0368a.f1106a) {
                Log.e(C0368a.m1417k(""), C0367a.m1399g(str, str2));
            }
        }

        protected static void m1392c(String str, String str2, String str3) {
            if (C0368a.f1106a) {
                Log.e(C0368a.m1417k(str2), C0367a.m1399g(str, str3));
            }
        }

        protected static void m1393d(String str, String str2) {
            if (C0368a.f1106a) {
                Log.i(C0368a.m1417k(""), C0367a.m1399g(str, str2));
            }
        }

        protected static void m1394d(String str, String str2, String str3) {
            if (C0368a.f1106a) {
                Log.i(C0368a.m1417k(str2), C0367a.m1399g(str, str3));
            }
        }

        protected static void m1395e(String str, String str2) {
            if (C0368a.f1106a) {
                Log.v(C0368a.m1417k(""), C0367a.m1399g(str, str2));
            }
        }

        protected static void m1396e(String str, String str2, String str3) {
            if (C0368a.f1106a) {
                Log.v(C0368a.m1417k(str2), C0367a.m1399g(str, str3));
            }
        }

        protected static void m1397f(String str, String str2) {
            if (C0368a.f1106a) {
                Log.wtf(C0368a.m1417k(""), C0367a.m1399g(str, str2));
            }
        }

        protected static void m1398f(String str, String str2, String str3) {
            if (C0368a.f1106a) {
                Log.wtf(C0368a.m1417k(str2), C0367a.m1399g(str, str3));
            }
        }

        private static String m1399g(String str, String str2) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(str2);
            stringBuilder.append(" # ");
            stringBuilder.append(C0367a.m1386a(str));
            return stringBuilder.toString();
        }

        private static String m1386a(String str) {
            StringBuilder stringBuilder = new StringBuilder();
            StackTraceElement[] stackTrace = new Throwable().fillInStackTrace().getStackTrace();
            StringBuilder stringBuilder2 = new StringBuilder();
            stringBuilder2.append("<unknown>");
            stringBuilder2.append(" ");
            stringBuilder.append(stringBuilder2.toString());
            StringBuilder stringBuilder3 = new StringBuilder();
            stringBuilder3.append("Method: ");
            stringBuilder3.append("<unknown>");
            stringBuilder3.append(" ");
            stringBuilder.append(stringBuilder3.toString());
            stringBuilder3 = new StringBuilder();
            stringBuilder3.append("Line: ");
            stringBuilder3.append("<unknown>");
            stringBuilder3.append(" ");
            stringBuilder.append(stringBuilder3.toString());
            stringBuilder3 = new StringBuilder();
            stringBuilder3.append(" File: ");
            stringBuilder3.append("<unknown>");
            stringBuilder.append(stringBuilder3.toString());
            for (int i = 2; i < stackTrace.length; i++) {
                String className = stackTrace[i].getClassName();
                if (!C0368a.m1418l(className) && className.equalsIgnoreCase(str)) {
                    str = stackTrace[i].getClassName();
                    className = stackTrace[i].getMethodName();
                    StringBuilder stringBuilder4 = new StringBuilder();
                    stringBuilder4.append(stackTrace[i].getLineNumber());
                    stringBuilder4.append("");
                    String stringBuilder5 = stringBuilder4.toString();
                    StringBuilder stringBuilder6 = new StringBuilder();
                    stringBuilder6.append(stackTrace[i].getFileName());
                    stringBuilder6.append("");
                    String stringBuilder7 = stringBuilder6.toString();
                    stringBuilder.setLength(0);
                    stringBuilder3 = new StringBuilder();
                    stringBuilder3.append(str);
                    stringBuilder3.append(" ");
                    stringBuilder.append(stringBuilder3.toString());
                    StringBuilder stringBuilder8 = new StringBuilder();
                    stringBuilder8.append("Method: ");
                    stringBuilder8.append(className);
                    stringBuilder8.append(" ");
                    stringBuilder.append(stringBuilder8.toString());
                    stringBuilder8 = new StringBuilder();
                    stringBuilder8.append("Line: ");
                    stringBuilder8.append(stringBuilder5);
                    stringBuilder8.append(" ");
                    stringBuilder.append(stringBuilder8.toString());
                    stringBuilder8 = new StringBuilder();
                    stringBuilder8.append(" File: ");
                    stringBuilder8.append(stringBuilder7);
                    stringBuilder.append(stringBuilder8.toString());
                    break;
                }
            }
            return stringBuilder.toString();
        }
    }

    private static boolean m1418l(String str) {
        return str == null || str.trim().isEmpty() || str.equalsIgnoreCase("null");
    }
}
