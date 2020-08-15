def gitURL1 = "https://github.com/awspratikdas/jenkins-git-integration.git"
def gitURL2 = "https://github.com/awspratikdas/devops_e2e_workflow.git"
def listProjectName = ["dev", "stage", "prod"]

def disableProj = [
	"dev": false,
    "stage": true,
    "prod": true
]

listProjectName.eachWithIndex { projName, idx ->
    def jobName = "epmp_${projName}"

freeStyleJob('WestCoast/jobName'){
    
    description('My DSL job in Jenkins Groovy')
    
    logRotator {
        daysToKeep(7)
        numToKeep(5)
    			}
    
    disabled(disableProj[projName])
    
        parameters {
        stringParam('env', "${projName}", 'enter the env name')    
        booleanParam('RUN_TESTS', true, 'uncheck to disable tests')
        choiceParam('OPTION', ['option 1 (default)', 'option 2', 'option 3'])
        labelParam('master')
    	          }
        
        concurrentBuild(true)
        
        multiscm {
        git {
            remote {
                url gitURL1
		credentials('test')    
                    }
            extensions {
                cleanAfterCheckout()
                wipeOutWorkspace()
                relativeTargetDirectory('IndiaSCM')
                       }
            branch('*/master')
            remote {
                url gitURL2
				   }
            extensions {
                cleanAfterCheckout()
                wipeOutWorkspace()
                relativeTargetDirectory('HopeSCM')
                       }
            branch('*/master')
        	 }
    	 }
	  }
  }
