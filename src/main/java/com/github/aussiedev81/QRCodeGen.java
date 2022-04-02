package com.github.aussiedev81;

import com.google.zxing.WriterException;

import java.io.IOException;

public interface QRCodeGen {

    byte[] createQRCode(Object data) throws IOException, WriterException;
}
