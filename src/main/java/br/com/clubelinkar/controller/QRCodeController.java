package br.com.clubelinkar.controller;

import net.glxn.qrgen.QRCode;
import net.glxn.qrgen.image.ImageType;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * @author Lennon Jesus
 */
@Controller
public class QRCodeController {

    @ResponseBody
    @RequestMapping(value = "/qrcode/{storeId}/{productId}", method = RequestMethod.GET, produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] qrcode(@PathVariable("storeId") String storeId, @PathVariable("productId") String productId) throws IOException {

        String url = "http://45.55.43.168:8080/checkout/" + storeId + "/" + productId;

        ByteArrayOutputStream in = QRCode.from(url).to(ImageType.PNG).stream();
        return in.toByteArray();
    }

}
