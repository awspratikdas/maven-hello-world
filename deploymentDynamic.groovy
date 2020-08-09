def listProjectName = ["dev", "stage", "prod"]

def disableProj = [
"dev": false,
    "stage": false,
    "prod": true
]

listProjectName.eachWithIndex { projName, idx ->
    def jobName = "sample_${projName}"
freeStyleJob(jobName) {
    
     description('My encode decode freestyle job in Groovy')
    
    
    logRotator {
        numToKeep(5)
        daysToKeep(7)
    }
    disabled(disableProj[projName])
    
    parameters {
        stringParam('env', "${projName}", 'enter the env name')
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
