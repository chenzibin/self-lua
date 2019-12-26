package self.lua.java;

import self.lua.java.binchunk.BinaryChunk;

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
    static String instructionFormat = "        %s       [%s]    %s         %s %s     ; %s";

    public static void laucl(BinaryChunk.Prototype prototype, String function) {
        System.out.println();
        System.out.println(String.format(functionFormat, function, prototype.getSource(), prototype.getLineDefined(), prototype.getLastLineDefined(), prototype.getCode().length, "0x7fd91ec018d0"));
        System.out.println(String.format(paramFormat, prototype.getNumParams(), prototype.getIsVararg() == 1 ? "+" : "", prototype.getMaxStackSize(), prototype.getUpvalues().length, prototype.getLocVars().length, prototype.getConstants().length, prototype.getProtos().length));

        for (int pc = 0; pc < prototype.getCode().length; pc++) {
            // todo instructions
            System.out.println(String.format(instructionFormat, pc + 1, prototype.getLineInfo()[pc], "CLOSURE", "0", "1", prototype.getCode()[pc]));
        }
        for (BinaryChunk.Prototype proto : prototype.getProtos()) {
            laucl(proto, "function");
        }
    }

}
