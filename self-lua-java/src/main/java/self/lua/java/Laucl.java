package self.lua.java;

import self.lua.java.binchunk.BinaryChunk;
import self.lua.java.vm.Instruction;
import self.lua.java.vm.Opcode;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class Laucl {

    public static void main(String[] args) {
        String rootDir = System.getProperty("user.dir");
        String luacPath = String.format("%s/self-lua-test/src/ch02/foo_bar.luac", rootDir);
        File file = new File(luacPath);
        try (FileInputStream in = new FileInputStream(file)) {
            byte[] data = new byte[in.available()];
            in.read(data);

            BinaryChunk binaryChunk = new BinaryChunk();
            BinaryChunk.Prototype prototype = binaryChunk.unDump(data);

            laucl(prototype, "main");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static String functionFormat = "%s <%s:%s,%s> (%s instructions at %s)";
    static String paramFormat = "%s%s params, %s slots, %s upvalue, %s locals, %s constant, %s function";
    static String instructionFormat = "        %s       [%s]    %s         %s     ; %s";

    public static void laucl(BinaryChunk.Prototype prototype, String function) {
        System.out.println();
        System.out.println(String.format(functionFormat, function, prototype.getSource(), prototype.getLineDefined(), prototype.getLastLineDefined(), prototype.getCode().length, "0x7fd91ec018d0"));
        System.out.println(String.format(paramFormat, prototype.getNumParams(), prototype.getIsVararg() == 1 ? "+" : "", prototype.getMaxStackSize(), prototype.getUpvalues().length, prototype.getLocVars().length, prototype.getConstants().length, prototype.getProtos().length));

        for (int pc = 0; pc < prototype.getCode().length; pc++) {
            Instruction instruction = new Instruction(prototype.getCode()[pc]);
            System.out.println(String.format(instructionFormat, pc + 1, prototype.getLineInfo()[pc], instruction.opName(), opArg(instruction), prototype.getCode()[pc]));
        }
        for (BinaryChunk.Prototype proto : prototype.getProtos()) {
            laucl(proto, "function");
        }
    }

    public static String opArg(Instruction instruction) {
        switch (instruction.opMode()) {
            case Opcode.IABC:
                Instruction.IABC iabc = instruction.abc();
                return String.format("%s %s %s", iabc.getA(), iabc.getB(), iabc.getC());
            case Opcode.IABx:
                Instruction.IABx iabx = instruction.abx();
                return String.format("%s %s", iabx.getA(), iabx.getBx());
            case Opcode.IAsBx:
                Instruction.IAsBx iasbx = instruction.asbx();
                return String.format("%s %s", iasbx.getA(), iasbx.getSbx());
            case Opcode.IAx:
                Instruction.IAx iax = instruction.ax();
                return String.valueOf(iax.getAx());
            default:
                return "";
        }
    }
}
