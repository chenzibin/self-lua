package self.lua.java.api;

import lombok.Builder;
import lombok.NoArgsConstructor;
import self.lua.java.state.LuaStack;

import java.util.List;

@Builder
@NoArgsConstructor
public class LuaState implements ApiStack {

    protected LuaStack stack;

    LuaState newLuaState() {
        return LuaState.builder()
                .stack(LuaStack.newLuaStack(20))
                .build();
    }

    @Override
    public int getTop() {
        return 0;
    }

    @Override
    public int absIndex(int index) {
        return 0;
    }

    @Override
    public boolean checkStack(int n) {
        return false;
    }

    @Override
    public void pop(int n) {

    }

    @Override
    public void copy(int fromIndex, int toIndex) {

    }

    @Override
    public void pushValue(int index) {

    }

    @Override
    public void replace(int index) {

    }

    @Override
    public void insert(int index) {

    }

    @Override
    public void remove(int index) {

    }

    @Override
    public void rotate(int index, int n) {

    }

    @Override
    public void setTop(int index) {

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
