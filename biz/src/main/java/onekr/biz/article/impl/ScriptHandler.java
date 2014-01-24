package onekr.biz.article.impl;

import onekr.biz.model.Script;
import onekr.framework.result.Result;

public interface ScriptHandler {

	Result handle(Script script, Object[] args);
}
