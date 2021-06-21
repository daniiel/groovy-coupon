package builder

import groovy.xml.XmlSlurper

def xml = '''
    <sports>
        <sport>
            <name>Football</name>
        </sport>
    </sports>
'''

def sports = new XmlSlurper().parseText(xml)

println sports.getClass().getName()     // groovy.builder.xml.slurpersupport.NodeChild
println sports.sport.name               // Football

def sports2 = new XmlSlurper().parse('../data/sports.builder.xml')

println sports2                         // baseballbasketballfootballhockey
println sports2.sport[2].name           // football