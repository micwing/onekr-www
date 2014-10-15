package onekr.commonservice.event;

import onekr.commonservice.model.Script;

public interface EventHandleProvider {

	EventHandle getEventHandle(Script script);
}
