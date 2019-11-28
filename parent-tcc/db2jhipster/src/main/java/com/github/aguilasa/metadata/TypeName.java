package com.github.aguilasa.metadata;

import java.sql.JDBCType;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TypeName {

	private String dataType;
	private String dataTypeName;
	private String typeName;
	private String columnSize;
	private String bufferLength;
	private String decimalDigits;
	private String numPrecRadix;

	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	public String getDataTypeName() {
		return dataTypeName;
	}

	public void setDataTypeName(String dataTypeName) {
		this.dataTypeName = dataTypeName;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getColumnSize() {
		return columnSize;
	}

	public void setColumnSize(String columnSize) {
		this.columnSize = columnSize;
	}

	public String getBufferLength() {
		return bufferLength;
	}

	public void setBufferLength(String bufferLength) {
		this.bufferLength = bufferLength;
	}

	public String getDecimalDigits() {
		return decimalDigits;
	}

	public void setDecimalDigits(String decimalDigits) {
		this.decimalDigits = decimalDigits;
	}

	public String getNumPrecRadix() {
		return numPrecRadix;
	}

	public void setNumPrecRadix(String numPrecRadix) {
		this.numPrecRadix = numPrecRadix;
	}

	private String getType(int type) {
		try {
			return JDBCType.valueOf(type).getName();
		} catch (IllegalArgumentException e) {
			return "UNKNOW";
		}
	}

	public void fromRs(ResultSet result) throws SQLException {
		dataType = result.getString("DATA_TYPE");
		dataTypeName = getType(result.getInt("DATA_TYPE"));
		typeName = result.getString("TYPE_NAME");
		columnSize = result.getString("COLUMN_SIZE");
		bufferLength = result.getString("BUFFER_LENGTH");
		decimalDigits = result.getString("DECIMAL_DIGITS");
		numPrecRadix = result.getString("NUM_PREC_RADIX");
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(dataType);
		builder.append("\t");
		builder.append(dataTypeName);
		builder.append("\t");
		builder.append(typeName);
		builder.append("\t");
		builder.append(columnSize);
		builder.append("\t");
		builder.append(bufferLength);
		builder.append("\t");
		builder.append(decimalDigits);
		builder.append("\t");
		builder.append(numPrecRadix);
		return builder.toString();
	}

}
