package onekr.biz.article.intf;

import java.util.Collection;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Min;

import onekr.biz.model.Script;
import onekr.biz.model.ScriptType;
import onekr.framework.result.Result;

import org.springframework.validation.annotation.Validated;

@Validated
public interface ScriptBiz {

	Result exceuteScript(Long scriptId, Object[] args);
	
	void saveScript(@Valid Script as);
	
	List<Script> listScriptAll();
	
	Script findScriptById(@Min(1) Long scriptId);
	
	List<Script> listScriptByType(Collection<ScriptType> types);
	
	void deleteScript(Collection<Long> ids);
}
