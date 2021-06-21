package builder

import groovy.xml.MarkupBuilder

FileWriter writer = new FileWriter('sports.builder.xml')
MarkupBuilder builder = new MarkupBuilder(writer)
builder.doubleQuotes = true

builder.sports {
    sport(id:1) {
        name 'baseball'
    }
    sport(id:2) {
        name 'basketball'
    }
    sport(id:3) {
        name 'football'
    }
    sport(id:4) {
        name 'hockey'
    }
}
