package pe.demo.papayin.ui.util;

import android.content.SharedPreferences;
import android.text.TextUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class PDUtilSharedPreference {

    public static final String SA_SHARED_PREFERENCE = "AHORA_SHARED_PREFERENCE";
    private SharedPreferences sharedPreferences;

    public PDUtilSharedPreference(SharedPreferences sharedPreferences) {
        this.sharedPreferences = sharedPreferences;
    }

    public <T> void setList(String key, List<T> list) {
        Gson gson = new Gson();
        String json = gson.toJson(list);

        SharedPreferences.Editor editor = getSharePreferenceEditor();
        editor.putString(key, json);
        editor.commit();
    }

    public <T> List<T> getList(String key, Class<T> myType) {
        List<T> arrayItems = null;
        String serializedObject = sharedPreferences.getString(key, null);
        if (serializedObject != null) {
            Gson gson = new Gson();
            Type collectionType = TypeToken.getParameterized(List.class, myType).getType();
            arrayItems = gson.fromJson(serializedObject, collectionType);
        }
        return arrayItems;
    }

    public void setObject(Object object, String key) {
        String jsonObjectString = "";

        if (object != null) {
            Gson gson = new Gson();
            jsonObjectString = gson.toJson(object);
        }

        SharedPreferences.Editor editor = getSharePreferenceEditor();
        editor.putString(key, jsonObjectString);
        editor.commit();
    }

    public <T> Object getObject(String key, Class<T> classReference) {
        Object object = null;
        String objString = sharedPreferences.getString(key, "");

        if (!TextUtils.isEmpty(objString)) {
            Gson gson = new Gson();
            object = gson.fromJson(objString, classReference);
        }

        return object;
    }

    public void setString(String key, String value) {
        SharedPreferences.Editor editor = getSharePreferenceEditor();
        editor.putString(key, value);
        editor.commit();
    }

    public String getString(String key) {
        return sharedPreferences.getString(key, "");
    }

    public void setBoolean(String key, boolean value) {
        SharedPreferences.Editor editor = getSharePreferenceEditor();
        editor.putBoolean(key, value);
        editor.commit();
    }

    public void setInteger(String key, Integer value) {
        SharedPreferences.Editor editor = getSharePreferenceEditor();
        editor.putInt(key, value);
        editor.commit();
    }

    public Integer getInteger(String key) {
        return sharedPreferences.getInt(key, 0);
    }

    public boolean getBoolean(String key) {
        return sharedPreferences.getBoolean(key, false);
    }

    private SharedPreferences.Editor getSharePreferenceEditor() {
        return sharedPreferences.edit();
    }

}
