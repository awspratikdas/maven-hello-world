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
        booleanParam('TESTING-BOOLEAN', true, 'uncheck to disable tests')
        choiceParam('OPTION', ['option 1 (default)', 'option 2', 'option 3'])
        labelParam('master')
    	          }
        
        concurrentBuild(true)
        
        multiscm {
        git {
            remote {
                url('https://github.com/awspratikdas/jenkins-git-integration.git')
		credentials('test')    
                    }
            extensions {
                cleanAfterCheckout()
                wipeOutWorkspace()
                relativeTargetDirectory('IndiaSCM')
                       }
            branch('*/master')
            remote {
                url('https://github.com/awspratikdas/devops_e2e_workflow.git')
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
