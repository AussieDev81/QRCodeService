package com.github.aussiedev81;

import com.google.zxing.WriterException;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class QRCodeGenerator implements QRCodeGen {

    @Override
    public byte[] createQRCode(Object data) throws IOException, WriterException {
        QRCode qrCode = new QRCode(data);
        File file = qrCode.getImageFile();
        var response = Files.readAllBytes(file.toPath());
//        Files.deleteIfExists(file.toPath());
        return response;
    }
}
