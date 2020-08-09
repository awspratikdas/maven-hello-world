job('Encode-Decode Groovy') {
    description('My encode decode freestyle job in Groovy')
    
    logRotator {
        numToKeep(5)
        daysToKeep(7)
    }
     
    concurrentBuild(true)
    
    wrappers {
        credentialsBinding {
            string('MY_SECRET', 'India DevOps secret text')
        }
    }
}
