package com.classifier.services;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.classifier.dto.RequestClassifierDTOO;
import com.classifier.dto.ResponseClassifier;
import com.classifier.services.retrofit.PythonAPI;
import com.classifier.services.retrofit.RetrofitService;

import retrofit2.Call;
import retrofit2.Response;

@Service
public class APIPythonService {

	private static final Logger LOG = LoggerFactory.getLogger(APIPythonService.class);

	private static final PythonAPI PYTHON_API = RetrofitService.getBancoBrasilHomologacaoInstance()
			.create(PythonAPI.class);

	public ResponseClassifier classifierImage(RequestClassifierDTOO obj) {
		
		LOG.info("Enviando imagem para API Python");
		ResponseClassifier res = null;

		Call<ResponseClassifier> call = PYTHON_API.sendImage(obj);
		try {
			Response<ResponseClassifier> response = call.execute();
			if (response.isSuccessful()) {
				System.out.println( "\n\n\n res: " + response.body().getImg());
				res = new ResponseClassifier();
				res.setMsg(response.body().getImg());
				
				if (res.getMsg().equals("Essa imagem nao e um documento"))
					res.setDocument(false);
				else 
					res.setDocument(true);	
			} else {
				res = new ResponseClassifier();
				res.setMsg(response.body().getImg());
				res.setDocument(false);
				//String err = response.body();
				//System.out.println("\n\n erro: "+ err);
			}
		} catch (IOException e1) {
			LOG.info("Erro sicrono " + e1.getMessage());
		}
		return res;
	}
}
