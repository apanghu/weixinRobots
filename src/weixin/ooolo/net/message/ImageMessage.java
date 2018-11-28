package weixin.ooolo.net.message;

import weixin.ooolo.net.entity.ImageBase;

public class ImageMessage extends BaseMessage{
    private ImageBase Image ;

	public ImageBase getImage() {
		return Image;
	}

	public void setImage(ImageBase image) {
		Image = image;
	}
   
    
}
