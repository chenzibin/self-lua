package self.lua.java.api;

import java.util.List;

public interface ApiStack {

    int getTop();

    int absIndex(int index);

    boolean checkStack(int n);

    void pop(int n);

    void copy(int fromIndex, int toIndex);

    void pushValue(int index);

    void replace(int index);

    void insert(int index);

    void remove(int index);

    void rotate(int index, int n);

    void setTop(int index);

    String typeName(LuaType tp);

    LuaType type(int index);

    boolean isNone(int index);

    boolean isNil(int index);

    boolean isNoneOrNil(int index);

    boolean isBoolean(int index);

    boolean isInteger(int index);

    boolean isNumber(int index);

    boolean isString(int index);

    boolean toBoolean(int index);

    long toInteger(int index);

    List<Object> toIntegerX(int index);

    double toNumber(int index);

    List<Object> toNumberX(int index);

    String toString(int index);

    List<Object> toStringX(int index);

    void pushNil();

    void pushBoolean(boolean b);

    void pushInteger(long n);

    void pushNumber(double n);

    void pushString(String s);
}
