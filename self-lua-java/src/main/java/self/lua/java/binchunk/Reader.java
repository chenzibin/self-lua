package self.lua.java.binchunk;

import java.util.Arrays;
import java.util.stream.IntStream;

public class Reader {

    private byte[] data;
    private int offset;

    public Reader(byte[] data) {
        this.data = data;
    }

    public byte readByte() {
        byte b = data[offset];
        offset += 1;
        return b;
    }

    public int readUint32() {
        int i = IntStream.range(0, 4).map(index -> Byte.toUnsignedInt(data[index + offset]) << (8 * index)).sum();
        offset += 4;
        return i;
    }

    public long readUint64() {
        int little = readUint32();
        long big = (long) readUint32() << 32;
        return little + big;
    }

    public long readLuaInteger() {
        long i = IntStream.range(0, 8).map(index -> data[index + offset] << (8 * index)).sum();
        offset += 8;
        return i;
    }

    public double readLuaNumber() {
        double i = Double.longBitsToDouble(readLuaInteger());
        offset += 8;
        return i;
    }

    public String readString() {
        long size = readByte();
        if (size == 0) {
            return "";
        }
        if (size == 0xFF) {
            size = readLuaInteger();
        }

        return new String(readBytes(size - 1));
    }

    public byte[] readBytes(int length) {
        byte[] bytes = Arrays.copyOfRange(data, offset, offset + length);
        offset += length;
        return bytes;
    }

    public void checkHeader() {

    }
}
