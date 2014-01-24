package onekr.biz.event;

import onekr.biz.model.Script;

public interface EventHandleProvider {

	EventHandle getEventHandle(Script script);
}
