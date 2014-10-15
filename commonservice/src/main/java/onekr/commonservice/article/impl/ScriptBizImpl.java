package onekr.commonservice.article.impl;

import java.util.Collection;
import java.util.List;

import onekr.commonservice.article.dao.ScriptDao;
import onekr.commonservice.article.intf.ScriptBiz;
import onekr.commonservice.model.Script;
import onekr.commonservice.model.ScriptLanguage;
import onekr.commonservice.model.ScriptType;
import onekr.framework.exception.AppException;
import onekr.framework.exception.ErrorCode;
import onekr.framework.result.Result;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class ScriptBizImpl implements ScriptBiz {
	
	@Autowired
	private ScriptDao scriptDao;
	@Autowired
	@Qualifier("ArticleScriptHandler")
	private ScriptHandler articleScriptHandler;
	@Autowired
	@Qualifier("UrlScriptHandler")
	private ScriptHandler urlScriptHandler;
	
	@Override
	public Result exceuteScript(Long scriptId, Object[] args) {
		Result result = new Result();
		Script script = scriptDao.findOne(scriptId);
		if (ScriptLanguage.GROOVY.ordinal() != script.getScriptLanguage().ordinal()) {
			throw new AppException(ErrorCode.UN_SUPPORTED);
		}
		if (ScriptType.ARTICLE.ordinal() == script.getScriptType().ordinal()) {
			return articleScriptHandler.handle(script, args);
		} else if (ScriptType.URL.ordinal() == script.getScriptType().ordinal()) {
			return urlScriptHandler.handle(script, args);
		}
		result.setCode(ErrorCode.SERVER_ERROR);
		return result;
	}
	
	@Override
	public void saveScript(Script as) {
		scriptDao.save(as);
	}

	@Override
	public List<Script> listScriptAll() {
		return scriptDao.findAll();
	}

	@Override
	public Script findScriptById(Long scriptId) {
		return scriptDao.findOne(scriptId);
	}
	
	@Override
	public List<Script> listScriptByType(Collection<ScriptType> types) {
		return scriptDao.findByScriptTypeIn(types);
	}
	
	@Override
	public void deleteScript(Collection<Long> ids) {
		for (Long id : ids) {
			scriptDao.delete(id);
		}
	}
	
}
