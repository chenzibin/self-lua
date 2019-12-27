package self.lua.java.vm;

import lombok.Getter;

@Getter
public enum Opcode {

    MOVE(       (byte) 0, (byte) 1, Opcode.OpArgR, Opcode.OpArgN, Opcode.IABC, (byte) 0),
    LOADK(      (byte) 0, (byte) 1, Opcode.OpArgK, Opcode.OpArgN, Opcode.IABx, (byte) 1),
    LOADKX(     (byte) 0, (byte) 1, Opcode.OpArgN, Opcode.OpArgN, Opcode.IABx, (byte) 2),
    LOADBOOL(   (byte) 0, (byte) 1, Opcode.OpArgU, Opcode.OpArgU, Opcode.IABC, (byte) 3),
    LOADNIL(    (byte) 0, (byte) 1, Opcode.OpArgU, Opcode.OpArgN, Opcode.IABC, (byte) 4),
    GETUPVAL(   (byte) 0, (byte) 1, Opcode.OpArgU, Opcode.OpArgN, Opcode.IABC, (byte) 5),
    GETTABUP(   (byte) 0, (byte) 1, Opcode.OpArgU, Opcode.OpArgK, Opcode.IABC, (byte) 6),
    GETTABLE(   (byte) 0, (byte) 1, Opcode.OpArgR, Opcode.OpArgK, Opcode.IABC, (byte) 7),
    SETTABUP(   (byte) 0, (byte) 0, Opcode.OpArgK, Opcode.OpArgK, Opcode.IABC, (byte) 8),
    SETUPVAL(   (byte) 0, (byte) 0, Opcode.OpArgU, Opcode.OpArgN, Opcode.IABC, (byte) 9),
    SETTABLE(   (byte) 0, (byte) 0, Opcode.OpArgK, Opcode.OpArgK, Opcode.IABC, (byte) 10),
    NEWTABLE(   (byte) 0, (byte) 1, Opcode.OpArgU, Opcode.OpArgU, Opcode.IABC, (byte) 11),
    SELF(       (byte) 0, (byte) 1, Opcode.OpArgK, Opcode.OpArgK, Opcode.IABC, (byte) 12),
    ADD(        (byte) 0, (byte) 1, Opcode.OpArgK, Opcode.OpArgK, Opcode.IABC, (byte) 13),
    SUB(        (byte) 0, (byte) 1, Opcode.OpArgK, Opcode.OpArgK, Opcode.IABC, (byte) 14),
    MUL(        (byte) 0, (byte) 1, Opcode.OpArgK, Opcode.OpArgK, Opcode.IABC, (byte) 15),
    MOD(        (byte) 0, (byte) 1, Opcode.OpArgK, Opcode.OpArgK, Opcode.IABC, (byte) 16),
    POW(        (byte) 0, (byte) 1, Opcode.OpArgK, Opcode.OpArgK, Opcode.IABC, (byte) 17),
    DIV(        (byte) 0, (byte) 1, Opcode.OpArgK, Opcode.OpArgK, Opcode.IABC, (byte) 18),
    IDIV(       (byte) 0, (byte) 1, Opcode.OpArgK, Opcode.OpArgK, Opcode.IABC, (byte) 19),
    BAND(       (byte) 0, (byte) 1, Opcode.OpArgK, Opcode.OpArgK, Opcode.IABC, (byte) 20),
    BOR(        (byte) 0, (byte) 1, Opcode.OpArgK, Opcode.OpArgK, Opcode.IABC, (byte) 21),
    BXOR(       (byte) 0, (byte) 1, Opcode.OpArgK, Opcode.OpArgK, Opcode.IABC, (byte) 22),
    SHL(        (byte) 0, (byte) 1, Opcode.OpArgK, Opcode.OpArgK, Opcode.IABC, (byte) 23),
    SHR(        (byte) 0, (byte) 1, Opcode.OpArgK, Opcode.OpArgK, Opcode.IABC, (byte) 24),
    UNM(        (byte) 0, (byte) 1, Opcode.OpArgR, Opcode.OpArgN, Opcode.IABC, (byte) 25),
    BNOT(       (byte) 0, (byte) 1, Opcode.OpArgR, Opcode.OpArgN, Opcode.IABC, (byte) 26),
    NOT(        (byte) 0, (byte) 1, Opcode.OpArgR, Opcode.OpArgN, Opcode.IABC, (byte) 27),
    LEN(        (byte) 0, (byte) 1, Opcode.OpArgR, Opcode.OpArgN, Opcode.IABC, (byte) 28),
    CONCAT(     (byte) 0, (byte) 1, Opcode.OpArgR, Opcode.OpArgR, Opcode.IABC, (byte) 29),
    JMP(        (byte) 0, (byte) 0, Opcode.OpArgR, Opcode.OpArgN, Opcode.IAsBx, (byte) 30),
    EQ(         (byte) 1, (byte) 0, Opcode.OpArgK, Opcode.OpArgK, Opcode.IABC, (byte) 31),
    LT(         (byte) 1, (byte) 0, Opcode.OpArgK, Opcode.OpArgK, Opcode.IABC, (byte) 32),
    LE(         (byte) 1, (byte) 0, Opcode.OpArgK, Opcode.OpArgK, Opcode.IABC, (byte) 33),
    TEST(       (byte) 1, (byte) 0, Opcode.OpArgN, Opcode.OpArgU, Opcode.IABC, (byte) 34),
    TESTSET(    (byte) 1, (byte) 1, Opcode.OpArgR, Opcode.OpArgU, Opcode.IABC, (byte) 35),
    CALL(       (byte) 0, (byte) 1, Opcode.OpArgU, Opcode.OpArgU, Opcode.IABC, (byte) 36),
    TAILCALL(   (byte) 0, (byte) 1, Opcode.OpArgU, Opcode.OpArgU, Opcode.IABC, (byte) 37),
    RETURN(     (byte) 0, (byte) 0, Opcode.OpArgU, Opcode.OpArgN, Opcode.IABC, (byte) 38),
    FORLOOP(    (byte) 0, (byte) 1, Opcode.OpArgR, Opcode.OpArgN, Opcode.IAsBx, (byte) 39),
    FORPREP(    (byte) 0, (byte) 1, Opcode.OpArgR, Opcode.OpArgN, Opcode.IAsBx, (byte) 40),
    TFORCALL(   (byte) 0, (byte) 0, Opcode.OpArgN, Opcode.OpArgU, Opcode.IABC, (byte) 41),
    TFORLOOP(   (byte) 0, (byte) 1, Opcode.OpArgR, Opcode.OpArgN, Opcode.IAsBx, (byte) 42),
    SETLIST(    (byte) 0, (byte) 0, Opcode.OpArgU, Opcode.OpArgU, Opcode.IABC, (byte) 43),
    CLOSURE(    (byte) 0, (byte) 1, Opcode.OpArgU, Opcode.OpArgN, Opcode.IABx, (byte) 44),
    VARARG(     (byte) 0, (byte) 1, Opcode.OpArgU, Opcode.OpArgN, Opcode.IABC, (byte) 45),
    EXTRAARG(   (byte) 0, (byte) 0, Opcode.OpArgU, Opcode.OpArgU, Opcode.IAx, (byte) 46);

