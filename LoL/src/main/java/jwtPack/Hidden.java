package jwtPack;

public class Hidden {
	private String id = null;

	public Hidden(String id) {
		super();
		this.id = id;
	}

	public String hexString(String id) {
		StringBuffer ostr = new StringBuffer();
		for (int i = 0; i < id.length(); i++) {
			char ch = id.charAt(i);

			// 유니코드로 변환할 필요가 있는 문자열인지 판단
			if ((ch >= 0x0020) && (ch <= 0x007e)) {
				ostr.append(ch); // 아닌 경우.
			} else { // 변경해야 하는 경우.
				String hex = Integer.toHexString(id.charAt(i) & 0xFFFF);
				// 16진수로 바꿔서 추가
				ostr.append(hex.toLowerCase());
			}
		}
		return (new String(ostr));
	}

	public int ch_value() {
		String hex = hexString(id);
		int value = 0;
		for (int i = 0; i < id.length(); i++) {
			value += id.charAt(i);
		}
		while (value >= 9999) {
			value /= 10;
		}
		while (value < 999) {
			value *= 7;
		}
		return value;
	}
}
