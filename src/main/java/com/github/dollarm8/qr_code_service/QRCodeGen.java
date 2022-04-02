package com.github.dollarm8.qr_code_service;

import com.google.zxing.WriterException;

import java.io.IOException;

public interface QRCodeGen {

    byte[] createQRCode(Object data) throws IOException, WriterException;
}
