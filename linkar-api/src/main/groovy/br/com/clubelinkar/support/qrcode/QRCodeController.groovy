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

/**
 * @author Lennon Jesus
 */
@Controller
public class QRCodeController {

    @ResponseBody
    @RequestMapping(value = "/qrcode/{companyId}/{productId}", method = RequestMethod.GET, produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] qrcode(@PathVariable("companyId") String companyId,
                         @PathVariable("productId") String productId,
                         HttpServletRequest request) throws IOException {

        String url = String.format("http://clubelinkar.com.br/#/order/%s/%s", companyId, productId)

        ByteArrayOutputStream baos = QRCode.from(url).to(ImageType.PNG).stream()

        return baos.toByteArray()
    }

}
