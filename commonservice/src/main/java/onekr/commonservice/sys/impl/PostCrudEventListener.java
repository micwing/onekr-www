package onekr.commonservice.sys.impl;

import org.hibernate.event.spi.PostDeleteEvent;
import org.hibernate.event.spi.PostDeleteEventListener;
import org.hibernate.event.spi.PostInsertEvent;
import org.hibernate.event.spi.PostInsertEventListener;
import org.hibernate.event.spi.PostUpdateEvent;
import org.hibernate.event.spi.PostUpdateEventListener;

public class PostCrudEventListener implements PostInsertEventListener,
		PostUpdateEventListener, PostDeleteEventListener {

	private static final long serialVersionUID = 4178994772658937866L;

	@Override
	public void onPostInsert(PostInsertEvent event) {
		System.out.println("insert:"+event.getEntity());
	}
	
	@Override
	public void onPostUpdate(PostUpdateEvent event) {
		System.out.println("update:"+event.getEntity());
	}
	
	@Override
	public void onPostDelete(PostDeleteEvent event) {
		System.out.println("delete:"+event.getEntity());
	}
}
