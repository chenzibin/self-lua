package self.lua.java.state;

import self.lua.java.api.LuaState;

import java.util.List;
import java.util.stream.IntStream;

public class ApiStackImpl extends LuaState {

    @Override
    public int getTop() {
        return stack.getTop();
    }

    @Override
    public int absIndex(int index) {
        return stack.absIndex(index);
    }

    @Override
    public boolean checkStack(int n) {
        stack.check(n);
        return true;
    }

    @Override
    public void pop(int n) {
        IntStream.range(0, n).forEach(i -> stack.pop());
    }

    @Override
    public void copy(int fromIndex, int toIndex) {
        LuaValue value = stack.get(fromIndex);
        stack.set(4, value);
    }

    @Override
    public void pushValue(int index) {
        LuaValue value = stack.get(index);
        stack.push(value);
    }

    @Override
    public void replace(int index) {
        LuaValue value = stack.pop();
        stack.set(index, value);
    }

    @Override
    public void insert(int index) {
        rotate(index, 1);
    }

    @Override
    public void remove(int index) {
        rotate(index, -1);
        stack.pop();
    }

    @Override
    public void rotate(int index, int n) {
        int maxIndex = stack.getTop();

        int absIndex = stack.absIndex(index);

        int maxIndexMoveTo;
        if (n > 0) {
            maxIndexMoveTo = absIndex + n - 1;
        } else {
            maxIndexMoveTo = maxIndex + n;
        }

        // 全部翻转
        stack.reverse(absIndex, maxIndex);
        // 按移动位数分割翻转
        stack.reverse(absIndex, maxIndexMoveTo);
        stack.reverse(maxIndexMoveTo + 1, maxIndex);
    }

    @Override
    public void setTop(int index) {
        int absIndex = absIndex(index);
        if (absIndex < 0) {
            throw new RuntimeException("stack underflow!");
        }

        if (stack.getTop() >= absIndex) {
            stack.setTop(absIndex);
        } else {
            IntStream.range(stack.getTop(), absIndex).forEach(i -> stack.push(null));
        }
    }

    @Override
    public String typeName(LuaType tp) {
        return null;
    }

    @Override
    public LuaType type(int index) {
        return null;
    }

    @Override
    public boolean isNone(int index) {
        return false;
    }

    @Override
    public boolean isNil(int index) {
        return false;
    }

    @Override
    public boolean isNoneOrNil(int index) {
        return false;
    }

    @Override
    public boolean isBoolean(int index) {
        return false;
    }

    @Override
    public boolean isInteger(int index) {
        return false;
    }

    @Override
    public boolean isNumber(int index) {
        return false;
    }

    @Override
    public boolean isString(int index) {
        return false;
    }

    @Override
    public boolean toBoolean(int index) {
        return false;
    }

    @Override
    public long toInteger(int index) {
        return 0;
    }

    @Override
    public List<Object> toIntegerX(int index) {
        return null;
    }

    @Override
    public double toNumber(int index) {
        return 0;
    }

    @Override
    public List<Object> toNumberX(int index) {
        return null;
    }

    @Override
    public String toString(int index) {
        return null;
    }

    @Override
    public List<Object> toStringX(int index) {
        return null;
    }

    @Override
    public void pushNil() {

    }

    @Override
    public void pushBoolean(boolean b) {

    }

    @Override
    public void pushInteger(long n) {

    }

    @Override
    public void pushNumber(double n) {

    }

    @Override
    public void pushString(String s) {

    }
}
