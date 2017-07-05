package app.developteam.detectfall.myapplication;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by barbie on 2/5/2017.
 */

public class SharePreferenceHelper {
//    public static final String DEFAULT_IP="192.168.1.41";
    public static final String DEFAULT_IP="172.30.35.153";
    public static final String FILE_SHARE ="SETTING_FILE";
    public static final String IP ="IPADDRESS";
    public static final String TEL="TELEPHONE";
    public static final String USER="USER";
    public static final String PASS="PASS";
    public static final String USECONTACT="usercontact";
    public static final String SERVICE ="service";

    Context context;

    public SharePreferenceHelper(Context context) {
        this.context = context;
    }

    public void putIPSharePreference(String ipaddress){
        SharedPreferences sp=context.getSharedPreferences(FILE_SHARE,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(IP,ipaddress);
        editor.commit();
    }

    public String getIPSharePreference(){
        SharedPreferences sp =context.getSharedPreferences(FILE_SHARE,Context.MODE_PRIVATE);
        String ipaddress =sp.getString(IP,DEFAULT_IP);
        return ipaddress;
    }

    public void putTelSharePreference(String tel){
        SharedPreferences sp=context.getSharedPreferences(FILE_SHARE,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(TEL,tel);
        editor.commit();
    }

    public String getTelSharePreference(){
        SharedPreferences sp =context.getSharedPreferences(FILE_SHARE,Context.MODE_PRIVATE);
        String tel =sp.getString(TEL,"");
        return tel;
    }

    public void putUsernameSharePreference(String user){
        SharedPreferences sp=context.getSharedPreferences(FILE_SHARE,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(USER,user);
        editor.commit();
    }

    public String getUsernameSharePreference(){
        SharedPreferences sp =context.getSharedPreferences(FILE_SHARE,Context.MODE_PRIVATE);
        String user =sp.getString(USER,"");
        return user;
    }

    public void putPasswordSharePreference(String pass){
        SharedPreferences sp=context.getSharedPreferences(FILE_SHARE,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(PASS,pass);
        editor.commit();
    }

    public String getPasswordSharePreference(){
        SharedPreferences sp =context.getSharedPreferences(FILE_SHARE,Context.MODE_PRIVATE);
        String pass =sp.getString(PASS,"");
        return pass;
    }

    public void putUseContactSharePreference(boolean use){
        SharedPreferences sp=context.getSharedPreferences(FILE_SHARE,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean(USECONTACT,use);
        editor.commit();
    }

    public boolean getUsecontactSharePreference(){
        SharedPreferences sp =context.getSharedPreferences(FILE_SHARE,Context.MODE_PRIVATE);
        boolean usecon =sp.getBoolean(USECONTACT,false);
        return usecon;
    }



    public void putServiceSharePreference(boolean service){
        SharedPreferences sp=context.getSharedPreferences(FILE_SHARE,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean(SERVICE,service);
        editor.commit();
    }

    public boolean getServiceSharePreference(){
        SharedPreferences sp =context.getSharedPreferences(FILE_SHARE,Context.MODE_PRIVATE);
        boolean service =sp.getBoolean(SERVICE,false);
        return service;
    }


}
