package onekr.framework.hibernate.event;

import org.hibernate.event.spi.PostDeleteEventListener;
import org.hibernate.event.spi.PostInsertEventListener;
import org.hibernate.event.spi.PostUpdateEventListener;

public interface PostEventListener extends PostInsertEventListener,
		PostUpdateEventListener, PostDeleteEventListener {

	
}
