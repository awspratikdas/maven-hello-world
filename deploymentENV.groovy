def listProjectName = ["sample-dev", "sample-stage", "sample-prod"]

def disableProj = [
"sample-dev": true,
    "sample-stage": false,
    "sample-prod": true
]

listProjectName.eachWithIndex { projName, idx ->

freeStyleJob(projName) {
    
     description('My encode decode freestyle job in Groovy')
    
    
    logRotator {
        numToKeep(5)
        daysToKeep(7)
    }
    disabled(disableProj[projName])
    
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
