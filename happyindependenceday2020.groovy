def listProjectName = ['dev', 'stage', 'prod']

def disableProj = [
	"dev": true,
    	"stage": false,
    	"prod": false
		  ]

listProjectName.eachWithIndex {projName, index->
    def jobName = "epmp_${projName}"

freeStyleJob(jobName) {
    
    description('My DSL job created on India Independence Day')
    
    logRotator {
        daysToKeep(7)
        numToKeep(5)
    		}
    
    disabled(disableProj[projName])
    concurrentBuild(true)
    label('master')
	
        parameters {
        booleanParam('TESTING-BOOLEAN', true, 'uncheck to disable tests')
        choiceParam('OPTION', ['option 1 (default)', 'option 2', 'option 3'])
                  }   
        
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
            branch("*/master")
	   }
	git {
            remote {
                url('https://github.com/awspratikdas/devops_e2e_workflow.git')
	           }
            extensions {
                cleanAfterCheckout()
                wipeOutWorkspace()
                relativeTargetDirectory('HopeSCM')
                       }
            branch("*/master")
	   }
        	 }
	wrappers {
        credentialsBinding {
            file('MYSECRETFILE', 'secretfile-id')
	                  }
	    buildName('JenkinsMaven#${BUILD_NUMBER}')
                 }
	steps {

        shell ('echo "The secret file data is:$(cat $MYSECRETFILE)">log.txt')
    	  
             }
	 publishers {
           archiveArtifacts {
            	pattern('build/test-output/**/*.html')
            	pattern('build/test-output/**/*.xml')
            	onlyIfSuccessful()
                           }
                    }
    	   }
        }
