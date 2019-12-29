package self.lua.java.binchunk;

import java.util.Arrays;
import java.util.stream.IntStream;

import static self.lua.java.binchunk.BinaryChunk.*;

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
        long i = IntStream.range(0, 8).mapToLong(index -> ((long) data[index + offset]) << (8 * index)).sum();
        offset += 8;
        return i;
    }

    public double readLuaNumber() {
        double i = Double.longBitsToDouble(readLuaInteger());
        return i;
    }

    public String readString() {
        int size = readByte();
        if (size == 0) {
            return "";
        }
        if (size == 0xFF) {
            size = (int) readUint64();
        }

        return new String(readBytes(size - 1));
    }

    public byte[] readBytes(int length) {
        byte[] bytes = Arrays.copyOfRange(data, offset, offset + length);
        offset += length;
        return bytes;
    }

    public String readByteString(int length) {
        byte[] bytes = readBytes(length);
        return new String(bytes);
    }

    public String bytesToString(byte[] bytes) {
        return new String(bytes);
    }

    public Header checkHeader() {
        Header header = Header.builder()
                .signature(readBytes(4))
                .version(readByte())
                .format(readByte())
                .luacData(readBytes(6))
                .cintSize(readByte())
                .sizetSize(readByte())
                .instructionSize(readByte())
                .luaIntegerSize(readByte())
                .luaNumberSize(readByte())
                .luacInt(readLuaInteger())
                .luacNum(readLuaNumber())
                .build();

        if (!bytesToString(header.getSignature()).equals(bytesToString(LUA_SIGNATURE))) {
            panic("not a precompiled chunk!");
        }

        if (header.getVersion() != LUAC_VERSION) {
            panic("version mismatch!");
        }

        if (header.getFormat() != LUAC_FORMAT) {
            panic("format mismatch!");
        }

        if (!bytesToString(header.getLuacData()).equals(bytesToString(LUAC_DATA))) {
            panic("corrupted!");
        }

        if (header.getCintSize() != CINT_SIZE) {
            panic("int size mismatch!");
        }

        if (header.getSizetSize() != CSIZET_SIZE) {
            panic("size_t size mismatch!");
        }

        if (header.getInstructionSize() != INSTRUCTION_SIZE) {
            panic("instruction size mismatch!");
        }

        if (header.getLuaIntegerSize() != LUA_INTEGER_SIZE) {
            panic("lua integer size mismatch!");
        }

        if (header.getLuaNumberSize() != LUA_NUMBER_SIZE) {
            panic("lua number size mismatch!");
        }

        if (header.getLuacInt() != LUAC_INT) {
            panic("endianness mismatch!");
        }

        if (header.getLuacNum() != LUAC_NUM) {
            panic("float format mismatch!");
        }

        return header;
    }

    public Prototype readProto(String parentSource) {
        String source = readString();
        if ("".equals(source)) {
            source = parentSource;
        }
        return Prototype.builder()
                .source(source)
                .lineDefined(readUint32())
                .lastLineDefined(readUint32())
                .numParams(readByte())
                .isVararg(readByte())
                .maxStackSize(readByte())
                .code(readCode())
                .constants(readConstants())
                .upvalues(readUpvalues())
                .protos(readProtos(source))
                .lineInfo(readLineInfo())
                .locVars(readLocVars())
                .upvalueNames(readUpvalueNames())
                .build();
    }

    public Integer[] readCode() {
        int size = readUint32();
        return IntStream.range(0, size).mapToObj(i -> readUint32()).toArray(Integer[]::new);
    }

    public Object[] readConstants() {
        int size = readUint32();
        return IntStream.range(0, size).mapToObj(i -> readConstant()).toArray();
    }

    public Object readConstant() {
        byte tag = readByte();
        switch (tag) {
            case TAG_NIL:
                return "nil";
            case TAG_BOOLEAN:
                return readByte() != 0;
            case TAG_INTEGER:
                return readLuaInteger();
            case TAG_NUMBER:
                return readLuaNumber();
            case TAG_SHORT_STR:
                return readString();
            case TAG_LONG_STR:
                return readString();
            default:
                panic("corrupted!");
                return null;
        }
    }

    private Prototype.Upvalue[] readUpvalues() {
        int size = readUint32();
        return IntStream.range(0, size)
                .mapToObj(i -> new Prototype.Upvalue(readByte(), readByte()))
                .toArray(Prototype.Upvalue[]::new);
    }

    private Prototype[] readProtos(String parentSource) {
        int size = readUint32();
        return IntStream.range(0, size)
                .mapToObj(i -> readProto(parentSource))
                .toArray(Prototype[]::new);
    }

    private Integer[] readLineInfo() {
        int size = readUint32();
        return IntStream.range(0, size).mapToObj(i -> readUint32()).toArray(Integer[]::new);
    }

    private Prototype.LocVar[] readLocVars() {
        int size = readUint32();
        return IntStream.range(0, size)
                .mapToObj(i -> new Prototype.LocVar(readString(), readUint32(), readUint32()))
                .toArray(Prototype.LocVar[]::new);
    }

    private String[] readUpvalueNames() {
        int size = readUint32();
        return IntStream.range(0, size)
                .mapToObj(i -> readString())
                .toArray(String[]::new);
    }

    private void panic(String message) {
        throw new RuntimeException(message);
    }
}
