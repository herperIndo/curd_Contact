package com.test.contact_jenius.Network;

import com.test.contact_jenius.Models.allContact;
import com.test.contact_jenius.Models.delete;
import com.test.contact_jenius.Models.wraperContectById;
import com.test.contact_jenius.Models.wraperSave;
import com.test.contact_jenius.Parameter.saveParam;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import rx.Observable;

public interface NetworkStores {
    @GET("contact")
    Observable<allContact> getAllContact();

    @GET("contact/{id}")
    Observable<wraperContectById> getContactCategory(@Path("id") String id);

    @POST("contact")
    Observable<delete> saveContact(@Body saveParam param);

    @DELETE("contact/{id}")
    Observable<delete> getDeleteContact(@Path("id") String id);

    @PUT("contact/{id}")
    Observable<delete> updateContact(@Path("id") String id,@Body saveParam param);
}
