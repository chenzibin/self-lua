package self.lua.java.binchunk;

import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.stream.IntStream;

/**
 * ReaderTest
 *
 * @author chenzb
 * @date 2019/12/25
 */
public class BinaryChunkTest {

    @Test
    public void testUnDump() throws IOException {

        String rootDir = System.getProperty("user.dir");
        String luacPath = String.format("%s/../self-lua-test/src/ch02/foo_bar.luac", rootDir);
        File file = new File(luacPath);
        try (FileInputStream in = new FileInputStream(file)) {
            byte[] data = new byte[in.available()];
            in.read(data);

            BinaryChunk binaryChunk = new BinaryChunk();
            BinaryChunk.Prototype prototype = binaryChunk.unDump(data);
        }

    }

    @Test
    public void test() {
        byte[] data = {0x75, (byte) 0xd2, 0x56, (byte) 0x80, 0x6a, 0x73, 0x7f, 0x40};
        long value = 0;
        for (int i = 0; i < 8; i++) {
            long temp = ((long) (data[i] & 0xff)) << (8 * i);
            value |= temp;
            System.out.println(String.format("%s, %s", temp, value));
        }
        System.out.println(Double.longBitsToDouble(value));
    }

    @Test
    public void test1() {
        byte[] data = {0x75, (byte) 0xd2, 0x56, (byte) 0x80, 0x6a, 0x73, 0x7f, 0x40};
        long i = IntStream.range(0, 8).mapToLong(index -> ((long) data[index]) << (8 * index)).sum();
        System.out.println(i);
        System.out.println(Double.longBitsToDouble(i));
    }
 }
