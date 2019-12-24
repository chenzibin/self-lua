package self.lua.java.binchunk;

import lombok.Builder;
import lombok.Data;

/**
 * BinaryChunk
 *
 * @author chenzb
 * @date 2019/12/17
 */
@Data
public class BinaryChunk {

    public static final byte[] LUA_SIGNATURE = {0x1b, 'L', 'u', 'a'};

    public static final byte LUAC_VERSION = 0x53;

    public static final byte LUAC_FORMAT = 0;

    public static final byte[] LUAC_DATA = {0x19, (byte) 0x93, '\r', '\n', 0x1a, '\n'};

    public static final int CINT_SIZE = 4;

    public static final int CSIZET_SIZE = 8;

    public static final int INSTRUCTION_SIZE = 4;

    public static final int LUA_INTEGER_SIZE = 8;

    public static final int LUA_NUMBER_SIZE = 8;

    public static final short LUAC_INT = 0x5678;

    public static final double LUAC_NUM = 370.5;

    public static final byte TAG_NIL = 0x00;

    public static final byte TAG_BOOLEAN = 0x01;

    public static final byte TAG_NUMBER = 0x03;

    public static final byte TAG_INTEGER = 0x13;

    public static final byte TAG_SHORT_STR = 0x04;

    public static final byte TAG_LONG_STR = 0x14;

    private Header header;

    private byte sizeUpvalues;

    private Prototype mainFunc;

    @Data
    public class Header {
        /*签名,快速校验文件格式*/
        private byte[] signature;
        /*版本号*/
        private byte version;
        /*格式号*/
        private byte format;
        /*进一步校验文件内容*/
        private byte[] luacData;
        /*c语言整型占用的字节数*/
        private byte cintSize;
        /*c语言size_t类型占用的字节数*/
        private byte sizetSize;
        /*指令类型占用的字节数*/
        private byte instructionSize;
        /*lua整型占用的字节数*/
        private byte luaIntegerSize;
        /*lua浮点型占用的字节数*/
        private byte luaNumberSize;
        /*0x5678,检测大小端*/
        private long luacInt;
        /*370.5,检测浮点数格式*/
        private double luacNum;
    }

    @Data
    @Builder
    public class Prototype {
        /*基本信息*/
        /*源文件名*/
        private String source;
        /*起始行号*/
        private int lineDefined;
        /*终止行号*/
        private int lastLineDefined;
        /*固定参数个数*/
        private byte numParams;
        /*是否是变长参数函数*/
        private byte isVararg;
        /*运行函数所必要的寄存器数量*/
        private byte maxStackSize;

        /*指令表*/
        private Integer[] code;
        /*常量表*/
        private Object[] constants;
        /*upvalue表*/
        private Upvalue[] upvalues;
        /*子函数原型表*/
        private Prototype[] protos;

        /*调试信息*/
        /*行号表*/
        private int[] lineInfo;
        /*局部变量表*/
        private LocVar[] locVars;
        /*upvalue名列表*/
        private String[] upvalueNames;

        public class Upvalue {
            private byte instack;
            private byte idx;
        }

        public class LocVar {
            private String varName;
            private int startPc;
            private int endPc;
        }
    }

    public Prototype undump(byte[] data) {
        Reader reader = new Reader(data);
        reader.checkHeader();
        reader.readByte();
        return reader.readProto();
    }

}
