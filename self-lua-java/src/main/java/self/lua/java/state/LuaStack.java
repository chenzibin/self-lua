package self.lua.java.state;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LuaStack {

    private LuaValue[] slots;
    private int top;

    public static LuaStack newLuaStack(int size) {
        return LuaStack.builder()
                .slots(new LuaValue[size])
                .top(0)
                .build();
    }

    /**
     * 检查栈空闲空间是否能容纳n个值，若栈空间不足，则进行扩容
     */
    public void check(int n) {
        int free = slots.length - top;
        if (free < n) {
            int newSize = slots.length + (n - free);
            LuaValue[] newSlots = new LuaValue[newSize];
            System.arraycopy(slots, 0, newSlots, 0, top);
            slots = newSlots;
        }
    }

    /**
     * 将值推入栈顶
     */
    public void push(LuaValue value) {
        if (top == slots.length) {
            throw new RuntimeException("stack overflow!");
        }
        slots[top] = value;
        top++;
    }

    /**
     * 从栈顶弹出一个值
     */
    public LuaValue pop() {
        if (top < 1) {
            throw new RuntimeException("stack underflow!");
        }
        top--;
        LuaValue value = slots[top];
        slots[top] = null;
        return value;
    }

    /**
     * 将索引转换成绝对索引
     */
    public int absIndex(int index) {
        if (index > 0) {
            return index;
        }

        return index + top + 1;
    }

    /**
     * 判断索引是否有效
     */
    public boolean isValid(int index) {
        int absIndex = absIndex(index);
        return absIndex > 0 && absIndex <= top;
    }

    /**
     * 根据索引从栈里取值
     */
    public LuaValue get(int index) {
        int absIndex = absIndex(index);
        if (absIndex > 0 && absIndex <= top) {
            return slots[absIndex - 1];
        }
        return null;
    }

    /**
     * 根据索引往栈里写入值
     */
    public void set(int index, LuaValue value) {
        int absIndex = absIndex(index);
        if (absIndex > 0 && absIndex <= top) {
            slots[absIndex - 1] = value;
            return;
        }
        throw new RuntimeException("invalid index!");
    }

    /**
     * 反转
     */
    public void reverse(int fromIndex, int toIndex) {
        int from = fromIndex - 1;
        int to = toIndex - 1;
        for (; from < to; from++, to--) {
            LuaValue remain = slots[from];
            slots[from] = slots[to];
            slots[to] = remain;
        }
    }
}
