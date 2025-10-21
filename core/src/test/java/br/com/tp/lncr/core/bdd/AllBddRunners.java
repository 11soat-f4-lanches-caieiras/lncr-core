package br.com.tp.lncr.core.bdd;

import br.com.tp.lncr.core.bdd.oauth.AllOauthRunners;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;
import org.junit.platform.suite.api.SuiteDisplayName;

@Suite
@SuiteDisplayName("Todos os Testes BDD")
@SelectClasses({
    AllOauthRunners.class
})
public class AllBddRunners {
}

