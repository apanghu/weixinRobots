package weixin.ooolo.net.utils;

import static org.junit.Assert.*;

public class Test {

	@org.junit.Test
	public void test() throws Exception {
		UploadPicToWx uploadPicToWx=new UploadPicToWx();
		uploadPicToWx.getMediaIdFromUrl("http://img1.juimg.com/170303/330474-1F3030I21924.jpg", ".jpg");
	}

}
