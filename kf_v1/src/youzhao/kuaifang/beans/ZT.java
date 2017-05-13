package youzhao.kuaifang.beans;

public class ZT {
	private String TL;
	private MM[] MM;
	private String URL;
	public String getTL() {
		return TL;
	}
	public void setTL(String tL) {
		TL = tL;
	}
	public MM[] getMM() {
		return MM;
	}
	public void setMM(MM[] mM) {
		MM = mM;
	}
	public String getURL() {
		return URL;
	}
	public void setURL(String uRL) {
		URL = uRL;
	}
	
	public class MM{
		private String PC;
		private String ZID;
		private String URL;
		public String getPC() {
			return PC;
		}
		public void setPC(String pC) {
			PC = pC;
		}
		public String getZID() {
			return ZID;
		}
		public void setZID(String zID) {
			ZID = zID;
		}
		public String getURL() {
			return URL;
		}
		public void setURL(String uRL) {
			URL = uRL;
		}
		
	}
}
