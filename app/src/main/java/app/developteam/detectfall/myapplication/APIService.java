package app.developteam.detectfall.myapplication;

import com.squareup.okhttp.RequestBody;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by barbie on 4/8/2017.
 */

public interface APIService {


    @FormUrlEncoded
    @POST("api/addsensor.php")
    Call<ResponseModel> insertDataBase(@Field("round") String round
            ,@Field("ax") String ax
            ,@Field("ay") String ay
            ,@Field("az") String  az
            ,@Field("acceleration") String  acceleration
            ,@Field("time") String time);

    @FormUrlEncoded
    @POST("/sms_api.php")
    Call<ResponseModel> sendSms(@Field("username") String username
            ,@Field("password") String password
            ,@Field("msisdn") String number
            ,@Field("message") String  message);

    @FormUrlEncoded
    @POST("/sms/check_credit.php")
    Call<GetSms> checkThaiBulkUser(@Field("username") String username
            ,@Field("password") String password);

}
