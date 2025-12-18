package br.com.tp.lncr.core.utils.security;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AuthorizatedUtilsTest {

    @Test
    void returnsAuthorizationWhenScopeMethodAndPathMatch() {
        Map<String, Object> rules = new HashMap<>();
        Map<String, List<String>> scopeData = new HashMap<>();
        scopeData.put("GET", List.of("/api/customers", "/api/orders"));
        rules.put("admin", scopeData);

        boolean result = AuthorizatedUtils.isAuthorizedResult(rules, "admin", "GET", "/api/customers");

        assertTrue(result);
    }

    @Test
    void returnsAuthorizationWhenMultiplePathsExistAndOneMatches() {
        Map<String, Object> rules = new HashMap<>();
        Map<String, List<String>> scopeData = new HashMap<>();
        scopeData.put("POST", List.of("/api/products", "/api/orders", "/api/items"));
        rules.put("user", scopeData);

        boolean result = AuthorizatedUtils.isAuthorizedResult(rules, "user", "POST", "/api/orders");

        assertTrue(result);
    }

    @Test
    void deniesAuthorizationWhenScopeDoesNotExist() {
        Map<String, Object> rules = new HashMap<>();
        Map<String, List<String>> scopeData = new HashMap<>();
        scopeData.put("GET", List.of("/api/customers"));
        rules.put("admin", scopeData);

        boolean result = AuthorizatedUtils.isAuthorizedResult(rules, "guest", "GET", "/api/customers");

        assertFalse(result);
    }

    @Test
    void deniesAuthorizationWhenMethodDoesNotExist() {
        Map<String, Object> rules = new HashMap<>();
        Map<String, List<String>> scopeData = new HashMap<>();
        scopeData.put("GET", List.of("/api/customers"));
        rules.put("admin", scopeData);

        boolean result = AuthorizatedUtils.isAuthorizedResult(rules, "admin", "POST", "/api/customers");

        assertFalse(result);
    }

    @Test
    void deniesAuthorizationWhenPathDoesNotExist() {
        Map<String, Object> rules = new HashMap<>();
        Map<String, List<String>> scopeData = new HashMap<>();
        scopeData.put("GET", List.of("/api/customers"));
        rules.put("admin", scopeData);

        boolean result = AuthorizatedUtils.isAuthorizedResult(rules, "admin", "GET", "/api/orders");

        assertFalse(result);
    }

    @Test
    void deniesAuthorizationWhenRulesAreEmpty() {
        Map<String, Object> rules = new HashMap<>();

        boolean result = AuthorizatedUtils.isAuthorizedResult(rules, "admin", "GET", "/api/customers");

        assertFalse(result);
    }

    @Test
    void deniesAuthorizationWhenScopeExistsButMethodListIsEmpty() {
        Map<String, Object> rules = new HashMap<>();
        Map<String, List<String>> scopeData = new HashMap<>();
        rules.put("admin", scopeData);

        boolean result = AuthorizatedUtils.isAuthorizedResult(rules, "admin", "GET", "/api/customers");

        assertFalse(result);
    }

    @Test
    void deniesAuthorizationWhenMethodExistsButPathListIsEmpty() {
        Map<String, Object> rules = new HashMap<>();
        Map<String, List<String>> scopeData = new HashMap<>();
        scopeData.put("GET", List.of());
        rules.put("admin", scopeData);

        boolean result = AuthorizatedUtils.isAuthorizedResult(rules, "admin", "GET", "/api/customers");

        assertFalse(result);
    }

    @Test
    void returnsAuthorizationWithMultipleScopesAndCorrectScopeMethodPathCombination() {
        Map<String, Object> rules = new HashMap<>();

        Map<String, List<String>> adminScope = new HashMap<>();
        adminScope.put("GET", List.of("/api/admin/users"));
        adminScope.put("DELETE", List.of("/api/admin/delete"));
        rules.put("admin", adminScope);

        Map<String, List<String>> userScope = new HashMap<>();
        userScope.put("GET", List.of("/api/user/profile"));
        rules.put("user", userScope);

        boolean result = AuthorizatedUtils.isAuthorizedResult(rules, "user", "GET", "/api/user/profile");

        assertTrue(result);
    }

    @Test
    void deniesAuthorizationWhenAccessingDifferentScopeResource() {
        Map<String, Object> rules = new HashMap<>();

        Map<String, List<String>> adminScope = new HashMap<>();
        adminScope.put("GET", List.of("/api/admin/users"));
        rules.put("admin", adminScope);

        Map<String, List<String>> userScope = new HashMap<>();
        userScope.put("GET", List.of("/api/user/profile"));
        rules.put("user", userScope);

        boolean result = AuthorizatedUtils.isAuthorizedResult(rules, "user", "GET", "/api/admin/users");

        assertFalse(result);
    }
}

