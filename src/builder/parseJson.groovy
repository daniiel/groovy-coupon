package builder

import groovy.json.JsonSlurper

def books = '''
{
    "books": {
        "currentBook": {
            "title": "The 4 hours work week",
            "isbn": "978-0-567-56534",
            "author": {
                "first": "Timothy",
                "last": "Ferriss",
                "twitter": "@tferrirs"
            },
            "related": [
                "The 4 hours body",
                "The 4 hour chef"
            ]
        },
        "nextBook": {
            "title": "#AskGaryVee",
            "isbn": "678-2-345-34543",
            "author": {
                "first": "Gary",
                "last": "Vaynerchuck",
                "twitter": "@garyvee"
            },
            "related": [
                "Job, job, job Right Hook",
                "Crush It!"
            ]
        }
    }
}
'''

JsonSlurper slurper = new JsonSlurper()
def json = slurper.parseText(books)

println json.books.nextBook

JsonSlurper slurper2 = new JsonSlurper()
def json2 = slurper2.parse(new File('../data/books.builder.json'))

println json2.books.currentBook
println json2.books.currentBook.title
println json2.books.currentBook.author.twitter
println json2.books.currentBook.related