    /**
     * 编码模式
     */
    public static final byte IABC   = 0;
    public static final byte IABx   = 1;
    public static final byte IAsBx  = 2;
    public static final byte IAx    = 3;

    /**
     * 操作码
     */
    public static final byte OP_MOVE     = 0;
    public static final byte OP_LOADK    = 1;
    public static final byte OP_LOADKX   = 2;
    public static final byte OP_LOADBOOL = 3;
    public static final byte OP_LOADNIL  = 4;
    public static final byte OP_GETUPVAL = 5;
    public static final byte OP_GETTABUP = 6;
    public static final byte OP_GETTABLE = 7;
    public static final byte OP_SETTABUP = 8;
    public static final byte OP_SETUPVAL = 9;
    public static final byte OP_SETTABLE = 10;
    public static final byte OP_NEWTABLE = 11;
    public static final byte OP_SELF     = 12;
    public static final byte OP_ADD      = 13;
    public static final byte OP_SUB      = 14;
    public static final byte OP_MUL      = 15;
    public static final byte OP_MOD      = 16;
    public static final byte OP_POW      = 17;
    public static final byte OP_DIV      = 18;
    public static final byte OP_IDIV     = 19;
    public static final byte OP_BAND     = 20;
    public static final byte OP_BOR      = 21;
    public static final byte OP_BXOR     = 22;
    public static final byte OP_SHL      = 23;
    public static final byte OP_SHR      = 24;
    public static final byte OP_UNM      = 25;
    public static final byte OP_BNOT     = 26;
    public static final byte OP_NOT      = 27;
    public static final byte OP_LEN      = 28;
    public static final byte OP_CONCAT   = 29;
    public static final byte OP_JMP      = 30;
    public static final byte OP_EQ       = 31;
    public static final byte OP_LT       = 32;
    public static final byte OP_LE       = 33;
    public static final byte OP_TEST     = 34;
    public static final byte OP_TESTSET  = 35;
    public static final byte OP_CALL     = 36;
    public static final byte OP_TAILCALL = 37;
    public static final byte OP_RETURN   = 38;
    public static final byte OP_FORLOOP  = 39;
    public static final byte OP_FORPREP  = 40;
    public static final byte OP_TFORCALL = 41;
    public static final byte OP_TFORLOOP = 42;
    public static final byte OP_SETLIST  = 43;
    public static final byte OP_CLOSURE  = 44;
    public static final byte OP_VARARG   = 45;
    public static final byte OP_EXTRAARG = 46;

    /**
     * 操作数类型
     */
    /* not used */
    public static final byte OpArgN = 0;
    /* used */
    public static final byte OpArgU = 1;
    /* register or a jump offset */
    public static final byte OpArgR = 2;
    /* constant or register/constant */
    public static final byte OpArgK = 3;


    /* operator is a test, next instruction must be a jump */
    private byte testFlag;

    /* instruction set register A */
    private byte setAFlag;

    /* B arg mode */
    private byte argBMode;

    /* C arg mode */
    private byte argCMode;

    /* op mode */
    private byte opMode;

    private byte opCode;

    Opcode(byte testFlag, byte setAFlag, byte argBMode, byte argCMode, byte opMode, byte opCode) {
        this.testFlag = testFlag;
        this.setAFlag = setAFlag;
        this.argBMode = argBMode;
        this.argCMode = argCMode;
        this.opMode = opMode;
        this.opCode = opCode;
    }

}
