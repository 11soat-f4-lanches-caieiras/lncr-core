package br.com.tp.lncr.core.bdd.oauth;

import io.cucumber.junit.platform.engine.Constants;
import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;

@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("bdd/oauth")
@ConfigurationParameter(key = Constants.GLUE_PROPERTY_NAME, value = "br.com.tp.lncr.core.bdd.oauth")
@ConfigurationParameter(key = Constants.PLUGIN_PROPERTY_NAME, value = "pretty, html:target/cucumber-reports/oauth.html, json:target/cucumber-reports/oauth.json")
@ConfigurationParameter(key = Constants.FILTER_TAGS_PROPERTY_NAME, value = "not @ignore")
public class AllOauthRunners {
}

