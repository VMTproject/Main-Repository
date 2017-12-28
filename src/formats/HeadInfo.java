package formats;

public class HeadInfo {
	public String title;	public float[] titleXY;
	public String subtitle;	public float[] subtitleXY;
	public String poet;		public float[] poetXY;
	public String composer;	public float[] composerXY;
	public String key;		public float[] keyXY;
	public byte beat;		public float[] beatXY;
	public int tempo;		public float[] tempoXY;
	
	public HeadInfo() {
		this.title = "";
		this.titleXY = new float[]{0 , 0};
		this.subtitle = "";
		this.subtitleXY = new float[]{0 , 0};
		this.poet = "";
		this.poetXY = new float[]{0 , 0};
		this.composer = "";
		this.key = "";
		this.keyXY = new float[]{0 , 0};;
		this.beat = 0x00;
		this.beatXY = new float[]{0 , 0};;
		this.tempo = 0;
		this.tempoXY = new float[]{0 , 0};;
	}
	
	public void setTitle(String text, double x, double y) {
		this.title = text;
		this.titleXY = new float[]{(float)x, (float)y};
	}
	
	public void setSubtitle(String text, double x, double y) {
		this.subtitle = text;
		this.subtitleXY = new float[]{(float)x, (float)y};
	}
	
	public void setPoet(String text, double x, double y) {
		this.poet = text + " ´Ê";
		this.poetXY = new float[]{(float)x, (float)y};
	}
	
	public void setComposer(String text, double x, double y) {
		this.composer = text + " Çú";
		this.composerXY = new float[]{(float)x, (float)y};
	}
	
	public void setKey(String text, double x, double y) {
		text = text.replace('b', '\u266D');
		text = text.replace('#', '\u266F');
		this.key = "1£½" + text;
		this.keyXY = new float[]{(float)x, (float)y};
	}
	
	public void setBeat(String numerator, String denominator, double x, double y) {
		this.beat = (byte)(Integer.parseInt(numerator) * 4);
		switch(denominator) {
		case "2": this.beat += 0x01;
		break;
		case "4": this.beat += 0x02;
		break;
		case "8": this.beat += 0x03;
		break;
		}
		this.beatXY = new float[]{(float)x, (float)y};		
	}
	
	public void setTempo(String text, double x, double y) {
		this.tempo = Integer.parseInt(text);
		this.tempoXY = new float[]{(float)x, (float)y};
	}
	
	public double getNoteStart(double headSize, double noteSize) {
		double res;
		double constant = headSize * 0.25 + noteSize * 0.75 + 2;
		if(this.composer.length() > 0 || this.tempo > 0) {
			res = Math.min(this.composerXY[1], this.tempoXY[1]);
			res -= constant;
			return res;
		}
		if(this.poet.length() > 0 || this.key.length() > 0 || this.beat > 0) {
			res = Math.min(this.poetXY[1], Math.min(this.keyXY[1], this.beatXY[1]));
			res -= constant;
			return res;
		}
		if(this.subtitle.length() > 0) {
			res = this.subtitleXY[1];
			res -= constant;
			return res;
		}
		if(this.title.length() > 0) {
			res = this.titleXY[1];
			res -= constant;
			return res;
		}
		return 0;
	}
}
