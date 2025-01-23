package org.treinamento.config;

import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.usertype.UserType;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Objects;

public class TrimmedStringTypeConfig implements UserType<String> {

  @Override
  public int getSqlType() {
    return Types.CHAR;
  }

  @Override
  public Class<String> returnedClass() {
    return String.class;
  }

  @Override
  public boolean equals(String s, String j1) {
    return Objects.equals(s, j1);
  }

  @Override
  public int hashCode(String s) {
    return s.hashCode();
  }

  @Override
  public String nullSafeGet(
      ResultSet resultSet,
      int i,
      SharedSessionContractImplementor sharedSessionContractImplementor,
      Object o)
      throws SQLException {
    String value = resultSet.getString(i);
    return value != null ? value.trim() : null;
  }

  @Override
  public void nullSafeSet(
      PreparedStatement preparedStatement,
      String s,
      int i,
      SharedSessionContractImplementor sharedSessionContractImplementor)
      throws SQLException {
    if (s != null) {
      preparedStatement.setString(i, s.trim());
    } else {
      preparedStatement.setNull(i, Types.CHAR);
    }
  }

  @Override
  public String deepCopy(String s) {
    return s;
  }

  @Override
  public boolean isMutable() {
    return false;
  }

  @Override
  public Serializable disassemble(String s) {
    return s;
  }

  @Override
  public String assemble(Serializable serializable, Object o) {
    return (String) serializable;
  }
}
