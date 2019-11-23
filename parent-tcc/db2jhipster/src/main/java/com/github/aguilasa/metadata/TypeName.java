package com.github.aguilasa.metadata;

import java.sql.JDBCType;
import java.sql.ResultSet;
import java.sql.SQLException;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class TypeName {

	private String DATA_TYPE;
	private String DATA_TYPE_NAME;
	private String TYPE_NAME;
	private String COLUMN_SIZE;
	private String BUFFER_LENGTH;
	private String DECIMAL_DIGITS;
	private String NUM_PREC_RADIX;

	private String getType(int type) {
		try {
			return JDBCType.valueOf(type).getName();
		} catch (IllegalArgumentException e) {
			return "UNKNOW";
		}
	}

	public void fromRs(ResultSet result) throws SQLException {
		DATA_TYPE = result.getString("DATA_TYPE");
		DATA_TYPE_NAME = getType(result.getInt("DATA_TYPE"));
		TYPE_NAME = result.getString("TYPE_NAME");
		COLUMN_SIZE = result.getString("COLUMN_SIZE");
		BUFFER_LENGTH = result.getString("BUFFER_LENGTH");
		DECIMAL_DIGITS = result.getString("DECIMAL_DIGITS");
		NUM_PREC_RADIX = result.getString("NUM_PREC_RADIX");
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(DATA_TYPE);
		builder.append("\t");
		builder.append(DATA_TYPE_NAME);
		builder.append("\t");
		builder.append(TYPE_NAME);
		builder.append("\t");
		builder.append(COLUMN_SIZE);
		builder.append("\t");
		builder.append(BUFFER_LENGTH);
		builder.append("\t");
		builder.append(DECIMAL_DIGITS);
		builder.append("\t");
		builder.append(NUM_PREC_RADIX);
		return builder.toString();
	}

}
