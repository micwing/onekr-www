package onekr.test;

import onekr.commonservice.common.intf.CommentBiz;
import onekr.commonservice.model.Comment;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:/applicationContext-test.xml")
@ActiveProfiles("test")
public class MainTest {
	@Autowired
	private CommentBiz commentBiz;
	
	@Test
	public void createUser() {
		Comment c = commentBiz.findById(1L);
		System.out.println(c.getContent());
	}
	
	
}
