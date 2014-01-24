package onekr.biz.article.dao;

import java.util.Collection;
import java.util.List;

import onekr.biz.model.Script;
import onekr.biz.model.ScriptType;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScriptDao extends JpaRepository<Script, Long> {

	List<Script> findByScriptTypeIn(Collection<ScriptType> types);
}
