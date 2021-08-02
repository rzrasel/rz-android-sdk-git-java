package com.rzandjavagit.core.log;

public class LogWriter {
    public static boolean isDebug = true;
    protected static final String f1110b = LogWriter.class.getName();

    public static void Log(String argMessage) {
        if (isDebug) {
            C0368a.f1106a = isDebug;
            C0368a.m1401a(argMessage);
        }
    }

    public static void Log(String str, String argMessage) {
        if (isDebug) {
            C0368a.f1106a = isDebug;
            C0368a.m1402a(str, argMessage);
        }
    }

    public static void dLog(String str) {
        if (isDebug) {
            C0368a.f1106a = isDebug;
            C0368a.m1403b(str);
        }
    }

    public static void dLog(String str, String str2) {
        if (isDebug) {
            C0368a.f1106a = isDebug;
            C0368a.m1404b(str, str2);
        }
    }

    public static void eLog(String str) {
        if (isDebug) {
            C0368a.f1106a = isDebug;
            C0368a.m1405c(str);
        }
    }

    public static void eLog(String str, String str2) {
        if (isDebug) {
            C0368a.f1106a = isDebug;
            C0368a.m1406c(str, str2);
        }
    }

    public static void iLog(String str) {
        if (isDebug) {
            C0368a.f1106a = isDebug;
            C0368a.m1407d(str);
        }
    }

    public static void iLog(String str, String str2) {
        if (isDebug) {
            C0368a.f1106a = isDebug;
            C0368a.m1408d(str, str2);
        }
    }

    public static void vLog(String str) {
        if (isDebug) {
            C0368a.f1106a = isDebug;
            C0368a.m1409e(str);
        }
    }

    public static void vLog(String str, String str2) {
        if (isDebug) {
            C0368a.f1106a = isDebug;
            C0368a.m1410e(str, str2);
        }
    }

    public static void wtfLog(String str) {
        if (isDebug) {
            C0368a.f1106a = isDebug;
            C0368a.m1411f(str);
        }
    }

    public static void wtfLog(String str, String str2) {
        if (isDebug) {
            C0368a.f1106a = isDebug;
            C0368a.m1412f(str, str2);
        }
    }

    public static class Writer {
        public static void Log(String argDesiredClassName, String argMessage) {
            if (isDebug) {
                C0368a.f1106a = isDebug;
                C0368a.C0367a.m1387a(argDesiredClassName, argMessage);
            }
        }

        public static void Log(String argDesiredClassName, String argTag, String argMessage) {
            if (isDebug) {
                C0368a.f1106a = isDebug;
                C0368a.C0367a.m1388a(argDesiredClassName, argTag, argMessage);
            }
        }

        public static void dLog(String argDesiredClassName, String argMessage) {
            if (isDebug) {
                C0368a.f1106a = isDebug;
                C0368a.C0367a.m1389b(argDesiredClassName, argMessage);
            }
        }

        public static void dLog(String argDesiredClassName, String argTag, String argMessage) {
            if (isDebug) {
                C0368a.f1106a = isDebug;
                C0368a.C0367a.m1390b(argDesiredClassName, argTag, argMessage);
            }
        }

        public static void eLog(String argDesiredClassName, String argMessage) {
            if (isDebug) {
                C0368a.f1106a = isDebug;
                C0368a.C0367a.m1391c(argDesiredClassName, argMessage);
            }
        }

        public static void eLog(String argDesiredClassName, String argTag, String argMessage) {
            if (isDebug) {
                C0368a.f1106a = isDebug;
                C0368a.C0367a.m1392c(argDesiredClassName, argTag, argMessage);
            }
        }

        public static void iLog(String argDesiredClassName, String argMessage) {
            if (isDebug) {
                C0368a.f1106a = isDebug;
                C0368a.C0367a.m1393d(argDesiredClassName, argMessage);
            }
        }

        public static void iLog(String argDesiredClassName, String argTag, String argMessage) {
            if (isDebug) {
                C0368a.f1106a = isDebug;
                C0368a.C0367a.m1394d(argDesiredClassName, argTag, argMessage);
            }
        }

        public static void vLog(String argDesiredClassName, String argMessage) {
            if (isDebug) {
                C0368a.f1106a = isDebug;
                C0368a.C0367a.m1395e(argDesiredClassName, argMessage);
            }
        }

        public static void vLog(String argDesiredClassName, String argTag, String argMessage) {
            if (isDebug) {
                C0368a.f1106a = isDebug;
                C0368a.C0367a.m1396e(argDesiredClassName, argTag, argMessage);
            }
        }

        public static void wtfLog(String argDesiredClassName, String argMessage) {
            if (isDebug) {
                C0368a.f1106a = isDebug;
                C0368a.C0367a.m1397f(argDesiredClassName, argMessage);
            }
        }

        public static void wtfLog(String argDesiredClassName, String argTag, String argMessage) {
            if (isDebug) {
                C0368a.f1106a = isDebug;
                C0368a.C0367a.m1398f(argDesiredClassName, argTag, argMessage);
            }
        }
    }
}
