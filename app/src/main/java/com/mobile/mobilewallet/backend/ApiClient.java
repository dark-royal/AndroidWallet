package com.mobile.mobilewallet.backend;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.POST;

public interface ApiClient {

    @POST
    Call<ResponseBody> createAccount(RequestBody requestBody);
}
