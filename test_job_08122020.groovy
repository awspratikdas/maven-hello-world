job('MyDSLJob_08132020'){
    
    logRotator {
        numToKeep(5)
        daysToKeep(7)
    }
     parameters {
        choiceParam('OPTION', ['option 1 (default)', 'option 2', 'option 3'])
        stringParam('MY STRING PARAM', defaultValue = null, description = 'This is a dsl script generated string param')
    }
    scm {
        git {
            remote {
                url('https://github.com/awspratikdas/jenkins-git-integration.git')
		credentials('test')    
                   }
		extensions {
                cleanAfterCheckout()
                            }
            branch('master')
		triggers {
        	scm('5 * * * *')
    		}
        }
    }
   wrappers {
	   
        buildName('JenkinsMaven#${BUILD_NUMBER}')
	   
    }
	steps {

        shell ('echo ${JOB_NAME}>log.txt')
	shell ('echo "Hello World!">>log.txt')
    	  
    }
    
}
