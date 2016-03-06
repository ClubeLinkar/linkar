package br.com.clubelinkar.support.qrcode

import net.glxn.qrgen.QRCode
import net.glxn.qrgen.image.ImageType
import org.springframework.http.MediaType
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.ResponseBody

import javax.servlet.http.HttpServletRequest
import java.io.ByteArrayOutputStream
import java.io.IOException

/**
 * @author Lennon Jesus
 */
@Controller
public class QRCodeController {

    @ResponseBody
    @RequestMapping(value = "/qrcode/{storeId}/{productId}", method = RequestMethod.GET, produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] qrcode(@PathVariable("storeId") String storeId,
                         @PathVariable("productId") String productId,
                         HttpServletRequest request) throws IOException {

        String url = String.format("http://clubelinkar.com.br/#/order/%s/%s", storeId, productId)

        ByteArrayOutputStream baos = QRCode.from(url).to(ImageType.PNG).stream()

        return baos.toByteArray()
    }

}
