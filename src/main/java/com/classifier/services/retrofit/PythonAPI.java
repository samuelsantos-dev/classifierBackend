package com.classifier.services.retrofit;

import com.classifier.dto.RequestClassifierDTOO;
import com.classifier.dto.ResponseClassifier;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface PythonAPI {

	@POST("classificador")
	Call<ResponseClassifier> sendImage(@Body RequestClassifierDTOO obj);

}
