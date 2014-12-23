package onekr.portalservice.article.impl;

import onekr.framework.result.Result;
import onekr.portalservice.model.Script;

public interface ScriptHandler {

	Result handle(Script script, Object[] args);
}
