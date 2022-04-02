package com.github.aussiedev81;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import lombok.Getter;
import lombok.Setter;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
class QRCode {

    private final int SIZE;
    private final int MIN_SIZE = 200;
    private final Object DATA;
    private final String PREFIX = "QR";
    private final String FORMAT = "png";
    private final Charset CHARSET = Charset.defaultCharset();
    private final Map<EncodeHintType, Object> MAP;
    private String codePath;

    public QRCode(Object data) {
        DATA = data;
        SIZE = Math.max(data.toString().length() / 2, MIN_SIZE);
        MAP = new HashMap<>(Map.of(
            EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L,
            EncodeHintType.CHARACTER_SET, CHARSET
        ));
    }

    public File getImageFile() throws WriterException, IOException {
        BitMatrix bitMatrix = new MultiFormatWriter().encode(
            new String(
                DATA.toString().getBytes(CHARSET),
                CHARSET),
            BarcodeFormat.QR_CODE, SIZE, SIZE, MAP);
        codePath = (String.format("%s%s.%s", PREFIX, System.currentTimeMillis(), FORMAT));
        MatrixToImageWriter.writeToPath(
            bitMatrix,
            FORMAT,
            Paths.get(codePath));
        return new File(this.getCodePath());
    }

}
