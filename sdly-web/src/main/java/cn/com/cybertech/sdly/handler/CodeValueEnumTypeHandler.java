package cn.com.cybertech.sdly.handler;

import cn.com.cybertech.sdly.enums.BaseCodeValueEnum;
import cn.com.cybertech.sdly.utils.EnumUtils;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by huangkd on 2019/2/26.
 */
public class CodeValueEnumTypeHandler<E extends Enum<?> & BaseCodeValueEnum> extends BaseTypeHandler<BaseCodeValueEnum> {

    private Class<E> type;

    public CodeValueEnumTypeHandler(Class<E> type){
        if(type==null){
            throw new IllegalArgumentException("type cannot be null");
        }
        this.type=type;
    }

    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, BaseCodeValueEnum baseCodeValueEnum, JdbcType jdbcType) throws SQLException {
        preparedStatement.setInt(i,baseCodeValueEnum.getCode());
    }

    @Override
    public BaseCodeValueEnum getNullableResult(ResultSet resultSet, String s) throws SQLException {
        int code=resultSet.getInt(s);
        return resultSet.wasNull()?null:codeOf(code);
    }

    @Override
    public BaseCodeValueEnum getNullableResult(ResultSet resultSet, int i) throws SQLException {
        int code=resultSet.getInt(i);
        return resultSet.wasNull()?null:codeOf(code);
    }

    @Override
    public BaseCodeValueEnum getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        int code=callableStatement.getInt(i);
        return callableStatement.wasNull()?null:codeOf(code);
    }

    private E codeOf(int code){
        try {
            return EnumUtils.codeOf(type, code);
        } catch (Exception ex) {
            throw new IllegalArgumentException("Cannot convert " + code + " to " + type.getSimpleName() + " by code value.", ex);
        }
    }
}
