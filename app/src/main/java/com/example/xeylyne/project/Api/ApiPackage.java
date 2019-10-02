package com.example.xeylyne.project.Api;

import com.example.xeylyne.project.ListData.ListAllGOR;
import com.example.xeylyne.project.ListData.ListGOR;
import com.example.xeylyne.project.ListData.ListGOR_user;
import com.example.xeylyne.project.ListData.ListInsertGOR;
import com.example.xeylyne.project.ListData.ListLap;
import com.example.xeylyne.project.ListData.ListLap_user;
import com.example.xeylyne.project.ListData.ListLapangan;
import com.example.xeylyne.project.ListData.Login;
import com.example.xeylyne.project.ListData.RegisUser;
import com.example.xeylyne.project.ListData.UserList;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface ApiPackage {

    @FormUrlEncoded
    @POST("login_user.php")
    Call<Login> login(@Field("username") String username,
                      @Field("password") String password);

    @FormUrlEncoded
    @POST("register_user.php")
    Call<RegisUser> register(@Field("username") String username,
                             @Field("password") String password,
                             @Field("email") String email,
                             @Field("no_telp") String no_telp,
                             @Field("stats_user") String stats_user);

    @FormUrlEncoded
    @POST("getData1.php")
    Call<ListLapangan> lapangan(@Field("username") String username,
                                @Field("stats_user") String stats_user,
                                @Field("jns_lap") int jns_lap);

    @FormUrlEncoded
    @POST("getDataGor.php")
    Call<ListGOR> gor(@Field("username") String username);

    @FormUrlEncoded
    @POST("getDatalap_owner.php")
    Call<ListLap> lap(@Field("id_gor") String id_gor);

    @FormUrlEncoded
    @POST("InsertData.php")
    Call<ListInsertGOR> insertGOR(@Field("nama_gor") String nama_gor,
                                  @Field("alamat_gor") String alamat_gor,
                                  @Field("jam_buka") String jam_buka,
                                  @Field("jam_tutup") String jam_tutup,
                                  @Field("username") String username);

    @FormUrlEncoded
    @POST("InsertDataLap.php")
    Call<ListInsertGOR> insertlap(@Field("jns_lap") String jns_lap,
                                  @Field("indeks_lap") String indeks_lap,
                                  @Field("harga_lap") String harga_lap,
                                  @Field("id_gor") String id_gor);

    @FormUrlEncoded
    @POST("getDataGOR_user.php")
    Call<ListGOR_user> gor_user(@Field("jns_lap") String jns_lap);

    @FormUrlEncoded
    @POST("getDatalap_User.php")
    Call<ListLap_user> lap_user(@Field("id_gor") String id_gor,
                                @Field("jns_lap") String jns_lap);

    @GET("getAllGOR.php")
    Call<ListAllGOR> listAllGOR();

    @FormUrlEncoded
    @POST("DeleteData.php")
    Call<ListGOR> del_data(@Field("id_gor") String id_gor);

    @FormUrlEncoded
    @POST("DeleteDataLap.php")
    Call<ListLap> del_datalap(@Field("id_lap") String id_lap);

    @FormUrlEncoded
    @POST("UpdateData.php")
    Call<ListGOR> update_data(@Field("id_gor") String id_gor,
                              @Field("nama_gor") String nama_gor,
                              @Field("alamat_gor") String alamat_gor,
                              @Field("jam_buka") String jam_buka,
                              @Field("jam_tutup") String jam_tutup);

}
