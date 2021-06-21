package builder

import groovyx.net.http.ContentType
import groovyx.net.http.RESTClient

@Grab('org.codehaus.groovy.modules.http-builder:http-builder:0.7')

final String base = 'http://api.icndb.com'

def client = new RESTClient(base)
def params = [firstName: "Dani", lastName: "btr"]
client.contentType = ContentType.JSON

client.get(path: '/jokes/random', query: params) { response, json ->
    println response.status
    println json
}