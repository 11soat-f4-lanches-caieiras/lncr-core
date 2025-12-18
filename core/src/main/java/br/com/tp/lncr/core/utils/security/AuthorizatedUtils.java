package br.com.tp.lncr.core.utils.security;

import java.util.List;
import java.util.Map;

public class AuthorizatedUtils {

    private AuthorizatedUtils() {
    }

    public static boolean isAuthorizedResult(Map<String, Object> allowResourcesRules, String scope, String method, String path) {
        boolean result = false;
        if (allowResourcesRules.containsKey(scope)) {
            Map<String, List<String>> scopeData = (Map<String, List<String>>) allowResourcesRules.get(scope);

            if (scopeData.containsKey(method)) {
                List<String> paths = scopeData.get(method).stream().toList();
                if (paths.contains(path)) {
                    result = true;
                }
            }
        }
        return result;
    }
}