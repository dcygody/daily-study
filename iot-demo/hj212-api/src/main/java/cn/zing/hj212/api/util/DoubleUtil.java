package cn.zing.hj212.api.util;


import java.math.BigDecimal;

public class DoubleUtil {

	public static boolean isNumeric(Object str) {
		try {
			Double.parseDouble(String.valueOf(str));
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public static Double get(Object value) {
		double d = 0d;

		if (value == null) {
			return null;
		}
		if (value instanceof Double) {
			d = (Double) value;
		}
		if (value instanceof BigDecimal) {
			d = ((BigDecimal) value).doubleValue();
		}
		if (value instanceof String) {
			d = Double.parseDouble((String) value);
		}
		if (value instanceof Float) {
			d = ((Float) value).doubleValue();
		}
		if (value instanceof Integer) {
			d = ((Integer) value).doubleValue();
		}
		return d;
	}

	public static Object get(Object value, int scale) {
		Object d = 0d;
		if (value == null) {
			return null;
		}
		if (value instanceof Double) {
			if (scale == 0) {
				d = ((Double) value).intValue();
			} else {
				BigDecimal bd = new BigDecimal((Double) value);
				d = bd.setScale(scale, BigDecimal.ROUND_HALF_UP).doubleValue();
			}
			if(String.valueOf(d).endsWith(".0")) {
				d = String.valueOf(d).replace(".0", "");
			}
		}
		if (value instanceof BigDecimal) {
			if (scale == 0) {
				d = ((BigDecimal) value).intValue();
			} else {
				d = ((BigDecimal) value).setScale(scale, BigDecimal.ROUND_HALF_UP).doubleValue();
			}
			if(String.valueOf(d).endsWith(".0")) {
				d = String.valueOf(d).replace(".0", "");
			}
		}
		if (value instanceof String) {
			if (((String) value).contains("A") || ((String) value).contains("B") || ((String) value).contains("C") || ((String) value).contains("D") || ((String) value).contains("E") || ((String) value).contains("F")) {
				d = Integer.parseInt(String.valueOf(value), 16);
			} else {
				if (scale == 0) {
					d = Double.valueOf((String) value).intValue();
				} else {
					BigDecimal bd = new BigDecimal((String) value);
					d = bd.setScale(scale, BigDecimal.ROUND_HALF_UP).doubleValue();
				}
			}
			
			if(String.valueOf(d).endsWith(".0")) {
				d = String.valueOf(d).replace(".0", "");
			}
		}
		if (value instanceof Float) {
			if (scale == 0) {
				d = ((Float) value).intValue();
			} else {
				BigDecimal bd = new BigDecimal((Float) value);
				d = bd.setScale(scale, BigDecimal.ROUND_HALF_UP).doubleValue();
			}
			if(String.valueOf(d).endsWith(".0")) {
				d = String.valueOf(d).replace(".0", "");
			}
		}
		if (value instanceof Integer) {
			d = value;
		}
		return d;
	}

	public static Double null20(Double value) throws Exception {
		if (value == null) {
			return 0d;
		}
		return value;
	}
}
