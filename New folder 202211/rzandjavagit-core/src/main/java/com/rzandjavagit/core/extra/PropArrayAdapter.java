package com.rzandjavagit.core.extra;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

//com.adept.archery-pack
public class PropArrayAdapter<T> extends ArrayAdapter<T> {
    //|------------------------------------------------------------|
    private Context context;
    private int layoutResourceId;
    private ArrayList<T> adapterListItems;

    //|------------------------------------------------------------|
    private OnFieldListenerHandler fieldListenerHandler;
    //private RowViewHolder rowViewHolder;

    //|------------------------------------------------------------|
    public PropArrayAdapter(Context argContext, int argLayoutResourceId, ArrayList<T> argAdapterListItems) {
        super(argContext, argLayoutResourceId, argAdapterListItems);
        this.context = argContext;
        this.layoutResourceId = argLayoutResourceId;
        this.adapterListItems = argAdapterListItems;
    }

    @Override
    public int getCount() {
        return adapterListItems.size();
    }

    @Override
    public T getItem(int argPosition) {
        return adapterListItems.get(argPosition);
    }

    @Override
    public long getItemId(int argPosition) {
        return argPosition;
    }

    public PropArrayAdapter setRowViewHolder(RowViewHolder argRowViewHolder) {
        //rowViewHolder = argRowViewHolder;
        return this;
    }

    public PropArrayAdapter setFieldListenerHandler(OnFieldListenerHandler argFieldListenerHandler) {
        fieldListenerHandler = argFieldListenerHandler;
        return this;
    }

    @Override
    public View getView(int argPosition, View argConvertView, ViewGroup argParent) {
        View rootRowView = argConvertView;
        //RowViewHolder rowViewHolder = null;
        Object rowViewHolder = null;
        if (argConvertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            rootRowView = layoutInflater.inflate(layoutResourceId, argParent, false);
            /*rowViewHolder = new RowViewHolder(rootRowView);
            rootRowView.setTag(rowViewHolder);*/
            if (fieldListenerHandler != null) {
                rowViewHolder = fieldListenerHandler.onSetRowViewHolder(rootRowView);
                //rootRowView.setTag(rowViewHolder);
            }
            rootRowView.setTag(rowViewHolder);
        } else {
            rowViewHolder = (Object) rootRowView.getTag();
        }
        if (rootRowView != null) {
            Object items = adapterListItems.get(argPosition);
            if (fieldListenerHandler != null) {
                fieldListenerHandler.onSetFieldValue(items, rowViewHolder);
            }
            /*//onInitializedLayoutFields(context, rowViewRoot);
            SparkedInitializedRowFields.onSetRowFields(context, rowViewRoot, rowViewFieldListItems);
            //Object item = getItem(argPosition);
            Object items = adapterListItems.get(argPosition);
            //if(list.get(argPosition)  instanceof A)
            if (onFieldListenerHandler != null) {
                onFieldListenerHandler.onSetFieldValue(rowViewFieldListItems, items);
            }
            onSetRowViewFieldsValues(rowViewFieldListItems, items);*/
        }
        //System.out.println("|----|------------|AdapterData|----|" + argPosition);
        return rootRowView;
    }

    //|------------------------------------------------------------|
    public interface OnFieldListenerHandler {
        public Object onSetRowViewHolder(View argRootView);

        public void onSetFieldValue(Object argItem, Object argRowViewHolder);
        //public void onSetResourceLayout(int argLayoutResourceId);
        //Customer cust = (Customer) pObject;
    }

    //|------------------------------------------------------------|
    public static class RowViewHolder {
        /*public RowViewHolder(View argRootView) {
        }*/
        /*public RelativeLayout sysReLayCircle;
        public TextView sysTextViewCounter;
        public ImageView sysImageViewIcon;
        public TextView sysTextViewTitle;

        public RowViewHolder(View argRootView) {
            sysReLayCircle = argRootView.findViewById(R.id.sysReLayCircle);
            sysTextViewCounter = argRootView.findViewById(R.id.sysTextViewCounter);
            sysImageViewIcon = argRootView.findViewById(R.id.sysImageViewIcon);
            sysTextViewTitle = argRootView.findViewById(R.id.sysTextViewTitle);
        }*/
        public void onRowViewHolder(View argRootView) {
            /*Class<?> myClass = ActReportHome.class;
            Object object = myClass.newInstance();*/
        }
    }
    //|------------------------------------------------------------|
}

class MagicModel {
    public void onModelWork() {
        ArrayList<Data> dataArrayList = (ArrayList<Data>) getModel(Data.class);
        setModel(Data.class);
        ArrayList<Data> dataArrayOne = (ArrayList<Data>) Tester.getModel(Data.class);
        ArrayList<Data> dataArrayTwo = Tester.getModel();
    }

    public <T> ArrayList<T> getModel(Class<T> type) {
        ArrayList<T> arrayList = new ArrayList<T>();
        return arrayList;
    }

    public <T> void setModel(Class<T> type) {
        ArrayList<T> arrayList = new ArrayList<T>();
    }

    public class Data {
        private int id;
    }

    public static class Tester {
        public static <T> ArrayList<T> getModel(Class<T> type) {
            ArrayList<T> arrayList = new ArrayList<T>();
            return arrayList;
        }

