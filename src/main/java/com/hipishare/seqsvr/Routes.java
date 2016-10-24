package com.hipishare.seqsvr;

import io.netty.handler.codec.http.HttpMethod;

import org.restexpress.RestExpress;

import com.hipishare.seqsvr.controller.UseridSeqController;
import com.hipishare.seqsvr.controller.UseridGenController;

public abstract class Routes {
	public static void define(RestExpress server) {
		server.uri("/seqsvr/getUseridSeq", new UseridSeqController()).action(
				"getUseridSeq", HttpMethod.POST);
		server.uri("/seqsvr/genUserid", new UseridGenController()).action(
				"genUserid", HttpMethod.POST);
	}
}
