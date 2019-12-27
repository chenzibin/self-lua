package self.lua.java.vm;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 *          31          24 23           16 15            8 7        0
 *         |--------------|---------------|---------------|----------|
 *  iABC   |    B: 9      |     C: 9      |     A: 8      | Opcode:6 |
 *         |----------------------------------------------------------
 *  iABx   |            Bx: 18            |     A: 8      | Opcode:6 |
 *         |----------------------------------------------------------
 * iAsBx   |           sBx: 18            |     A: 8      | Opcode:6 |
 *         |----------------------------------------------------------
 *   iAx   |                    Ax: 26                    | Opcode:6 |
 *         |----------------------------------------------------------
 */
@Data
@AllArgsConstructor
public class Instruction {

    public static final int MAXARG_BX = 1 << 18 -1;
    public static final int MAXARG_SBX = MAXARG_BX >> 1;

    private static Opcode[] opcodes = Opcode.values();

    private int instruction;

    /**
     * 操作码：低6比特
     */
    public int opcode() {
        return instruction & 0x3F;
    }

    /**
     * 操作模式：iABC
     */
    public IABC abc() {
        return IABC.builder()
                .a(instruction >> 6 & 0xFF)
                .b(instruction >> 14 & 0x1FF)
                .c(instruction >> 23)
                .build();
    }

    /**
     * 操作模式：iABx
     */
    public IABx abx() {
        return IABx.builder()
                .a(instruction >> 6 & 0xFF)
                .bx(instruction >> 14)
                .build();
    }

    /**
     * 操作模式：iAsBx
     */
    public IAsBx asbx() {
        return IAsBx.builder()
                .a(instruction >> 6 & 0xFF)
                .sbx((instruction >> 14) - MAXARG_SBX)
                .build();
    }

    /**
     * 操作模式：iAx
     */
    public IAx ax() {
        return IAx.builder()
                .ax(instruction >> 6)
                .build();
    }

    public String opName() {
        return opcodes[opcode()].name();
    }

    public byte opMode() {
        return opcodes[opcode()].getOpMode();
    }

    public byte argBMode() {
        return opcodes[opcode()].getArgBMode();
    }

    public byte argCMode() {
        return opcodes[opcode()].getArgCMode();
    }

    @Data
    @Builder
    public static class IABC {

        /* 8 bits */
        private int a;
        /* 9 bits */
        private int b;
        /* 9 bits*/
        private int c;
    }

    @Data
    @Builder
    public static class IABx {

        /* 8 bits */
        private int a;
        /* 18 bits */
        private int bx;
    }

    @Data
    @Builder
    public static class IAsBx {

        /* 8 bits */
        private int a;
        /* 18 bits */
        private int sbx;
    }

    @Data
    @Builder
    public static class IAx {

        /* 26 bits */
        private int ax;

    }
}
