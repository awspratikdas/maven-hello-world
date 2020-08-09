job('Encode-Decode Groovy') {
    description('My encode decode freestyle job in Groovy')
    
    logRotator {
        numToKeep(5)
        daysToKeep(7)
    }
    
    concurrentBuild(true)
    
    scm {
        git {
            remote {
                
                url('https://github.com/awspratikdas/jenkins-git-integration.git')
                credentials('test')
            }
            
            branch('master')
        }
    }
    
    wrappers {
        credentialsBinding {
            string('MY_SECRET', 'India DevOps secret text')
        }
        
        timestamps()
        colorizeOutput("css")
    }
}
