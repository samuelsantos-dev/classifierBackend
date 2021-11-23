package com.classifier.services.retrofit;

import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Service;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Service
public abstract class RetrofitService {
	

	private static final String API_URL_PYTHON = "http://localhost:5000/";
	
	
	private static Retrofit apipythonInstance;
	
	
	public static Retrofit getBancoBrasilHomologacaoInstance() {
		if (apipythonInstance == null) {
			apipythonInstance = new Retrofit.Builder().baseUrl(API_URL_PYTHON)
					.client(getClient().build())
					.addConverterFactory(GsonConverterFactory.create())
					.build();
		}
		return apipythonInstance;
	}
	
	public static void reset() {
		apipythonInstance = null;
	}
	
	private static OkHttpClient.Builder getClient() {
		return new OkHttpClient.Builder()
				.writeTimeout(1, TimeUnit.MINUTES)
                .readTimeout(1, TimeUnit.MINUTES);
	}
}
