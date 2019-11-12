//org.apache.httpcomponents:httpclient:jar:4.5.10

import org.apache.http.HttpEntity
import org.apache.http.HttpHeaders
import org.apache.http.HttpResponse
import org.apache.http.NameValuePair
import org.apache.http.client.HttpClient
import org.apache.http.client.methods.CloseableHttpResponse
import org.apache.http.client.methods.HttpGet
import org.apache.http.client.methods.HttpPost
import org.apache.http.client.utils.URIBuilder
import org.apache.http.impl.client.CloseableHttpClient
import org.apache.http.impl.client.HttpClients
import org.apache.http.message.BasicNameValuePair
import org.apache.http.util.EntityUtils

import java.io.IOException
import java.net.URISyntaxException
import java.util.ArrayList


object CoinTest {
    private val baseURL = "http://api.coincap.io/v2/assets"

    @JvmStatic
    fun main(args: Array<String>) {
        val paratmers = ArrayList<NameValuePair>()
        paratmers.add(BasicNameValuePair("interval", "d1"))

        try {
            val result = makeAPICall("$baseURL/bitcoin/history", paratmers)
            println(result)
        } catch (e: IOException) {
            println("Error: cannont access content - $e")
        } catch (e: URISyntaxException) {
            println("Error: Invalid URL $e")
        }

    }

    @Throws(URISyntaxException::class, IOException::class)
    fun makeAPICall(uri: String, parameters: List<NameValuePair>): String {
        var response_content = ""

        val query = URIBuilder(uri)
        query.addParameters(parameters)

        val client = HttpClients.createDefault()
        val request = HttpGet(query.build())
        val response = client.execute(request)

        try {
            System.out.println(response.getStatusLine())
            val entity = response.getEntity()
            response_content = EntityUtils.toString(entity)
            EntityUtils.consume(entity)
        } finally {

        }
        return response_content
    }
}