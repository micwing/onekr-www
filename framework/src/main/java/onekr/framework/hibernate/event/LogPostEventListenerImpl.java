package onekr.framework.hibernate.event;

import org.hibernate.event.spi.PostDeleteEvent;
import org.hibernate.event.spi.PostInsertEvent;
import org.hibernate.event.spi.PostUpdateEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogPostEventListenerImpl implements PostEventListener {

	private static final long serialVersionUID = -5137329318982940536L;
	
	private static final Logger logger = LoggerFactory
			.getLogger(LogPostEventListenerImpl.class);

	public void onPostInsert(PostInsertEvent event) {
		logger.info("type:"+event.getClass().getSimpleName()+" ID:"+event.getId()+" insert.");
	}

	public void onPostUpdate(PostUpdateEvent event) {
		logger.info("type:"+event.getClass().getSimpleName()+" ID:"+event.getId()+" update.");
	}

	public void onPostDelete(PostDeleteEvent event) {
		logger.info("type:"+event.getClass().getSimpleName()+" ID:"+event.getId()+" delete.");
	}
}
