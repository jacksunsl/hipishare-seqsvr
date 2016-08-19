package com.hipishare.seqsvr;

import io.netty.handler.codec.http.HttpMethod;
import org.restexpress.RestExpress;
import com.hipishare.seqsvr.controller.SeqServerController;

public abstract class Routes {
	public static void define(RestExpress server) {
		server.uri("/seqsvr/getSeq.{format}", new SeqServerController()).action(
				"getSeq", HttpMethod.GET);
	}
}
