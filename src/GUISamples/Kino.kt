package GUISamples

import org.apache.http.NameValuePair
import org.apache.http.client.methods.HttpGet
import org.apache.http.client.utils.URIBuilder
import org.apache.http.impl.client.HttpClients
import org.apache.http.message.BasicNameValuePair
import org.apache.http.util.EntityUtils
import java.io.IOException
import java.net.URISyntaxException
import java.util.ArrayList

object Kino {
    private val baseURL = "https://kinopoiskapiunofficial.tech"
    private val filmById = "/api/v2.1/films"

    @JvmStatic
    fun main(args: Array<String>) {
        val paratmers = ArrayList<NameValuePair>()

        val headerParams = ArrayList<NameValuePair>()
        headerParams.add(BasicNameValuePair("accept", "application/json"))
        headerParams.add(BasicNameValuePair("X-API-KEY", "f1d94351-2911-4485-b037-97817098724e"))


        try {
            val result = makeAPICall("$baseURL$filmById/301", paratmers, headerParams)
            println(result)
        } catch (e: IOException) {
            println("Error: cannont access content - $e")
        } catch (e: URISyntaxException) {
            println("Error: Invalid URL $e")
        }

    }

    @Throws(URISyntaxException::class, IOException::class)
    fun makeAPICall(uri: String, parameters: List<NameValuePair>, headerParams: List<NameValuePair>): String {
        var response_content = ""

        val query = URIBuilder(uri)
        query.addParameters(parameters)

        val client = HttpClients.createDefault()
        val request = HttpGet(query.build())
        for (p in headerParams) {
            request.addHeader(p.name, p.value)
        }

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