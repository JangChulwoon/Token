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

			// �����ڵ�� ��ȯ�� �ʿ䰡 �ִ� ���ڿ����� �Ǵ�
			if ((ch >= 0x0020) && (ch <= 0x007e)) {
				ostr.append(ch); // �ƴ� ���.
			} else { // �����ؾ� �ϴ� ���.
				String hex = Integer.toHexString(id.charAt(i) & 0xFFFF);
				// 16������ �ٲ㼭 �߰�
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
