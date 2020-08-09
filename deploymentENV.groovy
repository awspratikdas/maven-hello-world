def listProjectName = ["sample-dev", "sample-stage", "sample-prod"]

listProjectName.eachWithIndex { projName, idx ->

freeStyleJob(projName) {
    
     description('My encode decode freestyle job in Groovy')
    
    
    logRotator {
        numToKeep(5)
        daysToKeep(7)
    }
    
    parameters {
        stringParam('env', '', 'enter the env name')
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
    }
}
}
