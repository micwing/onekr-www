package onekr.portalservice.article.dao;

import java.util.Collection;
import java.util.List;

import onekr.portalservice.model.Script;
import onekr.portalservice.model.ScriptType;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScriptDao extends JpaRepository<Script, Long> {

	List<Script> findByScriptTypeIn(Collection<ScriptType> types);
}