        public static <T> ArrayList<T> getModel() {
            ArrayList<T> arrayList = new ArrayList<T>();
            return arrayList;
        }
    }
}
/*
class GenericDisplayAdapter<T> {
    private List<T> m_ArrData;

    public GenericDisplayAdapter(Activity activity, ArrayList<T> arrData) {
        ...
    }
}
public GenericDisplayAdapter(Activity activity, ArrayList<?> arrData)
*/
//https://android.googlesource.com/platform/frameworks/base/+/a175a5b/core/java/android/view/View.java
//https://www.journaldev.com/1663/java-generics-example-method-class-interface
//https://www.talentica.com/blogs/develop-an-android-library-part-1/
//http://www.skylit.com/javamethods/faqs/createjar.html
//https://gist.github.com/nisrulz/64dd09e0922aa48351c0
/*
public class RealmUtil {


    private static HashMap<String, HashMap<String, String>> classesFieldsMap = new HashMap<>();

    static {
        initClasses(Chat.class);
    }

    private static void initClasses(Class cls) {
        if (!classesFieldsMap.containsKey(cls.toString())) {
            Field[] fields = cls.getDeclaredFields();
            HashMap<String, String> fieldsMap = new HashMap<>();
            for (Field a : fields) {
                SerializedName annotation = a.getAnnotation(SerializedName.class);
                if (annotation != null) {
                    fieldsMap.put(annotation.value(), a.getName());
                }
            }
            classesFieldsMap.put(cls.toString(), fieldsMap);
        }
    }

    public static JSONObject mapGsonObjectToRealm(Class cls, JSONObject object) throws JSONException {
        JSONObject newUserObj = new JSONObject();
        HashMap<String, String> fieldsMap = classesFieldsMap.get(cls.toString());
        for (String setKey : fieldsMap.keySet()) {
            String mappedKey = fieldsMap.get(setKey);
            if (object.has(setKey))
                newUserObj.put(mappedKey, object.get(setKey));
        }
        return newUserObj;
    }
}
*/
/*
private static void initClasses(Class argClass) {
        HashMap<String, HashMap<String, String>> classesFieldsMap = new HashMap<>();
        if (!classesFieldsMap.containsKey(argClass.toString())) {
            Field[] fields = argClass.getDeclaredFields();
            System.out.println("SerializedName: SIZE " + fields.length);
            HashMap<String, String> fieldsMap = new HashMap<>();
            for (Field item : fields) {
                SerializedName annotation = item.getAnnotation(SerializedName.class);
                if (annotation != null) {
                    fieldsMap.put(annotation.value(), item.getName());
                    System.out.println("SerializedName: getName " + item.getName());
                    System.out.println("SerializedName: annotation " + annotation.value());
                }
            }
            classesFieldsMap.put(argClass.toString(), fieldsMap);
        }
        /- *for(Field field  : argClass.getDeclaredFields()) {
            if (field.isAnnotationPresent(RealmField.class)) {
                RealmField ann = field.getAnnotation(RealmField.class);
                String internalName = ann.name();
            }
        }*- /
//https://github.com/realm/realm-java/issues/1470
    }

public static List<Field> getAllModelFields(Class argClass) {
        List<Field> fields = new ArrayList<>();
        do {
        Collections.addAll(fields, argClass.getDeclaredFields());
        argClass = argClass.getSuperclass();
        } while (argClass != null);
        return fields;
        }

//public static Collection<Field> getFields(Class<?> clazz) {
public static List<Field> getFields(Class<?> clazz) {
        //    if (log.isDebugEnabled()) {
        //    log.debug("getFields(Class<?>) - start");
        //}

        //Map<String, Field> fields = new HashMap<String, Field>();
        List<Field> fields = new ArrayList<>();
        while (clazz != null) {
        for (Field field : clazz.getDeclaredFields()) {
                /- *if (!fields.containsKey(field.getName())) {
                    fields.put(field.getName(), field);
                }*- /
        fields.add(field);
        }

        clazz = clazz.getSuperclass();
        }

        //Collection<Field> returnCollection = fields.values();
        /- *List<Field> returnCollection = new ArrayList<>();
        returnCollection = (List<Field>) fields.values();*- /
        //  if (log.isDebugEnabled()) {
        //  log.debug("getFields(Class<?>) - end");
        //  }
        return fields;
        }
*/
/*
//|------------------------------------------------------------|
        /- *try {
            Field fields = Field.class.getDeclaredField("name");
            SerializedName sName = fields.getAnnotation(SerializedName.class);
            System.out.println(sName.value());
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }*- /
//ActSplash.initClasses(ModelUsual.class);
//Field[] fields = ModelUsual.class.getDeclaredFields();
List<Field> fields = getFields(ModelUsual.class);
        System.out.println("SerializedName: SIZE " + fields.size());
                for (Field item : fields) {
                SerializedName annotation = item.getAnnotation(SerializedName.class);
            /- *if (annotation != null) {
                System.out.println("SerializedName: getName " + item.getName());
                System.out.println("SerializedName: annotation " + annotation.value());
            }*- /
        System.out.println("SerializedName: getName " + item.getName());
        //System.out.println("SerializedName: annotation " + annotation.value());
        }
        //http://www.java2s.com/Code/Java/Reflection/ClassReflectionfieldinformation.htm
        //http://www.java2s.com/Code/Java/Reflection/Howtosetpublicfieldobjects.htm
        //http://www.java2s.com/Code/Java/Reflection/FieldReflection.htm
        //http://www.java2s.com/Code/Java/Reflection/Getallfieldsofaclass.htm
        //|------------------------------------------------------------|
*/
//https://medium.com/@toddcookevt/android-room-storing-lists-of-objects-766cca57e3f9