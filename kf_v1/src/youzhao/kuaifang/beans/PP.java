package youzhao.kuaifang.beans;

public class PP {
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
		private String URL;
		private String PC;
		private String ZID;
		private String TL;
		public String getURL() {
			return URL;
		}
		public void setURL(String uRL) {
			URL = uRL;
		}
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
		public String getTL() {
			return TL;
		}
		public void setTL(String tL) {
			TL = tL;
		}
		
		
	} 
}
