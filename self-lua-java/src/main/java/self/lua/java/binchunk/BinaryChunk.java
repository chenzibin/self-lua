package self.lua.java.binchunk;

import lombok.Data;

/**
 * BinaryChunk
 *
 * @author chenzb
 * @date 2019/12/17
 */
@Data
public class BinaryChunk {

    private String header;

    private byte upValueSize;

    private Object mainFunc;
}
