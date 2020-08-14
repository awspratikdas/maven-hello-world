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
        }
    }
   wrappers {
	   
        buildName('#${BUILD_NUMBER} on ${ENV,var="BRANCH"}')
	   
    }
	steps {

        shell ('echo ${JOB_NAME}>mylog.txt')
	shell ('echo "Hello World!">log.txt')
    	  
    }
    
}
