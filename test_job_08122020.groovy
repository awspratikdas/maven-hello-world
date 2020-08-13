job('DSL_Test_Job_08122020'){
    
    logRotator {
        numToKeep(5)
        daysToKeep(7)
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

        shell ('echo ${JOB_NAME}>log.txt')

    	  }
    
}
