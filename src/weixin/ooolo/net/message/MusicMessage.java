package weixin.ooolo.net.message;

import weixin.ooolo.net.entity.MusicBase;

public class MusicMessage  extends BaseMessage{
    private MusicBase Music;

	public MusicBase getMusic() {
		return Music;
	}

	public void setMusic(MusicBase music) {
		Music = music;
	}
    
}
