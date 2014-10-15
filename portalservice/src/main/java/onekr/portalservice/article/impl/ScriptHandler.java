package onekr.portalservice.article.impl;

import onekr.commonservice.model.Script;
import onekr.framework.result.Result;

public interface ScriptHandler {

	Result handle(Script script, Object[] args);
}
