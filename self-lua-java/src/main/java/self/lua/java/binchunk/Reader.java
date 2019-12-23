package self.lua.java.binchunk;

import java.util.Arrays;

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
        int i = Byte.toUnsignedInt(data[offset]);
        i += Byte.toUnsignedInt(data[1 + offset]) << 8;
        i += Byte.toUnsignedInt(data[2 + offset]) << 16;
        i += Byte.toUnsignedInt(data[3 + offset]) << 24;
        offset += 4;
        return i;
    }

    public long readUint64() {
        int little = readUint32();
        long big = (long) readUint32() << 32;
        return little + big;
    }

    public void readLuaInteger() {

    }

    public void readLuaNumber() {

    }

    public void readString() {

    }

    public byte[] readBytes(int length) {
        byte[] bytes = Arrays.copyOfRange(data, offset, offset + length);
        offset += length;
        return bytes;
    }

    public void checkHeader() {

    }
}
