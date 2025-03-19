package com.task02;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.syndicate.deployment.annotations.lambda.LambdaHandler;
import com.syndicate.deployment.model.RetentionSetting;

import java.util.HashMap;
import java.util.Map;

@LambdaHandler(
    lambdaName = "hello_world",
	roleName = "hello_world-role",
	isPublishVersion = true,
	aliasName = "${lambdas_alias_name}",
	logsExpiration = RetentionSetting.SYNDICATE_ALIASES_SPECIFIED
)
public class HelloWorld implements RequestHandler<Map<String, String>, Map<String, Object>> {

	@Override
	public Map<String, Object> handleRequest(Map<String, String> request, Context context) {
		String path = request.get("path");
		String method = request.get("method");

		Map<String, Object> resultMap = new HashMap<>();

		if ("GET".equals(method) && "/hello".equals(path)) {
			resultMap.put("statusCode", 200);
			resultMap.put("message", "Hello from Lambda");
		} else {
			resultMap.put("statusCode", 400);
			resultMap.put("message", "Bad request syntax or unsupported method. Request path: "
					+ path + ". HTTP method: " + method);
		}

		return resultMap;
	}
}
