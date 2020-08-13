job('DSL_Test_Job_08122020'){
    
    logRotator {
        numToKeep(5)
        daysToKeep(7)
    }
     parameters {
        choiceParam('OPTION', ['option 1 (default)', 'option 2', 'option 3'])
        stringParam('MY STRING PARAM', defaultValue = null, description = 'This is a DSL script generated string param')
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

	steps {

        shell ('echo ${JOB_NAME}>log1.txt')
	    	  
	}
    
}
