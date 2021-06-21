package builder

import groovy.json.JsonBuilder

JsonBuilder builder = new JsonBuilder()

builder.books {

    currentBook {
        title 'The 4 hours work week'
        isbn '978-0-567-56534'
        author (first: 'Timothy', last: 'Ferriss', twitter: '@tferrirs')
        related 'The 4 hours body', 'The 4 hour chef'
    }

    nextBook {
        title '#AskGaryVee'
        isbn '678-2-345-34543'
        author (first: 'Gary', last: 'Vaynerchuck', twitter: '@garyvee')
        related 'Job, job, job Right Hook', 'Crush It!'
    }
}

new File('../data/books.builder.json').write(builder.toPrettyString())
