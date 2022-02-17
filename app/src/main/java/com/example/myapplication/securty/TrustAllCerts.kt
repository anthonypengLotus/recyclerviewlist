import java.security.SecureRandom
import java.security.cert.X509Certificate
import javax.net.ssl.*

//自定义SS验证相关类
class TrustAllCerts : X509TrustManager {

    override fun checkClientTrusted(chain: Array<X509Certificate?>?, authType: String?) {
    }


    override fun checkServerTrusted(chain: Array<X509Certificate?>?, authType: String?) {
    }

    override fun getAcceptedIssuers(): Array<X509Certificate> {
        return arrayOf()
    }

}

 class TrustAllHostnameVerifier : HostnameVerifier {
    override fun verify(hostname: String?, session: SSLSession?): Boolean {
        return true
    }
}






